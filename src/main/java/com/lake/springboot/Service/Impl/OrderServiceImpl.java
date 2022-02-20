package com.lake.springboot.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lake.springboot.Bean.Order;
import com.lake.springboot.Mapper.OrderMapper;
import com.lake.springboot.Service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @author 28794
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
}
