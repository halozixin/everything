package com.zh.everything.core.index;

import com.zh.everything.core.interceptor.FileInterceptor;
import com.zh.everything.core.model.Thing;

/**
 * @auther zh
 * @data 2019/3/15 18:51
 */
public interface FileScan {
    void index(String path);

    void interceptor(FileInterceptor interceptor);
}
