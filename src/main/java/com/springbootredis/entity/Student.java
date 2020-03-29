package com.springbootredis.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类
 * 实现序列化接口，否则无法存入redis
 * @author norhtking
 */
@Data
public class Student implements Serializable {
    private Integer id;
    private String name;
    private Double score;
    private Date birthday;
}
