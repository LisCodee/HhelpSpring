package com.hhelp.service;

import com.hhelp.domain.Agenda;

import java.util.List;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-16 20:55
 */
public interface IAgendaService {

    void addAgenda(Agenda agenda);

    List<Agenda> getAgendaByUserId(Integer user_id);

    void deleteAgenda(Integer user_id, Integer agenda_id);
}
