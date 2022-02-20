package com.lake.springboot.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lake.springboot.Bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
