package com.zh.everything.core.model;

import lombok.Data;

/**
 * @auther zh
 * @data 2019/3/12 20:00
 */
@Data//getter setter toString生成
public class Thing {
    private String name;
    /*
    文件路径
     */
    private String path;
    /*
    文件深度
     */
    private Integer depth;

    private FileType fileType;

}
