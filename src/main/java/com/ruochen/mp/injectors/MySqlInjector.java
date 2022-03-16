package com.ruochen.mp.injectors;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;

import java.util.ArrayList;
import java.util.List;

// 如果直接继承 AbstractSqlInjector 的话，原有的 BaseMapper 中的方法将失效
// 所以我们选择继承 DefaultSqlInjector 进行扩展
public class MySqlInjector extends DefaultSqlInjector {
    @Override
    public List<AbstractMethod> getMethodList() {
        List<AbstractMethod> list = new ArrayList<>();
        // 获取父类中的集合
        list.addAll(super.getMethodList());
        // 扩充自定义的方法
        list.add(new FindAll());
        return list;
    }
}
