package com.lake.springboot.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lake.springboot.Bean.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {
}
