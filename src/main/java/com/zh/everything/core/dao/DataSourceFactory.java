package com.zh.everything.core.dao;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
                    //jdbc:h2:filepath->存储到本地文件
                    //jdbc:h2://ip:port/databaseName->存储到服务器
                    druidDataSource.setUrl("jdbc:h2:"+path+ File.separator+"everything-plus");
                }
            }
        }
        return druidDataSource;
    }
    public static void initDatabase(){
        //获取数据源
        //获取sql语句
        try (InputStream in = DataSourceFactory.class.getClassLoader().getResourceAsStream("everything.sql");
        ){
            if (in == null){
                throw new RuntimeException("Not read init database script please check it");
            }
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while( (line = reader.readLine() )!= null){
                if (!line.startsWith("#")){
                    sb.append(line);
                }
            }
            String sql = sb.toString();
            //JDBC
            Connection connection = dataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();

            connection.close();
            statement.close();
        }catch (IOException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
