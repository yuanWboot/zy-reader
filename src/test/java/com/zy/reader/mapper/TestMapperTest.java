package com.zy.reader.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.reader.entity.TestTable;
import com.zy.reader.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestMapperTest {
    @Resource
    private TestMapper testMapper;

    @Test
    public void insertSample() {
        testMapper.insertSample();
    }

    @Test
    public void testInsert() {
        TestTable test = new TestTable();
        test.setContent("cccccccccc");
        int count = testMapper.insert(test);
        System.out.println("本次新增" + count + "条数据");
    }

    @Test
    public void testUpdate() {
        TestTable test = new TestTable();
        test.setId(47);
        test.setContent("47号数据更新");
        testMapper.updateById(test);
    }

    @Test
    public void testDelete() {
        testMapper.deleteById(47);
    }

    @Test
    public void testSelectById(){
        TestTable testTable = testMapper.selectById(50);
        System.out.println(testTable.getId()+"===="+testTable.getContent());
    }

    @Test
    public void testSelectList(){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.gt("id", 50);
        wrapper.eq("content", "测试内容123");
        List<TestTable> list = testMapper.selectList(wrapper);
        System.out.println(list.size());
    }

    @Test
    public void testPagination(){
        IPage page = new Page(2, 2);
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.gt("id", 50);
        wrapper.eq("content", "测试内容123");
        page = testMapper.selectPage(page, wrapper);
        System.out.println("总页数：" + page.getPages());
        System.out.println("总记录数：" + page.getTotal());
        System.out.println("当前页数据：" + page.getRecords());
    }

}