package com.hhelp.domain;

import lombok.Data;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-10 17:09
 */

@Data
public class Formula {
    private int id;
    private int medicine_id;
    private String illness_name;
    private int illness_id;
}
