package com.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

public class time {
    public static void main(String[] agrs) throws Exception{
        //时间字符串转换格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = "2015-02-09 10:15:24";
        Date date = simpleDateFormat.parse(str);
        //Date date = new Date();
        int year = Integer.parseInt(String.format("%tY",date));
        int month = Integer.parseInt(String.format("%tm",date));
        int day = Integer.parseInt(String.format("%td",date));
        System.out.println("年："+year+"\t月："+month+"\t日："+day);
        int realMonth;
        switch (month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:realMonth = 31;break;
            case 4:
            case 6:
            case 9:
            case 11:realMonth = 30;break;
            case 2:realMonth = 28;break;
            default:    realMonth = 0; break;
        }
        System.out.println("月的天数："+realMonth);
        Date nowDate = new Date();
        int nowYear = Integer.parseInt(String.format("%tY",nowDate));
        int nowMonth = Integer.parseInt(String.format("%tm",nowDate));
        int nowDay = Integer.parseInt(String.format("%td",nowDate));

        if(year == nowYear && (month == nowMonth || month == nowMonth-1)){
            System.out.println("时间正确");
        }else{
            System.out.println("时间错误");
        }
    }
}
