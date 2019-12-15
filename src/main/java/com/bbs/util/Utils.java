package com.bbs.util;

public class Utils {

    //随机生成指定长度的id
    public static Long randomId(int length){
        Long zero = (long)Math.pow(10,length - 1);
        return (long)(Math.random()*9*zero + zero);
    }
}
