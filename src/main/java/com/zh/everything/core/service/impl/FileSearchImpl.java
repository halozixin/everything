package com.zh.everything.core.service.impl;

import com.zh.everything.core.dao.FileIndexDao;
import com.zh.everything.core.model.Condition;
import com.zh.everything.core.model.Thing;
import com.zh.everything.core.service.FileSerch;

import java.util.List;

/**
 * @auther zh
 * @data 2019/3/15 18:41
 */
public class FileSearchImpl implements FileSerch {

    private final FileIndexDao fIleIndexDao;

    public FileSearchImpl(FileIndexDao fIleIndexDao) {
        this.fIleIndexDao = fIleIndexDao;
    }

    @Override
    public List<Thing> search(Condition condition) {
        return this.fIleIndexDao.search(condition);
    }
}
