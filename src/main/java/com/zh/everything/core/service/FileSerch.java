package com.zh.everything.core.service;

import com.zh.everything.core.model.Condition;
import com.zh.everything.core.model.Thing;

import java.util.List;

/**
 * @auther zh
 * @data 2019/3/15 18:40
 */
public interface FileSerch {
    List<Thing> search(Condition condition);


}
