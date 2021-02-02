package com.hhelp.controller;

import com.alibaba.fastjson.JSONArray;
import com.hhelp.HhelpApplication;
import com.hhelp.anno.LoginToken;
import com.hhelp.domain.Agenda;
import com.hhelp.service.IAgendaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-16 20:56
 */

@RestController
@LoginToken
public class AgendaController {

    @Autowired
    private IAgendaService agendaService;
    protected static final Logger logger = LoggerFactory.getLogger(HhelpApplication.class);

    @PostMapping("/add_agenda")
    @LoginToken
    public void addAgenda(Agenda agenda){
        try {
            logger.info(agenda.toString());
            agendaService.addAgenda(agenda);
        }catch (Exception e){
            logger.error("addAgenda error!" + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/get_agenda")
    public JSONArray getAgenda(Integer user_id){
        JSONArray jsonArray = new JSONArray();
        try {
            List<Agenda> agendaByUserId = agendaService.getAgendaByUserId(user_id);
            jsonArray.addAll(agendaByUserId);
            logger.info(jsonArray.toJSONString());
        }catch (Exception e){
            logger.error("getAgenda error " + user_id + "/n" + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return jsonArray.isEmpty() ? null : jsonArray;
    }

    @PostMapping("/delete_agenda")
    @LoginToken
    public void deleteAgenda(Integer user_id, Integer agenda_id){
        try {
            agendaService.deleteAgenda(user_id, agenda_id);
            logger.info(agenda_id + "已删除");
        }catch (Exception e){
            logger.error("deleteAgenda error" + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
