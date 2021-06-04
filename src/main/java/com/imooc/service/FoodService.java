package com.imooc.service;


import com.imooc.pojo.Fruit;

import java.util.List;

public interface FoodService {
    int addFruit(Fruit fruit);
    int delete(int fid);
    int updateFruit(Fruit fruit);
    Fruit getFruit(int fid);
    List<Fruit> selectByName(String name);
    Fruit selectByIdAndName(Integer fid,String name);
}
