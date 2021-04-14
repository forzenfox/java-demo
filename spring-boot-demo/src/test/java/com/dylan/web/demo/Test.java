package com.dylan.web.demo;


import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ClassUtils;

@Slf4j
public class Test {
    
    @org.junit.Test
    public void test() {
        String fileName = "model_file_template.xlsx";
        log.info(ClassUtils.getDefaultClassLoader().getResource(fileName).getPath());
    }
}
