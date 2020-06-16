package 集合;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/*
    JDK<=8 properties文件编码是ASCII编码（ISO8859-1）
    JDK>=9 .properties文件可以使用UTF-8编码
 */
public class PropertiesDemo {
    public static void main(String[] args) throws Exception {
        String f = System.getProperty("user.dir") + "/empty-model/src/main/resources/test.properties";

        // 读取配置文件
        Properties read = new Properties();
        read.load(new FileInputStream(f.replaceAll("//", File.separator)));// "//"为"/"转义结果

        String filepath = read.getProperty("last_open_file");
        String interval = read.getProperty("auto_save_interval", "120");

        System.out.println(filepath);
        System.out.println(interval);

        // 写入配置文件 写入是直接覆盖的操作
        // Properties write = new Properties();
        read.setProperty("url", "http://www.liaoxuefeng.com");
        read.setProperty("language", "Java");
        read.store(new FileOutputStream(f), "this a comment for write properties");
    }
}
