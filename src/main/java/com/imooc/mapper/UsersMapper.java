package com.imooc.mapper;

import com.imooc.mapper.MyMapper.MyMapper;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UsersMapper extends MyMapper<Users> {
    Users queryUserForLogin(String username,String password);
    int mydelteUser(String userid);
    int updateUser(String userid,String username);
    List<Users> selectLikeByName(String username);
}