package com.zy.reader.controller;

import com.zy.reader.utils.ResponseUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    @PostMapping("/register")
    public ResponseUtils register(String username, String password, String nickname, 
                                  String vc, HttpServletRequest request){
        String verifyCode = (String) request.getSession().getAttribute("kaptchaVerifyCode");
        ResponseUtils resp = null;
        if (vc == null || verifyCode == null || !vc.equalsIgnoreCase(verifyCode)){
            resp = new ResponseUtils("VerifyCodeError","验证码错误");
        }else {
            resp = new ResponseUtils();
        }
            
            return  resp;
    }
}
