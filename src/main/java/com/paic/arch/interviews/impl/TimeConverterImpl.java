package com.paic.arch.interviews.impl;

import com.paic.arch.interviews.TimeConverter;
import com.paic.arch.interviews.util.DateUtils;

import java.util.Date;

/**
* @author 黎鹏
* @description TimeConverterImpl
* @version 创建时间：2018年3月5日 下午10:30:54
*/
public class TimeConverterImpl implements TimeConverter {


    @Override
    public String convertTime(String aTime) {
        Date date = DateUtils.stringToDate(aTime, DateUtils.HH_mm_ss_sdf);
        int hour = DateUtils.getHour(date);
        int minute = DateUtils.getMinute(date);
        int second = DateUtils.getSecond(date);

        StringBuffer result = new StringBuffer();
        if ((second % 2) == 0) {
            result.append("Y\r\n");
        } else {
            result.append("O\r\n");
        }

        for (int i = 0; i <= (hour / 5); i++) {
            if ("24".equals(aTime.substring(0, 2))) {
                result.append("RRRR\r\n");
                result.append("RRRR");
                break;
            }
            if (i >= 4) {
                break;
            }
            if (i == (hour / 5) && i < 4 - 1) {
                for (int j = i; j < 4; j++) {
                    result.append("O");
                }
            } else {
                result.append("R");
            }
        }
        result.append("\r\n");


        if (hour % 5 == 0) {
            if (!"24".equals(aTime.substring(0, 2))) {
                result.append("OOOO\r\n");
            }
        } else {
            for (int i = 0; i < hour % 5; i++) {
                result.append("R");
            }
            for (int i = hour % 5; i < 4; i++) {
                result.append("O");
            }
            result.append("\r\n");
        }

        for (int i = 0; i <= (minute / 5); i++) {
            if (i >= 60 / 5 - 1) {
                break;
            }
            if (i == (minute / 5) && i < 11 - 1) {
                for (int j = i; j < 11; j++) {
                    result.append("O");
                }
            } else {
                if (((i + 1) * 5) % 15 == 0) {
                    result.append("R");
                } else {
                    result.append("Y");
                }
            }
        }
        result.append("\r\n");

        if (minute % 5 == 0) {
            result.append("OOOO");
        } else {
            for (int i = 0; i < minute % 5; i++) {
                result.append("Y");
            }
            for (int i = minute % 5; i < 4; i++) {
                result.append("O");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(new TimeConverterImpl().convertTime("00:00:00"));
        System.out.println("2----------");
        System.out.println(new TimeConverterImpl().convertTime("24:00:00"));
        System.out.println("3----------");
        System.out.println(new TimeConverterImpl().convertTime("23:59:59"));
        System.out.println("4----------");
        System.out.println(new TimeConverterImpl().convertTime("13:17:01"));
    }

}
