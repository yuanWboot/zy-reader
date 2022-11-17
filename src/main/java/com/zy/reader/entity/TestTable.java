package com.zy.reader.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("test")
public class TestTable {
    //说明id是表的主键，同时数据新增时使用表自带的新增主键生成编号
    @TableId(type = IdType.AUTO)
    @TableField("id")  //说明属性对应哪个字段
    private Integer id;
    @TableField("context")   //如果字段名与属性名相同时@TableFile可以忽略
    private String context;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
