package com.imooc.service.impl;

import com.imooc.mapper.JPATestMapper;
import com.imooc.pojo.JPATest;
import com.imooc.service.IService.JPATestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JPATestServiceImpl implements JPATestService {
    @Autowired
    private JPATestMapper jpaTestMapper;

    @Override
    public JPATest insertone(JPATest jpaTest) {
        return jpaTestMapper.save(jpaTest);
    }

    @Override
    public String deleteById(Integer id) {
        jpaTestMapper.deleteById(id);
        return "delete ok";
    }

    @Override
    public JPATest updateOne(JPATest jpaTest) {
        return jpaTestMapper.save(jpaTest);
    }

    @Override
    public JPATest findById(Integer myid) {
        //TODO:为什么不行
        //JPATest jpaTest = jpaTestMapper.getOne(myid);
        JPATest jpaTest = jpaTestMapper.findById(myid).get();
        return jpaTest;
    }

    @Override
    public List<JPATest> findALL() {
        return jpaTestMapper.findAll();
    }

}
