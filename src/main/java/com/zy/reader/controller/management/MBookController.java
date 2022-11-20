package com.zy.reader.controller.management;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zy.reader.service.BookService;
import com.zy.reader.utils.ResponseUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

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
    @PostMapping("/upload")
    public Map upload(@RequestParam("img") MultipartFile file, HttpServletRequest request) throws IOException {
        //得到文件上传目录
        String uploadPath = request.getServletContext().getResource("/").getPath()+"/upload/";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String fileName = sdf.format(new Date());
        String subffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        file.transferTo(new File(uploadPath+fileName+subffix));
        Map result = new LinkedHashMap();
        result.put("errno",0);
        result.put("data",new String[]{"/upload/"+fileName+subffix});
        return result;
    }
}
