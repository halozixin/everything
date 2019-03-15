package com.zh.everything.config;

import lombok.Getter;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
 * @auther zh
 * @data 2019/3/15 19:04
 */
@Getter
public class EveryThingPlusConfig {
    private EveryThingPlusConfig(){}
    private static volatile EveryThingPlusConfig config;


    /**
     * 创建索引的路径
     */
    private Set<String> includePath = new HashSet<>();
    /**
     * 排除路径
     */
    private Set<String> excludePath = new HashSet<>();
    public static EveryThingPlusConfig getInstance(){
        if (config == null){
            synchronized (EveryThingPlusConfig.class){
                if (config == null){
                    config = new EveryThingPlusConfig();
                    //遍历的
                    //排除的
                    FileSystem fileSystem = FileSystems.getDefault();
                    Iterable<Path> iterable = fileSystem.getRootDirectories();
                    iterable.forEach(path -> config.getExcludePath().add(path.toString()));
                    String osName = System.getProperty("os.name");
                    if (osName.startsWith("Windows")){
                        config.getExcludePath().add("C:\\Windows");
                        config.getExcludePath().add("C:\\ProgramData");
                        config.getExcludePath().add("C:\\Program Files");
                        config.getExcludePath().add("C:\\Program Files (x86)");
                    }else {
                        config.getExcludePath().add("/tmp");
                    }
                }
            }
        }
        return config;
    }
}
