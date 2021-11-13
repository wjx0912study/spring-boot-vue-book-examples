package com.alan.hrsys.test_redis;

import com.alan.hrsys.test_redis.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TestRedisJson {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

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
    @Test
    void setEmps() {
        Employee emp1 = new Employee();
        emp1.setId(1);
        emp1.setName("李静");
        emp1.setGender("女");
        emp1.setAge(25);
        Employee emp2 = new Employee();
        emp2.setId(2);
        emp2.setName("王正");
        emp2.setGender("男");
        emp2.setAge(30);
        List<Employee> list = new ArrayList<>();
        list.add(emp1);
        list.add(emp2);
        redisTemplate.opsForList().leftPushAll("emps2", list);
    }

    @Test
    void getEmps() {
        Employee emp = (Employee) redisTemplate.opsForList().index("emps2",0);
        System.out.println(emp.getName());
    }

}
