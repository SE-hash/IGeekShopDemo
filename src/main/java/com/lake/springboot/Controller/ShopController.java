package com.lake.springboot.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lake.springboot.Bean.*;
import com.lake.springboot.Mapper.ShoppingCartMapper;
import com.lake.springboot.Service.OrderService;
import com.lake.springboot.Service.ProductService;
import com.lake.springboot.Service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.*;

@Slf4j
@Controller
public class ShopController {
    @Autowired
    ProductService productService;

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    ShoppingCartMapper shoppingCartMapper;

    @Autowired
    OrderService orderService;

    @PostMapping("/products")
    @ResponseBody
    public R putProducts(@RequestParam("id") String id,
                         @RequestParam("num") Integer num,
                         HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        Product product = productService.getById(id);

        //先查询购物车中有没有此产品
        QueryWrapper<ShoppingCart> shoppingCartQueryWrapper = new QueryWrapper<>();
        shoppingCartQueryWrapper.eq("username", loginUser.getUsername());
        shoppingCartQueryWrapper.eq("product_id", id);
        ShoppingCart one = shoppingCartService.getOne(shoppingCartQueryWrapper);

        boolean save;
        if (one == null) {
            ShoppingCart cart = new ShoppingCart(loginUser.getUsername(), id, product.getName(), product.getPrice(), num);
            save = shoppingCartService.save(cart);
        }else{
            one.setNumber(one.getNumber() + num);
            save = shoppingCartService.update(one, shoppingCartQueryWrapper);
        }
        return new R(save, save ? "添加成功!" : "添加失败!");
    }

    /**
     *
     * @param id
     * @param session
     * @return
     */
    @DeleteMapping("/products")
    public String delProducts(@RequestParam("id") String id, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        QueryWrapper<ShoppingCart> shoppingCartQueryWrapper = new QueryWrapper<>();
        shoppingCartQueryWrapper.eq("product_id", id);
        shoppingCartQueryWrapper.eq("username", loginUser.getUsername());
        boolean remove = shoppingCartService.remove(shoppingCartQueryWrapper);
        return "ShoppingCart";
    }

    @PutMapping("/products")
    public String updateProducts(HttpSession session, @RequestParam("id") String id, @RequestParam("number") Integer number) {
        User loginUser = (User) session.getAttribute("loginUser");
        QueryWrapper<ShoppingCart> shoppingCartQueryWrapper = new QueryWrapper<>();
        shoppingCartQueryWrapper.eq("product_id", id);
        shoppingCartQueryWrapper.eq("username", loginUser.getUsername());

        ShoppingCart one = shoppingCartService.getOne(shoppingCartQueryWrapper);
        one.setNumber(number);
        //修改商品数量
        shoppingCartService.update(one,shoppingCartQueryWrapper);
        return "ShoppingCart";
    }

    @PostMapping("/orders")
    public String orders(HttpSession session, @RequestParam("address") String address, HttpServletRequest request) {
        String ids = request.getParameterValues("ids")[0];
        Calendar now = Calendar.getInstance();
        User loginUser = (User) session.getAttribute("loginUser");

        String[] split = ids.split(",");
        List<ShoppingCart> shoppingCarts = new ArrayList<>();

        QueryWrapper<ShoppingCart> shoppingCartQueryWrapper = new QueryWrapper<>();
        for (String s : split) {
            shoppingCartQueryWrapper.clear();
            shoppingCartQueryWrapper.eq("username", loginUser.getUsername());
            shoppingCartQueryWrapper.eq("product_id",s);
            ShoppingCart one = shoppingCartService.getOne(shoppingCartQueryWrapper);

            shoppingCarts.add(shoppingCartService.getOne(shoppingCartQueryWrapper));
        }

        //
        //插入订单信息到订单表
        for (ShoppingCart cart : shoppingCarts) {
            now.get(Calendar.YEAR);
            Order order = new Order(UUID.randomUUID().toString(), cart.getProductId(),cart.getName(),
                    loginUser.getUsername(), cart.getPrice(), cart.getNumber(), 1.000,now.get(Calendar.YEAR) + "年" +  now.get(Calendar.MONTH) + "月" +  now.get(Calendar.DAY_OF_MONTH) + "日",address);
            orderService.save(order);
        }

        //更新产品表数量
        for (ShoppingCart cart : shoppingCarts) {
            Product byId = productService.getById(cart.getProductId());
            byId.setQuantity(byId.getQuantity() - cart.getNumber());
            productService.saveOrUpdate(byId);
        }

        //将此用户购物车清空
        for (ShoppingCart shoppingCart : shoppingCarts) {
            shoppingCartQueryWrapper.clear();
            shoppingCartQueryWrapper.eq("username", loginUser.getUsername());
            shoppingCartQueryWrapper.eq("product_id", shoppingCart.getProductId());
            shoppingCartService.remove(shoppingCartQueryWrapper);
        }

        return "redirect:/state";
    }
}
