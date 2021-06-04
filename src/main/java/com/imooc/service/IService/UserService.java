package com.imooc.service.IService;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBo;

import java.util.List;
import java.util.Map;

public interface UserService {
    boolean queryUserNameIsExist(String userName);
    Users creatUser(UserBo userBo);
    Users queryUserForLogin(String username,String password);
    List<Users> queryUser(Map<String,Object> usermap);
    int delteUser(String userid);
    int updateUser(String userid,String username);
    List<Users> selectLikeByName(String username);

}
