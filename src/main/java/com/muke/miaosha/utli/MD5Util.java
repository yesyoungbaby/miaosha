package com.muke.miaosha.utli;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author yesyoungbaby
 * @Title: MD5Util
 * @ProjectName miaosha
 * @Description: 两次md5
 * @date 2019/6/2611:10
 */
public class MD5Util {

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    private static final String salt = "1a2b3c4d";

    /**
     * 第一次md5 和前端对输入的pwd处理一模一样
     * 用户端输入铭文密码  md5（铭文+salt）完成后发给后端
     * @param inputPass
     * @return
     */
    public static String inputPassToFormPass(String inputPass) {
        String str = ""+salt.charAt(0)+salt.charAt(2) + inputPass +salt.charAt(5) + salt.charAt(4);
        System.out.println(str);
        return md5(str);
    }

    /**
     * 后台存储时 再次md5 md5（formPass+salt）
     * @param formPass  从前端传来的密码
     * @param salt
     * @return
     */
    public static String formPassToDBPass(String formPass, String salt) {
        String str = ""+salt.charAt(0)+salt.charAt(2) + formPass +salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDbPass(String inputPass, String saltDB) {
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDBPass(formPass, saltDB);
        return dbPass;
    }

    public static void main(String[] args) {

        //d3b1294a61a07da9b49b6e22b2cbd7f9
        //System.out.println(inputPassToFormPass("123456"));

        System.out.println(inputPassToDbPass("123456","1a2b3c4d"));
    }
}
