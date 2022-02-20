package com.lake.springboot.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lake.springboot.Bean.Admin;
import com.lake.springboot.Bean.Product;
import com.lake.springboot.Bean.R;
import com.lake.springboot.Service.AdminService;
import com.lake.springboot.Service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author 28794
 */

@Controller
@Slf4j
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    ProductService productService;

    @GetMapping("/adminLogin")
    public String adminLogin() {
        return "admin/adminLogin";
    }

    @PostMapping("/adminLogin")
    @ResponseBody
    public R adminLogin(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ID", username);
        queryWrapper.eq("password", password);

        Admin admin = adminService.getOne(queryWrapper);
        session.setAttribute("admin", admin);

        return new R(admin != null, admin != null ? "登录成功!" : "登录失败!");
    }

    @GetMapping("/adminMessage")
    @ResponseBody
    public R adminMessage(HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        return new R(admin != null, admin);
    }

    @GetMapping("/adminIndex")
    public String adminIndex(Model model, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        model.addAttribute("admin", admin);
        return "admin/adminIndex";
    }

    @GetMapping("/addProduct")
    public String addProduct() {
        return "admin/addProduct";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("filename") String filename
            , @RequestPart("img") MultipartFile img) throws IOException {
        String name = img.getOriginalFilename();
        String suffixName = name.substring(name.lastIndexOf("."));
        if (!img.isEmpty()) {
            img.transferTo(new File("E:\\idea_projects\\IGeekShopDemo\\target\\classes\\static\\images\\products\\" + filename + suffixName));
        }
        return "redirect:addProduct";
    }

    @PostMapping("/addProduct")
    @ResponseBody
    public R addProduct(Product product) {
        product.setPicture("/IGeekShopDemo/images/products/" + product.getId() + ".jpg");
        boolean save = productService.save(product);
        return new R(save, save ? "添加成功!" : "添加失败!");
    }

    @GetMapping("/adminExit")
    public String adminExit(HttpSession session) {
        session.removeAttribute("admin");
        return "admin/adminLogin";
    }

    @GetMapping("/getAllProducts")
    @ResponseBody
    public R getAllProducts() {
        List<Product> products = productService.list();
        return new R(products != null, products);
    }

    @GetMapping("/allProducts")
    public String allProducts() {
        return "admin/allProducts";
    }

    @PostMapping("/updateProduct")
    @ResponseBody
    public R updateProduct(@RequestParam("id") String id,
                           @RequestParam("name") String name,
                           @RequestParam("price") Double price,
                           @RequestParam("description") String description,
                           @RequestParam("quantity") Integer quantity) {
        Product product = productService.getById(id);
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setQuantity(quantity);
        boolean save = productService.saveOrUpdate(product);
        return new R(save, save ? "更新成功!" : "更新失败!");
    }

    @PostMapping("/deleteProduct")
    @ResponseBody
    public R deleteProduct(@RequestParam("id") String id) {
        boolean remove = productService.removeById(id);
        return new R(remove, remove ? "删除成功!" : "删除失败!");
    }
}
