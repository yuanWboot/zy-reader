package com.zy.reader.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MemberServiceTest {

    @Resource
    private MemberService memberService;

    //用户已存在的情况
    @Test
    public void createMember1() {
        memberService.createMember("imooc_1","123456","imooc_1");
    }
    //用户未存在的情况
    @Test
    public void createMember2() {
        memberService.createMember("imooc_666","123456","imooc_666");
    }
}