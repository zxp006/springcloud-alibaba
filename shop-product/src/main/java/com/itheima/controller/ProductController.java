package com.itheima.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itheima.domain.Product;
import com.itheima.domain.User;
import com.itheima.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/product/api1/demo1")
    public User demo1(@RequestBody User user) {
       User user1= new User();
       user1.setUid(1000);
       user1.setUsername(user.getUsername()+"-服务返回");
       user1.setTelephone("13521161595");
       user1.setPassword("123456");
       return  user1;
    }

    @RequestMapping("/product/api1/demo2")
    public JSONObject demo2(User user) {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("uid",user.getUid());
        jsonObject.put("username","json值username");
        jsonObject.put("address",user.getTelephone());
        return jsonObject;
    }

    @RequestMapping("/product/api2/demo3")
    public String demo3() {
        return "demo3";
    }

    @RequestMapping("/product/api2/demo4")
    public String demo4() {
        return "demo4";
    }


    //商品信息查询
    @RequestMapping("/product/{pid}")
    public Product product(@PathVariable("pid") Integer pid) {
        log.info("接下来要进行{}号商品信息的查询", pid);
        Product product = productService.findByPid(pid);
        log.info("商品信息查询成功,内容为{}", JSON.toJSONString(product));
        return product;
    }

    //扣减库存
    @RequestMapping("/product/reduceInventory")
    public void reduceInventory(Integer pid, Integer number) {
        productService.reduceInventory(pid, number);
    }
}
