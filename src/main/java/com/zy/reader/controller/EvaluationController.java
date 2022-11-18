package com.zy.reader.controller;

import com.zy.reader.service.EvaluationService;
import com.zy.reader.utils.ResponseUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController 
@RequestMapping("/api/evaluation")
public class EvaluationController {
    @Resource
    private EvaluationService evaluationService;
    @GetMapping("/list")
    public ResponseUtils list(Long bookId){
        ResponseUtils resp = null;
        try{
            List<Map> maps = evaluationService.selectByBookId(bookId);
            resp = new ResponseUtils().put("list",maps);
        }catch (Exception e){
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());
        }
        return  resp;
    }
}
