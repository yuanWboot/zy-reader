package com.zy.reader.service;

import com.zy.reader.entity.Member;

public interface MemberService {
    public Member createMember(String username , String password, String nickname);
}
