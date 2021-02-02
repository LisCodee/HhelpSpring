package com.hhelp.service;

import com.hhelp.domain.Medicine;

import java.util.List;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-16 20:04
 */
public interface IFormulaService {

    List<Integer> getMedicineIdByIllnessName(String illnessName);

    List<Medicine> getMedicine(String illnessName);
}
