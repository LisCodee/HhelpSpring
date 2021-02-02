package com.hhelp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-10 17:16
 */
@Mapper
public interface FormulaMapper {

    @Select("select medicine_id from formula where illness_name like #{illness_name}")
    List<Integer> findMedicineIdByIllnessName(String illness_name);
}
