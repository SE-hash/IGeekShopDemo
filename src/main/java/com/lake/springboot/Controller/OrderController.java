package com.lake.springboot.Controller;

import com.lake.springboot.Bean.Order;
import com.lake.springboot.Bean.R;
import com.lake.springboot.Service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/getOrder")
    @ResponseBody
    public R getOrder() {
        List<Order> orderList = orderService.list();
        return new R(orderList != null, orderList);
    }

    @GetMapping("/orders")
    public String order() {
        return "/orders";
    }
}
