package com.example.demo.mapper;

import com.example.demo.common.BaseMapper;
import com.example.demo.entity.AdminUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminUserMapper extends BaseMapper<AdminUser> {
}