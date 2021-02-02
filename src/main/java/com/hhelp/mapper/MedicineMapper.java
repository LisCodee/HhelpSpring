package com.hhelp.mapper;

import com.hhelp.domain.Medicine;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: Jingxin Li
 * @Date: 2020-06-10 17:16
 */
@Mapper
public interface MedicineMapper {

    @Select("select * from medicine where id=#{id}")
    Medicine findMedicineById(Integer id);
}
