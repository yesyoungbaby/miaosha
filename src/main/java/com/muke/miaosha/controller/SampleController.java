package com.muke.miaosha.controller;

import com.muke.miaosha.domain.User;
import com.muke.miaosha.redis.RedisService;
import com.muke.miaosha.redis.UserKey;
import com.muke.miaosha.result.CodeMsg;
import com.muke.miaosha.result.Result;
import com.muke.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/demo/s")
public class SampleController {

	@Autowired
    UserService userService;
	
	@Autowired
    RedisService redisService;
	
    @RequestMapping("/hello")
    @ResponseBody
    public Result<String> home() {
        return Result.success("Hello，world");
    }
    
    @RequestMapping("/error")
    @ResponseBody
    public Result<String> error() {
        return Result.error(CodeMsg.SESSION_ERROR);
    }
    
    @RequestMapping("/hello/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "Joshua");
        return "hello";
    }
    
    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbGet() {
    	User user = userService.getById(1);
        return Result.success(user);
    }
    
    
    @RequestMapping("/db/tx")
    @ResponseBody
    public Result<Boolean> dbTx() {
    	userService.tx();
        return Result.success(true);
    }

/*    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<String>  redisSet(){
        boolean set = redisService.set("key1", "abc");
        String res = redisService.get("key1",String.class);
        System.out.println(res);
        return Result.success(res);
    }*/
    
    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet() {
    	User  user  = redisService.get(UserKey.getById, ""+1, User.class);
        return Result.success(user);
    }
    
    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet() {
    	User user  = new User();
    	user.setId(1);
    	user.setName("1111");
        //UserKey:id1
    	redisService.set(UserKey.getById, ""+1, user);
        return Result.success(true);
    }


}
