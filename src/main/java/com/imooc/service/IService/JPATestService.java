package com.imooc.service.IService;

import com.imooc.pojo.JPATest;

import java.util.List;

public interface JPATestService {
    JPATest insertone(JPATest jpaTest);
    String deleteById(Integer id);
    JPATest updateOne(JPATest jpaTest);
    JPATest findById(Integer myid);
    List<JPATest> findALL();

}
