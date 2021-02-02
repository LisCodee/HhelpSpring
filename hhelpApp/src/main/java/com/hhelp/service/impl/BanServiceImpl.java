package com.hhelp.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.hhelp.domain.Ban;
import com.hhelp.mapper.BanMapper;
import com.hhelp.service.IBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-16 16:25
 */
@Service
public class BanServiceImpl implements IBanService {

    @Autowired
    private BanMapper banMapper;

    @Override
    public JSONArray get_avoids(String illness_name) {
        List<Ban> bans = banMapper.get_avoids(illness_name);
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(bans);
        return jsonArray;
    }

    @Override
    public Ban get_avoid_detail(Integer id) {
        return banMapper.getBanById(id);
    }
}
