package com.alan.hrsys.test_redis;

import com.alan.hrsys.test_redis.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TestRedisEmp {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void setList() {
        redisTemplate.opsForList().leftPush("emps", "王正");
        String[] strs = {"李静", "陈建"};
        redisTemplate.opsForList().leftPushAll("emps", strs);
        List<String> list = new ArrayList<>();
        list.add("赵杰");
        list.add("孙悦");
        redisTemplate.opsForList().leftPushAll("emps", list);
    }
    @Test
    void getList() {
        String emp = (String)redisTemplate.opsForList().index("emps",0);
        System.out.println(emp);
    }
    @Test
    void setEmp() {
        Employee emp1 = new Employee();
        emp1.setId(1);
        emp1.setName("李静");
        emp1.setGender("女");
        emp1.setAge(25);
        redisTemplate.opsForValue().set("emp1", emp1);
    }

    @Test
    void getEmp() {
        Employee emp = (Employee) redisTemplate.opsForValue().get("emp1");
        System.out.println(emp.getName());
    }


}
