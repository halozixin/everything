package com.zh.everything.core.interceptor.impl;

import com.zh.everything.core.interceptor.FileInterceptor;

import java.io.File;

/**
 * @auther zh
 * @data 2019/3/15 19:48
 */
public class FilePrintInterceptor implements FileInterceptor {

    @Override
    public void apply(File file) {
        System.out.println(file.getAbsoluteFile());
    }
}
