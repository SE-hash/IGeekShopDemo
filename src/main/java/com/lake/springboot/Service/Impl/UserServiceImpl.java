package com.lake.springboot.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lake.springboot.Bean.User;
import com.lake.springboot.Mapper.UserMapper;
import com.lake.springboot.Service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
