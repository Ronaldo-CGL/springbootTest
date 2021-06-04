package com.imooc.mapper;

import com.imooc.mapper.MyMapper.MyMapper;
import com.imooc.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MyUsersMapper{
    List<Users> queryUser(@Param("paramsMap") Map<String,Object> usermap);
}