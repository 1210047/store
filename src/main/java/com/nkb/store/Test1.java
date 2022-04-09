package com.nkb.store;

import org.springframework.util.DigestUtils;

public class Test1 {

    public static void main(String[] args) {
        String password="123";
        String salt = "9DCB3568-13CF-4956-9CA0-F0C6553BBE4E";
        Test1 test1 = new Test1();
        String new_password = test1.getMd5Password(password,salt);
        String password2 = test1.getMd5Password2(password,salt);

        System.out.println(new_password);
        System.out.println(password2);

    }

    private String getMd5Password(String password, String salt) {
        /*
         * 加密规则：
         * 1、无视原始密码的强度
         * 2、使用UUID作为盐值，在原始密码的左右两侧拼接
         * 3、循环加密3次
         * 串 + password + 串 -- md5加密
         */
        for (int i = 0; i < 3; i++) {
            // md5 加密算法的调用
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        // 返回加密之后的密码
        return password;
    }

    private String getMd5Password2(String password, String salt) {
        /*
         * 加密规则：
         * 1、无视原始密码的强度
         * 2、使用UUID作为盐值，在原始密码的左右两侧拼接
         * 3、循环加密3次
         * 串 + password + 串 -- md5加密
         */
        for (int i = 0; i < 3; i++) {
            // md5 加密算法的调用
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes());
        }
        // 返回加密之后的密码
        return password;
    }




}
