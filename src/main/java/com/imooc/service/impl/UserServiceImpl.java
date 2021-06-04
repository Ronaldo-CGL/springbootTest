package com.imooc.service.impl;

import com.imooc.mapper.MyUsersMapper;
import com.imooc.service.IService.UserService;
import com.imooc.common.enums.Sex;
import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBo;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private static final String USER_FACE = "https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=c%E7%BD%97%E7%85%A7%E7%89%87&hs=2&pn=2&spn=0&di=17490&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=4204562988%2C3747802583&os=2066560735%2C1237711606&simid=3413681789%2C316628231&adpicid=0&lpn=0&ln=30&fr=ala&fm=&sme=&cg=&bdtype=0&oriquery=c%E7%BD%97%E7%85%A7%E7%89%87&objurl=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%3A%2F%2Fsports.gywb.cn%2Fimage%2Fattachement%2Fpng%2Fsite2%2F20150411%2F8941143809356484579.png%26refer%3Dhttp%3A%2F%2Fsports.gywb.cn%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Djpeg%3Fsec%3D1621334921%26t%3Ddce1571fd68a759fa37aa1272b5f1c08&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bxtvt_z%26e3BgjpAzdH3FtvijAzdH3Fi54j-tpj4-dcb0nnn_z%26e3Bip4s&gsm=3&islist=&querylist=";
    //@Autowired
    //@Qualifier(value = "usermapper")
    @Resource
    UsersMapper usersMapper;
    @Autowired
    MyUsersMapper myUsersMapper;
    @Autowired
    private Sid sid;
    @Override
    public boolean queryUserNameIsExist(String userName) {
        Example UserExample = new Example(Users.class);
        Example.Criteria userCriteria = UserExample.createCriteria();
        userCriteria.andEqualTo("username",userName);
        Users users = usersMapper.selectOneByExample(UserExample);
        return users ==null?false:true;
    }

    @Override
    public Users creatUser(UserBo userBo) {
        String userId = sid.nextShort();
        String password = DigestUtils.md5DigestAsHex(userBo.getPassword().getBytes());
        Users users = new Users();
        users.setId(userId);
        users.setUsername(userBo.getUsername());
        users.setPassword(password);
        users.setNickname(userBo.getUsername());
        users.setFace(USER_FACE);
        users.setBirthday(new Date());
        users.setSex(Sex.secret.type);
        users.setCreatedTime(new Date());
        users.setUpdatedTime(new Date());
        usersMapper.insert(users);
        return users;
    }

    @Override
    public Users queryUserForLogin(String username,String password) {
        String pwd = DigestUtils.md5DigestAsHex(password.getBytes());
        Users users = usersMapper.queryUserForLogin(username,pwd);
        return users;
    }

    @Override
    public List<Users> queryUser(Map<String,Object> usermap) {
        return myUsersMapper.queryUser(usermap);
    }

    @Override
    public int delteUser(String userid) {
        return usersMapper.mydelteUser(userid);
    }

    @Override
    public int updateUser(String userid, String username) {
        return usersMapper.updateUser(userid,username);
    }

    @Override
    public List<Users> selectLikeByName(String username) {
        return usersMapper.selectLikeByName(username);
    }


}
