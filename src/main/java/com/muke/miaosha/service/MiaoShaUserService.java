package com.muke.miaosha.service;

import com.muke.miaosha.dao.MiaoshaUserDao;
import com.muke.miaosha.domain.MiaoshaUser;
import com.muke.miaosha.exception.GlobalException;
import com.muke.miaosha.redis.MiaoshaUserKey;
import com.muke.miaosha.redis.RedisService;
import com.muke.miaosha.result.CodeMsg;
import com.muke.miaosha.utli.MD5Util;
import com.muke.miaosha.utli.UUIDUtil;
import com.muke.miaosha.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yesyoungbaby
 * @Title: MiaoShaUserService
 * @ProjectName miaosha
 * @Description: TODO
 * @date 2019/6/2611:40
 */
@Service
public class MiaoShaUserService {
    public static final String COOKI_NAME_TOKEN = "token";

    @Autowired
    MiaoshaUserDao miaoshaUserDao;

    @Autowired
    RedisService redisService;

    /**
     * 用手机号查用户
     * @param id
     * @return
     */
    public MiaoshaUser getById(long id) {
        return miaoshaUserDao.getById(id);
    }

    //public CodeMsg login(LoginVo loginVo){

    public boolean login(HttpServletResponse response, LoginVo loginVo){
        if(loginVo == null){
            //return CodeMsg.SERVER_ERROR;
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        // 提取用户输入的信息
        String mobile = loginVo.getMobile();
        String pwd = loginVo.getPassword();

        //判断用户是否存在
        MiaoshaUser user = getById(Long.parseLong(mobile));

        if(user == null){
            //return CodeMsg.MOBILE_NOT_EXIST;
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }

        //验证密码

        // （拿手机号）取该用户db中的密码
        String dbPass = user.getPassword();

        // 取db中该user的slat
        String slatDB = user.getSalt();
        // pwd是前台传来的 经过了一次md5 拿它再次md5和db中的密码比较
        String calcPass = MD5Util.formPassToDBPass(pwd,slatDB);

        if(!calcPass.equals(dbPass)){
            //return CodeMsg.PASSWORD_ERROR;
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }


        //生成token 写入cookie 传给client
        String token = UUIDUtil.uuid();

        addCookie(response, token, user);


        return true;
    }

    /**
     * token取user 在redis中
     * @param response
     * @param token
     * @return
     */
    public MiaoshaUser getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        MiaoshaUser user = redisService.get(MiaoshaUserKey.token, token, MiaoshaUser.class);

        //延长有效期
        if(user != null) {
            addCookie(response, token, user);
        }
        return user;
    }

    private void addCookie(HttpServletResponse response, String token, MiaoshaUser user) {
        // 用token将用户信息存入到redis 后台生成session
        redisService.set(MiaoshaUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);
        // cookie有效期和session保持一致
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        //网站的根目录
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
