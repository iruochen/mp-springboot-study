package com.ruochen.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruochen.mp.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(12L);  // 查询条件
        user.setAge(123);  // 更新数据

        boolean result = user.updateById();
        System.out.println("result => " + result);
    }

    @Test
    public void testDelete() {
        User user = new User();
        user.setId(12L);

        boolean result = user.deleteById();
        System.out.println("result => " + result);
    }

    @Test
    public void testSelect() {
        User user = new User();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.ge("age", 22);  // 大于等于22岁的用户
        List<User> users = user.selectList(wrapper);
        for (User user1 : users) {
            System.out.println(user1);
        }
    }
}
