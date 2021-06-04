package com.imooc.controller;

import com.github.pagehelper.PageHelper;
import com.imooc.pojo.JPATest;
import com.imooc.service.IService.JPATestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "JPATest",tags = "JPATest")
@RestController
public class JPATestController {
    @Autowired
    private JPATestService jpaTestService;

    /**
     * 新增
     * @param jpaTest
     * @return
     */
    @GetMapping("/save")
    @ApiOperation(value = "新增", notes = "新增")
    public JPATest findById(@RequestBody JPATest jpaTest){
        return jpaTestService.insertone(jpaTest);
    }

    /**
     * 根据id删除
     * @param id
     */
    @GetMapping("/deleteOne")
    public void deleteOne(@RequestParam Integer id){
        jpaTestService.deleteById(id);
    }

    @GetMapping("/updateOne")
    public JPATest updateOne(@RequestBody JPATest jpaTest){
        return jpaTestService.updateOne(jpaTest);
    }

    @GetMapping("/getOneById")
    public JPATest findById(@RequestParam Integer id){
        return jpaTestService.findById(id);
    }

    /**
     * 查询所有
     * @return
     */
    @GetMapping("/findALL")
    public List<JPATest> findALL(){
        PageHelper.startPage(1,2);
        return jpaTestService.findALL();
    }

}
