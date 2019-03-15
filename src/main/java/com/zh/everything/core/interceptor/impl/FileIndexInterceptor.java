package com.zh.everything.core.interceptor.impl;

import com.zh.everything.core.common.FileConvertThing;
import com.zh.everything.core.dao.FileIndexDao;
import com.zh.everything.core.interceptor.FileInterceptor;
import com.zh.everything.core.model.Thing;

import java.io.File;

/**
 * @auther zh
 * @data 2019/3/15 20:08
 */
public class FileIndexInterceptor implements FileInterceptor {
    private FileIndexDao fileIndexDao;

    public FileIndexInterceptor(FileIndexDao fileIndexDao) {
        this.fileIndexDao = fileIndexDao;
    }

    @Override
    public void apply(File file) {
        Thing thing = FileConvertThing.convert(file);
        System.out.println("Thing ==> "+thing);
        fileIndexDao.insert(thing);
    }
}
