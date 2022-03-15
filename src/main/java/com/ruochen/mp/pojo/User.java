package com.ruochen.mp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_user")
public class User {

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
}