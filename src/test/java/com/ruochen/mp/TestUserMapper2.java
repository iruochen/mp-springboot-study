package com.ruochen.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruochen.mp.pojo.User;
import com.ruochen.mp.enums.SexEnum;
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
        user.setId(15L);

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
        user.setVersion(1);
        user.setSex(SexEnum.WOMAN);  // 使用枚举

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

    /**
     * 测试乐观锁
     */
    @Test
    public void testUpdateVersion() {
        User user = new User();
        user.setId(2L);  // 查询条件

        User userVersion = user.selectById();

        user.setAge(33);  // 更新数据
        user.setVersion(userVersion.getVersion());  // 当前版本信息

        boolean result = user.updateById();
        System.out.println("result => " + result);
    }

    /**
     * 测试全表更新，SQL分析器阻断效果
     */
    @Test
    public void testUpdateAll() {
        User user = new User();
        user.setAge(123);  // 更新数据

        boolean result = user.update(null);  // 全表更新
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

    @Test
    public void testSelectBySex() {
        User user = new User();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.ge("sex", SexEnum.WOMAN);  // 性别为女
        List<User> users = user.selectList(wrapper);
        for (User user1 : users) {
            System.out.println(user1);
        }
    }
}
