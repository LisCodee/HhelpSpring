package com.hhelp.service;

import com.hhelp.domain.Medicine;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-16 20:07
 */
public interface IMedicineService {

    Medicine findMedicineById(Integer id);
}
