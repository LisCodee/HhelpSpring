package com.hhelp.domain;

import lombok.Data;

import java.util.Calendar;
import java.util.Date;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-10 17:09
 */

@Data
public class Agenda {
    private int id;
    private int user_id;
    private String title;
    private Date start_time;
    private Date end_time;
    private String repeated;
    private int effective;
}
