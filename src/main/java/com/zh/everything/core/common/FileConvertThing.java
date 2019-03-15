package com.zh.everything.core.common;

import com.zh.everything.core.model.FileType;
import com.zh.everything.core.model.Thing;

import java.io.File;

/**
 * @auther zh
 * @data 2019/3/15 18:53
 *
 * 辅助工具类
 */
public final class FileConvertThing {
    public static Thing convert(File file){
        Thing thing = new Thing();
        thing.setName(file.getName());
        thing.setPath(file.getAbsolutePath());
        thing.setDepth(computeFileDepth(file));
        thing.setFileType(computerFileType(file));
        return thing;
    }
    private static int computeFileDepth(File file){
        int depth = 0;
        String[] split = file.getAbsolutePath().split("\\\\");
        depth = split.length;
        return depth;
    }
    private static FileType computerFileType(File file){
        if (file.isDirectory()){
            return FileType.OTHER;
        }
        String fileName = file.getName();
        int index = fileName.lastIndexOf(".");
        if (index != -1 && index < fileName.length()-1){
            String fileType = file.getName().substring(index + 1);
            return FileType.lookup(fileType);
        }else {
            return FileType.OTHER;
        }
    }
}
