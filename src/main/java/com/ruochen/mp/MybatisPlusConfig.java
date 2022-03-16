package com.ruochen.mp;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.SqlExplainInterceptor;
import com.ruochen.mp.plugins.MyInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@MapperScan("com.ruochen.mp.mapper") //设置mapper接口的扫描包
public class MybatisPlusConfig {

    @Bean  // 配置分页插件
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 注入自定义拦截器（插件）
     *
     * @return
     */
    @Bean
    public MyInterceptor myInterceptor() {
        return new MyInterceptor();
    }

    // SQL分析插件：阻断全表更新、删除的操作（仅适用于开发环境，不适用于生产环境）
    @Bean
    public SqlExplainInterceptor sqlExplainInterceptor() {
        SqlExplainInterceptor sqlExplainInterceptor = new SqlExplainInterceptor();
        List<ISqlParser> list = new ArrayList<>();
        list.add(new BlockAttackSqlParser());  // 全表更新、删除阻断器
        sqlExplainInterceptor.setSqlParserList(list);
        return sqlExplainInterceptor;
    }
}
