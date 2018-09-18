package com.fhzz.springbootdemo.dao.master.mybatis;

import com.fhzz.springbootdemo.entity.master.OracleDemoTable;
import java.util.List;

public interface OracleDemoTableMapper {
    int deleteByPrimaryKey(String id);

    int insert(OracleDemoTable record);

    OracleDemoTable selectByPrimaryKey(String id);

    List<OracleDemoTable> selectAll();

    int updateByPrimaryKey(OracleDemoTable record);
    

}