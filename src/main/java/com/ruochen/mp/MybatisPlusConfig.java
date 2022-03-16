package com.ruochen.mp;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.ruochen.mp.plugins.MyInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.ruochen.mp.mapper") //设置mapper接口的扫描包
public class MybatisPlusConfig {

    @Bean  // 配置分页插件
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 注入自定义拦截器（插件）
     * @return
     */
    @Bean
    public MyInterceptor myInterceptor() {
        return new MyInterceptor();
    }
}
