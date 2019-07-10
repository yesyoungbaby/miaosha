package com.muke.miaosha.controller;

import com.muke.miaosha.result.Result;
import com.muke.miaosha.service.MiaoShaUserService;
import com.muke.miaosha.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author yesyoungbaby
 * @Title: LonginController
 * @ProjectName miaosha
 * @Description: TODO
 * @date 2019/6/2611:17
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    MiaoShaUserService userService;

/*    @Autowired
    RedisService redisService;*/

    /**
     * 跳转至登录页面
     * @return
     */
    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    /**
     *
     * @param response
     * @param loginVo
     * @return
     */
    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
        //System.out.println("测试 哈哈哈哈哈");
        // loginVo接收前端传来的参数

        log.info(loginVo.toString());

        // 做参数校验  使用了jsr校验
/*        String pwd = loginVo.getPassword();
        if(pwd == null){
            return Result.error(CodeMsg.PASSWORD_EMPTY);
        }
        String mobile = loginVo.getMobile();
        if(mobile == null){
            return Result.error(CodeMsg.MOBILE_EMPTY);
        }
        if(!ValidatorUtil.isMobile(mobile)){
            return Result.error(CodeMsg.MOBILE_ERROR);
        }*/

/*        登录判断
        //CodeMsg code = userService.login(loginVo);
        if(code.getCode() == 0){
            return Result.success(true);
        }
        return Result.error(code);*/

        //增加了全局异常处理后代码较上面的改动
        // loginVo是前端给后台做登录处理的 response是后台处理完返回给client的

        userService.login(response, loginVo);
        return Result.success(true);
    }
}
