package com.imooc.service.impl;

import com.imooc.mapper.FruitMapper;
import com.imooc.pojo.Fruit;
import com.imooc.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FruitMapper fruitMapper;
    @Override
    public int addFruit(Fruit fruit) {
        return fruitMapper.insert(fruit);
    }

    @Override
    public int delete(int fid) {
        return fruitMapper.deleteByPrimaryKey(fid);
    }

    public int updateFruit(Fruit fruit) {

        return fruitMapper.updateByPrimaryKey(fruit);
    }

    @Override
    public Fruit getFruit(int fid) {
        return fruitMapper.selectByPrimaryKey(fid);
    }

    @Override
    public List<Fruit> selectByName(String name) {
        return fruitMapper.selectByName(name);
    }

    @Override
    public Fruit selectByIdAndName(Integer fid, String name) {
        return fruitMapper.selectByIdAndName(fid,name);
    }
}
