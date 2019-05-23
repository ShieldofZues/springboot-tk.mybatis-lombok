package com.example.demo.common;


import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
/**
 * @Author shieldofzues
 * @Description BaseMapper
 * @Date 11:50 2016/5/23
 *
 **/
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
