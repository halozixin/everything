package com.zh.everything.core.model;

import lombok.Data;

/**
 * @auther zh
 * @data 2019/3/12 20:02
 */
@Data
public class Condition {
    private String name;

    private String fileType;

    private Integer limit;
    /*
    检索文件信息排序规则
     */
    private boolean orderByAsc;


}
