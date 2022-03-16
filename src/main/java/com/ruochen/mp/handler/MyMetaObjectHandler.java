package com.ruochen.mp.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入数据时填充
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 获取到 password 的值，进行判断，若为空，进行填充，若不为空，不做处理
        Object password = getFieldValByName("password", metaObject);
        if (null == password) {
            // 为空，填充
            setFieldValByName("password", "1111", metaObject);
        }
    }

    /**
     * 更新数据时填充
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
