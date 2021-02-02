package com.hhelp.service.impl;

import com.hhelp.domain.Agenda;
import com.hhelp.mapper.AgendaMapper;
import com.hhelp.service.IAgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-16 20:56
 */

@Service
public class AgendaService implements IAgendaService {

    @Autowired
    private AgendaMapper agendaMapper;

    @Override
    public void addAgenda(Agenda agenda) {
        agendaMapper.addAgenda(agenda);
    }

    @Override
    public List<Agenda> getAgendaByUserId(Integer user_id) {
        return agendaMapper.getAgendaByUserId(user_id);
    }

    @Override
    public void deleteAgenda(Integer user_id, Integer agenda_id) {
        agendaMapper.deleteAgenda(user_id, agenda_id);
    }
}
