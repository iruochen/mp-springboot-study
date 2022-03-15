package com.ruochen.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruochen.mp.pojo.User;

public interface UserMapper extends BaseMapper<User> {

    User findById(Long id);
}
