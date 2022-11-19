package com.zy.reader.controller;

import com.zy.reader.entity.Member;
import com.zy.reader.entity.MemberReadState;
import com.zy.reader.service.MemberService;
import com.zy.reader.utils.ResponseUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    @Resource
    private MemberService memberService;

    @PostMapping("/register")
    public ResponseUtils register(String username, String password, String nickname,
                                  String vc, HttpServletRequest request) {
        String verifyCode = (String) request.getSession().getAttribute("kaptchaVerifyCode");
        ResponseUtils resp = null;
        if (vc == null || verifyCode == null || !vc.equalsIgnoreCase(verifyCode)) {
            resp = new ResponseUtils("VerifyCodeError", "验证码错误");
        } else {
            try {
                Member member = memberService.createMember(username, password, nickname);
                resp = new ResponseUtils();

            } catch (Exception e) {
                e.printStackTrace();
                resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
            }
        }
           
        return resp;
    }
    
    @PostMapping("/check_login")
    public ResponseUtils checkLogin(String username,String password,String vc,HttpServletRequest request){
            String verifyCode = (String) request.getSession().getAttribute("kaptchaVerifyCode");
            ResponseUtils resp ;
            if (vc == null || verifyCode == null || !vc.equalsIgnoreCase(verifyCode)) {
                resp = new ResponseUtils("VerifyCodeError", "验证码错误");
            } else {
                resp = new ResponseUtils();
                //验证码比对成功后进行用户登录查询
                try {
                    Member member = memberService.checkLogin(username, password);
                    member.setPassword(null);
                    member.setSalt(null);
                    resp = new ResponseUtils().put("member",member);
                } catch (Exception e) {
                    e.printStackTrace();
                    resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
                }
            }

            return resp;
    }
    
    @GetMapping("/select_by_id")
    public ResponseUtils selectById(Long memberId){
        ResponseUtils resp = null;
        try{
            Member member = memberService.selectById(memberId);
            resp = new ResponseUtils().put("member",member);
        }catch (Exception e){
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        return  resp;
    }
    
    @GetMapping("/select_read_state")
    public  ResponseUtils selectMemberReadState(Long memberId,Long bookId){
        ResponseUtils resp = null;
        try{
            MemberReadState memberReadState = memberService.selectMemberReadState(memberId, bookId);
            resp = new ResponseUtils().put("readState",memberReadState);
        }catch (Exception e){
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        return  resp;
    }
    
    @PostMapping("/update_read_state")
    public ResponseUtils updateReadState(Long memberId,Long bookId,Integer readState){
        ResponseUtils resp = null;
        try{
            MemberReadState memberReadState = memberService.updateMemberReadState(memberId, bookId, readState);
            resp = new ResponseUtils().put("readState",memberReadState);
        }catch (Exception e){
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        return  resp;
    }
}
