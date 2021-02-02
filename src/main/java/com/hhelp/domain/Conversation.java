package com.hhelp.domain;

import lombok.Data;

import java.util.Date;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-10 17:04
 */

@Data
public class Conversation {
    private int id;
    private int sender_id;
    private int receiver_id;
    private String content;
    private Date time;
}
