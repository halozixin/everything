package com.zh.everything.core.dao.impl;

import com.zh.everything.core.dao.FIleIndexDao;
import com.zh.everything.core.model.Condition;
import com.zh.everything.core.model.FileType;
import com.zh.everything.core.model.Thing;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther zh
 * @data 2019/3/12 21:34
 */
public class FileIndexDaoIndex implements FIleIndexDao {

    private final DataSource dataSource;

    public FileIndexDaoIndex(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(Thing thing) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            String sql = "insert into file_index(name,path,depth,file_type) values (?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, thing.getName());
            statement.setString(2, thing.getPath());
            statement.setInt(3, thing.getDepth());
            statement.setString(4, thing.getFileType().name());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Thing> search(Condition condition) {
        List<Thing> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            String sql = "select name,path,depth,file_type from file_index  ";
            //文件类型 =
            //文件名称 like
            StringBuilder sb = new StringBuilder();
            sb.append(sql);
            sb.append("where name lile '%").append(condition.getName()).append("%' ");
            if (condition.getFileType() !=null){
                sb.append(" and file_type = '").append(condition.getFileType().toUpperCase()).append("' ");
            }

            sb.append(" order by depth ").append(condition.isOrderByAsc() ? "asc ": "desc" );
            sb.append(" limit ").append(condition.getLimit()).append(" offset 0");
            statement = connection.prepareStatement(sb.toString());


            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Thing thing = new Thing();
                thing.setName(resultSet.getString("name"));
                thing.setPath(resultSet.getString("path"));
                thing.setDepth(resultSet.getInt("depth"));
                String file_type = resultSet.getString("file_type");
                thing.setFileType(FileType.lookupByName(file_type));
                list.add(thing);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

}



