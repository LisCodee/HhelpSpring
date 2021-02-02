package com.hhelp.controller;

import com.alibaba.fastjson.JSONArray;
import com.hhelp.HhelpApplication;
import com.hhelp.anno.LoginToken;
import com.hhelp.domain.Medicine;
import com.hhelp.service.IFormulaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-16 20:11
 */

@RestController
public class FormulaController {

    @Autowired
    private IFormulaService formulaService;
    protected static final Logger logger = LoggerFactory.getLogger(HhelpApplication.class);

    @RequestMapping("/get_medicine")
    public JSONArray getMedicine(String illness_name){
        JSONArray jsonArray = new JSONArray();
        try {
            List<Medicine> medicines = formulaService.getMedicine(illness_name);
            jsonArray.addAll(medicines);
            logger.info(jsonArray.toJSONString());
        }catch (Exception e){
            logger.error("getMedicine ERROR!" + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return jsonArray.isEmpty() ? null:jsonArray;
    }
}
