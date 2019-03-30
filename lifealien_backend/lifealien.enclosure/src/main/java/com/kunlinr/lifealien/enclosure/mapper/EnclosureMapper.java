package com.kunlinr.lifealien.enclosure.mapper;

import com.kunlinr.lifealien.enclosure.po.Enclosure;
import com.kunlinr.lifealien.enclosure.po.EnclosureExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EnclosureMapper {
    long countByExample(EnclosureExample example);

    int deleteByExample(EnclosureExample example);

    int deleteByPrimaryKey(String id);

    int insert(Enclosure record);

    int insertSelective(Enclosure record);

    List<Enclosure> selectByExampleWithBLOBs(EnclosureExample example);

    List<Enclosure> selectByExample(EnclosureExample example);

    Enclosure selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Enclosure record, @Param("example") EnclosureExample example);

    int updateByExampleWithBLOBs(@Param("record") Enclosure record, @Param("example") EnclosureExample example);

    int updateByExample(@Param("record") Enclosure record, @Param("example") EnclosureExample example);

    int updateByPrimaryKeySelective(Enclosure record);

    int updateByPrimaryKeyWithBLOBs(Enclosure record);

    int updateByPrimaryKey(Enclosure record);
}