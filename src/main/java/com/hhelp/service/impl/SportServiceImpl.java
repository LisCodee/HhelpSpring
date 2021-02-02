package com.hhelp.service.impl;

import com.hhelp.domain.Sport;
import com.hhelp.mapper.SportMapper;
import com.hhelp.service.ISportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-16 16:49
 */

@Service
public class SportServiceImpl implements ISportService {

    @Autowired
    private SportMapper sportMapper;

    @Override
    public List<Sport> searchSports(String title) {
        return sportMapper.searchSports("%" + title + "%");
    }

    @Override
    public List<Sport> getSports(Integer li) {
        li = li==0?65535:li;
        return sportMapper.getSports(li);
    }

    @Override
    public Sport getSportById(Integer id) {
        return sportMapper.getSportById(id);
    }

    @Override
    public void addSport(Sport sport) {
        sportMapper.addSport(sport);
    }

    @Override
    public void favourSport(Sport sport) {
        sportMapper.favourSport(sport);
    }

    @Override
    public void clickSport(Sport sport) {
        sportMapper.clickSport(sport);
    }
}
