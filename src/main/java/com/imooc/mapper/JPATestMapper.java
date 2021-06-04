package com.imooc.mapper;

import com.imooc.pojo.JPATest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface JPATestMapper extends JpaRepository<JPATest,Integer> {
}
