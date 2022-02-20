package com.lake.springboot.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lake.springboot.Bean.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 28794
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
