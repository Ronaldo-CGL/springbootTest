package com.imooc.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.imooc.common.utils.IMOOCJSONResult;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBo;
import com.imooc.service.IService.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "注册登录", tags = "用于注册登录的接口")
@RestController
@RequestMapping("passport")
public class PassportController {
    private static final Logger logger = LoggerFactory.getLogger(PassportController.class);
    @Autowired
    UserService userService;
    @Autowired
    RedisTemplate redisTemplate;

    //    @GetMapping("/dandian")
//    public Users dandian() {
//        Users users =  redisTemplate.opsForValue().get("210523D1CFHASM3C");
//        System.out.println(users.toString());
//        return users;
//    }
    @GetMapping("/usernameIsExist")
    public IMOOCJSONResult usernameIsExist(@RequestParam String username) {
        //int a = 1/0;
        if (StringUtils.isBlank(username)) {
            return IMOOCJSONResult.errorMsg("请输入用户名");
        }
        boolean userNameIsExist = userService.queryUserNameIsExist(username);

        if (userNameIsExist) {
            return IMOOCJSONResult.errorMsg("用户名已存在");
        }
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "用户操作", notes = "用户注册")
    @PostMapping("/regist")
    public synchronized IMOOCJSONResult regist(@RequestBody UserBo userBo) {
        String username = userBo.getUsername();
        String password = userBo.getPassword();
        String confirmPassword = userBo.getConfirmPassword();
        //0. 判断用户名和密码必须不为空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(confirmPassword)) {
            return IMOOCJSONResult.errorMsg("用户名密码不能为空");
        }
        //1. 查询用户名是否存在
        if (userService.queryUserNameIsExist(username)) {
            return IMOOCJSONResult.errorMsg("用户名已存在");
        }
        //2. 判断两次密码是否一致
        if (!password.equals(confirmPassword)) {
            return IMOOCJSONResult.errorMsg("两次密码不一致");
        }
        //3. 密码长度不能少于6位
        if (password.length() < 6) {
            return IMOOCJSONResult.errorMsg("密码长度不能少于6位");
        }
        //4. 实现注册
        userService.creatUser(userBo);
        return IMOOCJSONResult.ok(userBo);
    }

    //    @ApiOperation(value = "用户登录",notes = "登录")
//    @GetMapping("/login")
//    public IMOOCJSONResult queryUserForLogin(@RequestBody UserBo userBo) {
//        String username = userBo.getUsername();
//        String password = userBo.getPassword();
//        String pwd = DigestUtils.md5DigestAsHex(password.getBytes());
//        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
//            return IMOOCJSONResult.errorMsg("用户名密码不能为空");
//        }
//        Users users = userService.queryUserForLogin(userBo);
//        if (users == null) {
//            return IMOOCJSONResult.errorMsg("用户名密码输入有误");
//        }
//        return IMOOCJSONResult.ok(users);
//    }
    @ApiOperation(value = "用户登录", notes = "登录")
    @GetMapping("/login")
    public IMOOCJSONResult queryUserForLogin(@RequestParam(required = false) String username, @RequestParam String password) {
        logger.info("login进入");
        //0. 判断用户名和密码必须不为空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return IMOOCJSONResult.errorMsg("用户名密码不能为空");
        }
        Users users = userService.queryUserForLogin(username, password);
        if (users == null) {
            return IMOOCJSONResult.errorMsg("用户名密码输入错误");
        }
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.json());
        redisTemplate.opsForValue().set(users.getId(), users);
        return IMOOCJSONResult.ok(users);
    }

    private Users setNullProperty(Users users) {
        users.setPassword(null);
        users.setMobile(null);
        users.setEmail(null);
        users.setCreatedTime(null);
        users.setUpdatedTime(null);
        users.setBirthday(null);
        return users;
    }

    /**
     * 查询用户
     *
     * @param username
     * @param userid
     * @return
     */
    @ApiOperation(value = "查询用户", notes = "查询用户")
    @GetMapping("/queryUser")
    public IMOOCJSONResult queryUser(@RequestParam(required = false) String userid,
                                     @RequestParam(required = false) String username,
                                     @RequestParam(required = false) int pagenum,
                                     @RequestParam(required = false) int size) {
        Map<String, Object> userMap = new HashMap<String, Object>();
        userMap.put("userid", userid);
        userMap.put("username", username);
        PageHelper.startPage(pagenum, size);  //分页
        int pages = PageHelper.getLocalPage().getPageNum();
        int pageSize = PageHelper.getLocalPage().getPageSize();
        List<Users> users = userService.queryUser(userMap);

        // Users user = (Users) redisTemplate.opsForValue().get(users.get(0).getId());
        // Users users1 = (Users) redisTemplate.opsForValue().get(users.get(0));
        return IMOOCJSONResult.ok(users);
    }

    /**
     * 根據id刪除用戶
     *
     * @param userid
     * @return
     */
    @ApiOperation(value = "刪除用户", notes = "刪除用户")
    @GetMapping("/deleteUser")
    public IMOOCJSONResult deleteUser(@RequestParam(required = false) String userid) {
        int result = userService.delteUser(userid);
        if (result == 1) {
            return IMOOCJSONResult.ok();
        } else {
            return IMOOCJSONResult.errorMsg("刪除失敗");
        }
    }

    /**
     * 更新用戶
     *
     * @param userid
     * @param username
     * @return
     */
    @ApiOperation(value = "更新用戶", notes = "更新用戶")
    @GetMapping("/updateUser")
    public IMOOCJSONResult updateUser(@RequestParam(required = false) String userid, @RequestParam(required = false) String username) {
        int result = userService.updateUser(userid, username);
        if (result == 1) {
            return IMOOCJSONResult.ok();
        } else {
            return IMOOCJSONResult.errorMsg("更新失敗");
        }
    }

    /**
     * 模糊查詢
     *
     * @param username
     * @return
     */
    @ApiOperation(value = "模糊查詢用戶", notes = "模糊查詢用戶")
    @GetMapping("/selectLikeByName")
    public IMOOCJSONResult selectLikeByName(@RequestParam(required = false) String username) {
        List<Users> list = userService.selectLikeByName(username);
        if (list.size() > 0) {
            return IMOOCJSONResult.ok(list);
        } else {
            return IMOOCJSONResult.errorMsg("用戶不存在");
        }
    }

}
