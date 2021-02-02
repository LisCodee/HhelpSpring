package com.hhelp.service;

import com.alibaba.fastjson.JSONArray;
import com.hhelp.domain.Ban;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-16 16:24
 */
public interface IBanService {

    JSONArray get_avoids(String illness_name);

    Ban get_avoid_detail(Integer id);
}
