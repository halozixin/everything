package com.zh.everything.core.dao;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.io.File;
import java.io.InputStream;

/**
 * @auther zh
 * @data 2019/3/12 20:37
 */
public final class DataSourceFactory {
    private static volatile DruidDataSource druidDataSource;
    private DataSourceFactory(){

    }
    public static DataSource dataSource(){
        if (druidDataSource == null){
            synchronized (DataSourceFactory.class){
                if (druidDataSource == null){
                    druidDataSource = new DruidDataSource();
                    druidDataSource.setDriverClassName("org.h2.Driver");
                    //h2是嵌入式，数据以本地文件存储，只提供url
                    //获取当前路径
                    String path = System.getProperty("user.dir");
                    druidDataSource.setUrl("jdbc:h2:"+path+ File.separator+"everything-plus");
                }
            }
        }
        return druidDataSource;
    }
    public static void initDatabase(){
        //获取数据源
        //获取sql语句
        InputStream stream = DataSourceFactory.class.getClassLoader().getResourceAsStream("everything.sql");
    }
}
