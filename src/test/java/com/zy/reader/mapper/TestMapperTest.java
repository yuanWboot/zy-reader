package com.zy.reader.mapper;

import com.zy.reader.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestMapperTest {
    @Resource
    private TestService testService;
    @Test
    public void insertSample() {
        testService.batchImport();
    }
}