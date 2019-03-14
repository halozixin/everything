package com.zh.everything.core.dao;

import com.zh.everything.core.model.Condition;
import com.zh.everything.core.model.Thing;

import java.sql.Connection;
import java.util.List;

/**
 * @auther zh
 * @data 2019/3/12 21:33
 */
public interface FIleIndexDao {
    void insert(Thing thing);

    List<Thing> search(Condition condition);
}
