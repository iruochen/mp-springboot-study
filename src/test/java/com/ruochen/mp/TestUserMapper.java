package com.ruochen.mp;

import com.ruochen.mp.mapper.UserMapper;
import com.ruochen.mp.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserMapper {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        User user = new User();
        user.setMail("2@2.com");
        user.setAge(23);
        user.setUserName("caopi");
        user.setName("曹丕");
        user.setPassword("123456");

        int result = this.userMapper.insert(user);  // result：数据库受影响的行数
        System.out.println("result => " + result);

        // 获取自增长后id值，自增长后的id值会回填到user对象中
        System.out.println("id => " + user.getId());
    }

    @Test
    public void testSelectById() {
        User user = this.userMapper.selectById(1L);
        System.out.println(user);
    }
}
