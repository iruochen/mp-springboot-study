package com.ruochen.mp.injectors;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.AbstractSqlInjector;

import java.util.ArrayList;
import java.util.List;

public class MySqlInjector extends AbstractSqlInjector {
    @Override
    public List<AbstractMethod> getMethodList() {
        List<AbstractMethod> list = new ArrayList<>();
        list.add(new FindAll());
        return list;
    }
}
