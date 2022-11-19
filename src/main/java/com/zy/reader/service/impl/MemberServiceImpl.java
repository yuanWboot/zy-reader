package com.zy.reader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.reader.entity.Member;
import com.zy.reader.mapper.MemberMapper;
import com.zy.reader.service.MemberService;
import com.zy.reader.service.excption.MemberException;
import com.zy.reader.utils.Md5Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class MemberServiceImpl implements MemberService {
    @Resource
    private MemberMapper memberMapper;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Member createMember(String username, String password, String nickname) {
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        List<Member> members = memberMapper.selectList(wrapper);
        if (members.size()>0){
            throw new MemberException("用户已存在");
        }
        Member member = new Member();
        member.setUsername(username);
       
        member.setNickname(nickname);
        member.setCreateTime(new Date());
        //生成随机四位盐值
        int salt = new Random().nextInt(1000) + 1000;
        member.setSalt(salt);
        String md5 = Md5Utils.md5Digest(password, salt);
        member.setPassword(md5);
        memberMapper.insert(member);
        return member;
    }

   
    @Override
    public Member checkLogin(String username, String password) {
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        Member member = memberMapper.selectOne(wrapper);
        if (member == null ){
            throw new MemberException("用户不存在");
        }
        String md5 = Md5Utils.md5Digest(password, member.getSalt());
        if (!md5.equals(member.getPassword())){
            throw new MemberException("您输入的密码有误");
        }
        
        return member;
    }

    @Override
    public Member selectById(Long memberId) {
        
        return memberMapper.selectById(memberId);
    }
}
