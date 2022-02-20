package com.lake.springboot.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lake.springboot.Bean.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper  extends BaseMapper<Product> {
}
