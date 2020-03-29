package com.springbootredis.controller;

import com.springbootredis.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @author norhtking
 */
@RestController
public class StudentHandler {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @RequestBody 将json数据转换成java对象
     * @param student
     * @return
     */
    @PostMapping("/set")
    public void set(@RequestBody Student student) {
        redisTemplate.opsForValue().set("student",student);
    }

    @GetMapping("/get/{key}")
    public Student get(@PathVariable String key) {
        return (Student) redisTemplate.opsForValue().get(key);
    }

    @DeleteMapping("/delete/{key}")
    public boolean delete(@PathVariable("key") String key){
        redisTemplate.delete(key);
        return redisTemplate.hasKey(key);
    }

    @GetMapping("/string")
    public String stringTest(){
        redisTemplate.opsForValue().set("str","Hello world");
        String str = (String) redisTemplate.opsForValue().get("str");
        return str;
    }

    @GetMapping("list")
    public List<String> listTest(){
        ListOperations<String,String> listOperations = redisTemplate.opsForList();
        listOperations.leftPush("list","hello");
        listOperations.leftPush("list","world");
        listOperations.rightPush("list","java");

        List<String> list = listOperations.range("list",0,2);
        return list;
    }

    @GetMapping("/set")
    public Set<String> setTest(){
        SetOperations<String,String> setOperations = redisTemplate.opsForSet();
        setOperations.add("set","Hello");
        setOperations.add("set","Hello");
        setOperations.add("set","world");
        setOperations.add("set","world");
        setOperations.add("set","java");
        setOperations.add("set","java");

        Set<String> set = setOperations.members("set");
        return set;
    }

    @GetMapping("/zset")
    public Set<String> zsetTest(){
        ZSetOperations<String,String> zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.add("zset","Hello",1);
        zSetOperations.add("zset","world",2);
        zSetOperations.add("zset","java",3);

        Set<String> set = zSetOperations.range("zset",0,2);
        return set;
    }

    @GetMapping("/hash")
    public void hashTest(){
        HashOperations<String,String,String> hashOperations = redisTemplate.opsForHash();
        hashOperations.put("key","hashKey","Hello");
        System.out.println(hashOperations.get("key","hashKey"));

    }
    public void test(){
        System.out.println("测试");
    }
}
