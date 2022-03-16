package com.ruochen.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruochen.mp.mapper.UserMapper;
import com.ruochen.mp.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        User user = this.userMapper.selectById(2L);
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

    @Test
    public void testDeleteByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_name", "zhangsan");
        map.put("password", "000");
        // 根据 map 删除数据，多条件之间是 and 关系
        int result = this.userMapper.deleteByMap(map);
        System.out.println("result => " + result);
    }

    @Test
    public void testDelete() {
        // 用法一：
        /*
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", "caocao")
                .eq("password", "123456");
        */

        // 用法二（推荐使用）：
        User user = new User();
        user.setPassword("123456");
        user.setUserName("caopi");

        QueryWrapper<User> wrapper = new QueryWrapper<>(user);

        // 根据条件删除
        int result = this.userMapper.delete(wrapper);
        System.out.println("result => " + result);
    }

    @Test
    public void testDeleteBatchIds() {
        // 根据id批量删除
        int result = this.userMapper.deleteBatchIds(Arrays.asList(9L, 10L));
        System.out.println("result => " + result);
    }

    @Test
    public void testSelectBatchIds() {
        // 根据ID批量查询数据
        List<User> users = this.userMapper.selectBatchIds(Arrays.asList(2L, 3L, 4L, 100L));
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectOne() {
        // 查询条件
        User user = new User();
        user.setUserName("lisi");
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        // 查询的数据超过一条时会抛出异常
        User u = this.userMapper.selectOne(wrapper);
        System.out.println(u);
    }

    @Test
    public void testSelectCount() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 条件：年龄大于20
        wrapper.gt("age", 20);
        // 根据条件查询数据条数
        Integer count = this.userMapper.selectCount(wrapper);
        System.out.println("count => " + count);
    }

    @Test
    public void testSelectList() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 设置查询条件
        wrapper.like("email", "@");
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    // 测试分页查询
    @Test
    public void testSelectPage() {
        Page<User> page = new Page<>(1, 1);  // 第一页，一条数据

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 设置查询条件
        wrapper.like("email", "@");
        IPage<User> iPage = this.userMapper.selectPage(page, wrapper);
        System.out.println("数据总条数：" + iPage.getTotal());
        System.out.println("数据总页数：" + iPage.getPages());
        System.out.println("当前页数：" + iPage.getCurrent());

        List<User> records = iPage.getRecords();
        for (User record : records) {
            System.out.println(record);
        }
    }

    /**
     * 测试自定义方法
     */
    @Test
    public void testFindById() {
        User user = this.userMapper.findById(2L);
        System.out.println(user);
    }

    @Test
    public void testAllEq() {
        Map<String, Object> params = new HashMap<>();
        params.put("name", "李四");
        params.put("age", "20");
        params.put("password", null);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE password IS NULL AND name = ? AND age = ?
        // wrapper.allEq(params);

        // SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE name = ? AND age = ?
        // wrapper.allEq(params, false);

        // param 中的值是否可以作为查询条件（与k进行比对）
        // SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE age = ?
        wrapper.allEq((k, v) -> (k.equals("age") || k.equals("id")), params);
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testEq() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE password = ? AND age >= ? AND name IN (?,?,?)
        wrapper.eq("password", "123456")
                .ge("age", 20)
                .in("name", "李四", "王五", "赵六");
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testLike() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE name LIKE ?
        // 参数： %四(String)
        wrapper.likeLeft("name", "四");
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testOrderByAgeDesc() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 按照年龄倒序排序
        wrapper.orderByDesc("age");
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testOr() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE name = ? OR age = ?
        wrapper.eq("name", "王五")
                .or().eq("age", 21);
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelect() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // SELECT id,name FROM tb_user WHERE name = ? OR age = ?
        wrapper.eq("name", "王五")
                .or().eq("age", 21)
                .select("id", "name");  // 指定查询的字段
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindAll() {
        List<User> userList = this.userMapper.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
