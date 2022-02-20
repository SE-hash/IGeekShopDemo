package com.lake.springboot.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lake.springboot.Bean.Product;
import com.lake.springboot.Mapper.ProductMapper;
import com.lake.springboot.Service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}
