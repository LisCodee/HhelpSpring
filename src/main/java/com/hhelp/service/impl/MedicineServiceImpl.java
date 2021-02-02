package com.hhelp.service.impl;

import com.hhelp.domain.Medicine;
import com.hhelp.service.IMedicineService;
import org.springframework.stereotype.Service;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-16 20:07
 */

@Service
public class MedicineServiceImpl implements IMedicineService {
    @Override
    public Medicine findMedicineById(Integer id) {
        return findMedicineById(id);
    }
}
