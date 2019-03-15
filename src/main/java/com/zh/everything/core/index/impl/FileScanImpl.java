package com.zh.everything.core.index.impl;

import com.zh.everything.config.EveryThingPlusConfig;
import com.zh.everything.core.index.FileScan;
import com.zh.everything.core.interceptor.FileInterceptor;
import com.zh.everything.core.interceptor.impl.FilePrintInterceptor;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @auther zh
 * @data 2019/3/15 19:32
 */
public class FileScanImpl implements FileScan {
    private EveryThingPlusConfig config = EveryThingPlusConfig.getInstance();
    private LinkedList<FileInterceptor> fileInterceptors = new LinkedList<>();
    @Override
    public void index(String path) {
        File file = new File(path);
        if (file.isFile()){
            if (config.getExcludePath().contains(file.getParent())){
                return;
            }else {

            }
        }else {
            if (config.getExcludePath().contains(path)){
                return;
            }else {
                File[] files = file.listFiles();
                if (files!= null) {
                    for (File file1 :files){
                        index(file1.getAbsolutePath());
                    }
                }
            }
        }

        //文件变Thing
        for (FileInterceptor interceptor : this.fileInterceptors){
            interceptor.apply(file);
        }
    }

    @Override
    public void interceptor(FileInterceptor interceptor) {
        this.fileInterceptors.add(interceptor);
    }


}
