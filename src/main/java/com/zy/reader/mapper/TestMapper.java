package com.zy.reader.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.reader.entity.TestTable;
import org.apache.ibatis.annotations.Mapper;


public interface TestMapper extends BaseMapper<TestTable> {
    public void insertSample();
}
