package com.lake.springboot.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lake.springboot.Bean.Admin;
import com.lake.springboot.Mapper.AdminMapper;
import com.lake.springboot.Service.AdminService;
import org.springframework.stereotype.Service;
/**
 * @author 28794
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
}
