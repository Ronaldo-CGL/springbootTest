package com.imooc.mapper;

import com.imooc.mapper.MyMapper.MyMapper;
import com.imooc.pojo.Fruit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
//@Repository
public interface FruitMapper extends MyMapper<Fruit> {
     List<Fruit> selectByName(String name);
     Fruit selectByIdAndName(Integer fid,String name);
}