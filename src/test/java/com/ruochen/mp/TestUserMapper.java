package com.ruochen.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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

    @Test
    public void testUpdateById() {
        User user = new User();
        user.setId(1L);  // 条件：根据id更新
        user.setAge(22);  // 更新字段
        user.setPassword("5555");

        int result = this.userMapper.updateById(user);
        System.out.println("result => " + result);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setAge(33);  // 更新字段
        user.setPassword("888");

        QueryWrapper<User> wrapper = new QueryWrapper<>();

        // 匹配 user_name = zhangsan 的用户数据
        wrapper.eq("user_name", "zhangsan");

        // 根据条件更新
        int result = this.userMapper.update(user, wrapper);
        System.out.println("result => " + result);
    }

    @Test
    public void testUpdate2() {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        // 这里都是字段名，不是属性名
        wrapper.set("age", 55).set("password", "000")  // 更新的字段
                .eq("user_name", "zhangsan");  // 更新的条件


        // 根据条件更新
        int result = this.userMapper.update(null, wrapper);
        System.out.println("result => " + result);
    }

    @Test
    public void testDeleteById() {
        // 根据ID删除数据
        int result = this.userMapper.deleteById(8L);
        System.out.println("result => " + result);
    }
}
