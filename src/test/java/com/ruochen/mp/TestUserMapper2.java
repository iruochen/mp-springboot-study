package com.ruochen.mp;

import com.ruochen.mp.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserMapper2 {

    @Test
    public void testSelectById() {
        // 隐式使用 UserMapper，不能删除 UserMapper
        User user = new User();
        user.setId(2L);

        User user1 = user.selectById();
        System.out.println(user1);
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setUserName("liubei");
        user.setPassword("123");
        user.setAge(22);
        user.setName("刘备");
        user.setMail("liubei@1.com");
        
        // 调用AR的insert方法插入数据
        boolean result = user.insert();
        System.out.println("result => " + result);
    }
}