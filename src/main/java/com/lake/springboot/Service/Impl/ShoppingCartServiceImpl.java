package com.lake.springboot.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lake.springboot.Bean.ShoppingCart;
import com.lake.springboot.Mapper.ShoppingCartMapper;
import com.lake.springboot.Service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
