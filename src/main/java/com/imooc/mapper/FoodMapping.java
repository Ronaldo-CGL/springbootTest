package com.imooc.mapper;

import com.imooc.pojo.Fruit;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface FoodMapping {
    Fruit getFood(int fid);
}
