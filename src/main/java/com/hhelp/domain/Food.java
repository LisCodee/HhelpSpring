package com.hhelp.domain;

import lombok.Data;

import java.util.Calendar;
import java.util.Date;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-10 17:07
 */

@Data
public class Food {
    private int id;
    private String title;
    private String introduction;
    private String content;
    private int pub_user;
    private Date pub_time;
    private int click_num;
    private int favour_num;
}
