package com.zy.reader.controller.management;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zy.reader.service.BookService;
import com.zy.reader.utils.ResponseUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/management/book")
public class MBookController {
    @Resource
    private BookService bookService;
    @GetMapping("/list")
    public ResponseUtils list(Integer page,Integer rows){
        if (page == null){
            page = 1;
        }
        if (rows == null){
            rows = 10;
        }
        ResponseUtils resp = null;
        try {
            IPage p = bookService.selectBookMap(page, rows);
            resp = new ResponseUtils().put("list",p.getRecords()).put("count",p.getTotal());
        }catch (Exception e){
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        return resp;
    }
}
