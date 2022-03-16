package com.ruochen.mp.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// @TableName("tb_user")
// extends Model<T> --- ActiveRecord
public class User extends Model<User> {

    // 设置ID策略-自增长
    // @TableId(type = IdType.AUTO)
    private Long id;
    private String userName;

    @TableField(select = false)  // 查询时不返回该字段的值
    private String password;
    private String name;
    private Integer age;

    @TableField(value = "email")  // 指定数据库表中字段名
    private String mail;

    @TableField(exist = false)  // 表示此字段在数据库表中不存在
    private String address; // 在数据库表中不存在

    @Version  // 乐观锁版本字段
    /*
        数据库操作
        ALTER TABLE `tb_user`
        ADD COLUMN `version` int(10) NULL AFTER `email`;
        UPDATE `tb_user` SET `version`='1';
     */
    private Integer version;
}