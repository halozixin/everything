package com.zh.everything.core.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 文件类型
 * @auther zh
 * @data 2019/3/12 19:53
 */
public enum  FileType {
    IMG("png","jpeg","jpe","gif"),
    DOC("ppt","doc","docx","pptx","pdf"),
    BIN("exe","jar","sh","msi"),
    ACHIVE("zip","rar"),
    OTHER("*");
    private Set<String> extend = new HashSet<>();
    private FileType(String... extend){
        this.extend.addAll(Arrays.asList(extend));
    }

    public static FileType lookup(String extend){
        for (FileType fileType : FileType.values()){
            if (fileType.extend.contains(extend)){
                return fileType;
            }
        }
        return FileType.OTHER;
    }


}
