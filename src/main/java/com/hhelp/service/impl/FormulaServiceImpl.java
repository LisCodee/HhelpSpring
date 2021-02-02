package com.hhelp.service.impl;

import com.hhelp.domain.Medicine;
import com.hhelp.mapper.FormulaMapper;
import com.hhelp.mapper.MedicineMapper;
import com.hhelp.service.IFormulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-16 20:05
 */

@Service
public class FormulaServiceImpl implements IFormulaService {

    @Autowired
    private FormulaMapper formulaMapper;

    @Autowired
    private MedicineMapper medicineMapper;

    @Override
    public List<Integer> getMedicineIdByIllnessName(String illnessName) {
        return formulaMapper.findMedicineIdByIllnessName("%" + illnessName + "%");
    }

    @Override
    public List<Medicine> getMedicine(String illnessName) {
        List<Medicine> medicines = new ArrayList<>();
        List<Integer> mids = formulaMapper.findMedicineIdByIllnessName(illnessName);
        for(int mid: mids){
            medicines.add(medicineMapper.findMedicineById(mid));
        }
        return medicines;
    }
}
