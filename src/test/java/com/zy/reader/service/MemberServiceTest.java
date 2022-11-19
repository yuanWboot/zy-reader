package com.zy.reader.service;

import com.zy.reader.entity.Member;
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
        Member member = memberService.createMember("imooc_1", "123456", "imooc_1");
        System.out.println(member);
    }
    //用户未存在的情况
    @Test
    public void createMember2() {
        Member member = memberService.createMember("imooc_666", "123456", "imooc_666");
        System.out.println(member);
    }

    //模拟用户名均正确
    @Test
    public void checkLogin1() {
        Member member = memberService.checkLogin("imooc_1", "123456");
        System.out.println(member);
    }

    //模拟用户不存在
    @Test
    public void checkLogin2() {
        Member member = memberService.checkLogin("imooc_1k", "123456");
        System.out.println(member);
    }
    //模拟密码不正确
    @Test
    public void checkLogin3() {
        Member member = memberService.checkLogin("imooc_1", "12‘’3456");
        System.out.println(member);
    }
}