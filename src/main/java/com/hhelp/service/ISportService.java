package com.hhelp.service;

import com.hhelp.domain.Sport;

import java.util.List;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-16 16:48
 */
public interface ISportService {

    List<Sport> searchSports(String title);

    List<Sport> getSports(Integer li);

    Sport getSportById(Integer id);

    void addSport(Sport sport);

    void favourSport(Sport sport);

    void clickSport(Sport sport);
}
