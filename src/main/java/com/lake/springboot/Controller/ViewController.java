package com.lake.springboot.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lake.springboot.Bean.*;
import com.lake.springboot.Mapper.ShoppingCartMapper;
import com.lake.springboot.Service.OrderService;
import com.lake.springboot.Service.ProductService;
import com.lake.springboot.Service.ShoppingCartService;
import com.lake.springboot.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author 28794
 */
@Slf4j
@Controller
public class ViewController {

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    ShoppingCartMapper shoppingCartMapper;

    @Autowired
    OrderService orderService;

    @GetMapping({"/", "/index"})
    public String index(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
                        Model model) {
        Page<Product> productPage = new Page<>(pn, 4);
        //查询新增产品
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("is_new", 1);
        Page<Product> productsPage = productService.page(productPage, productQueryWrapper);
        model.addAttribute("products", productsPage);
        return "index";
    }

    @GetMapping("/products/{category}")
    public String products(@PathVariable("category") String category,
                           @RequestParam(value = "pn", defaultValue = "1") Integer pn,
                           Model model) {
        Page<Product> productPage = new Page<>(pn, 4);
        //查询指定类型商品
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("category", category);
        Page<Product> page = productService.page(productPage, productQueryWrapper);
        model.addAttribute("products", page);
        return "Product";
    }

    @GetMapping("/Login")
    public String login() {
        return "login";
    }

    @PostMapping("/Login")
    @ResponseBody
    public R loginCheck(User user, HttpSession session, Model model) {
        //查询数据库中是否存在此用户
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", user.getUsername());
        userQueryWrapper.eq("password", user.getPassword());
        User loginUser = userService.getOne(userQueryWrapper);
        boolean flag = loginUser != null;
        if (loginUser != null) {
            session.setAttribute("loginUser", loginUser);
        }
        return new R(flag, flag ? "登录成功OvO" : "登录失败!请检查密码是否输入正确!");
    }

    @GetMapping("/Register")
    public String register() {
        return "register";
    }

    @PostMapping("/Register")
    @ResponseBody
    public R checkRegister(User user) {
        boolean flag = userService.save(user);
        return new R(flag, flag ? "注册成功OvO,即将返回登录界面" : "注册失败,该用户已经存在!");
    }

    @GetMapping("/accountMessage")
    @ResponseBody
    public R getAccountMsg(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        return new R(loginUser != null, loginUser);
    }

    @GetMapping("/exit")
    public String exit(HttpSession session) {
        session.removeAttribute("loginUser");
        return "redirect:/";
    }

    @GetMapping("/ShoppingCart")
    public String shoppingCart() {
        return "ShoppingCart";
    }

    @GetMapping("/getProducts")
    @ResponseBody
    public R getProducts(HttpServletRequest request) {
        List<ShoppingCart> productsInfo;
        productsInfo = shoppingCartMapper.selectList(null);
        return new R(productsInfo != null, productsInfo);
    }

    @GetMapping("/state")
    public String state() {
        return "/success";
    }

    @GetMapping("/detail/{id}")
    public String details(@PathVariable("id") String id,Model model) {
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        return "/detail";
    }
}
