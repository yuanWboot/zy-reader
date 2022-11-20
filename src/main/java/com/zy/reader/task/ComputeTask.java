package com.zy.reader.task;

import com.zy.reader.mapper.BookMapper;
import com.zy.reader.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ComputeTask {
    @Resource
    private BookService bookService;
    Logger logger = LoggerFactory.getLogger(ComputeTask.class);

    //秒 分 时 日 月 星期
    @Scheduled(cron = "0 * * * * ?")
    public void updateScore() {
        bookService.updateScore();
        logger.info("已更新所有图书评分");
    }
}
