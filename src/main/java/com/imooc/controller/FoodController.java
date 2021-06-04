package com.imooc.controller;

import com.imooc.pojo.Fruit;
import com.imooc.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
@ApiIgnore
@RestController
public class FoodController {
    @Autowired
    public FoodService foodService;
    @RequestMapping("selectByIdAndName")
    public Fruit selectByIdAndName(@RequestParam(value = "fid",required = false) Integer fid, @RequestParam(value = "name",required = false) String name){
        return foodService.selectByIdAndName(fid,name);
    }
    @RequestMapping("/selectByName")
    public List<Fruit> selectByName(String name){
        return foodService.selectByName(name);
    }
    @GetMapping("/showFood")
    public Fruit showFood(@RequestParam(value = "fid") int fid){
        return foodService.getFruit(fid);
    }
    @PostMapping("/addFood")
    public int addFruit(String name){
        Fruit fruit = new Fruit();
        fruit.setName(name);
        return foodService.addFruit(fruit);
    }
    @RequestMapping("/delete")
    public int deleteFruit(int fid){
        return  foodService.delete(fid);
    }
    @RequestMapping("/update")
    public int updateFruit(int fid){
        Fruit fruit = new Fruit();
        fruit.setFid(fid);
        fruit.setName("banana");
        return foodService.updateFruit(fruit);
    }
}
