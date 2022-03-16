package com.ruochen.mp.mapper;

import com.ruochen.mp.pojo.User;

public interface UserMapper extends MyBaseMapper<User> {

    User findById(Long id);
}
