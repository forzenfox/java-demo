package org.dylan.demo.api.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ClassScanUtil {

    /**
     * 扫描指定包路径下所有包含指定注解的类 * * @param packageName 包名 * @param apiClass 指定的注解 * @return Set
     */
    public static Set<Class> getClass4Annotation(String packageName)
            throws IOException, ClassNotFoundException {
        Set<Class> classSet = new HashSet<>();
        // 获取包的名字 并进行替换
        String packageDirName = packageName.replace('.', '/');
        // 定义一个枚举的集合 并进行循环来处理这个目录下的things
        Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
        // 循环迭代下去
        while (dirs.hasMoreElements()) {
            // 获取下一个元素
            URL url = dirs.nextElement();
            // 得到协议的名称
            String protocol = url.getProtocol();
            // 如果是以文件的形式保存在服务器上
            if ("file".equals(protocol)) {
                // 获取包的物理路径
                String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                // 以文件的方式扫描整个包下的文件 并添加到集合中
                File dir = new File(filePath);
                List<File> fileList = new ArrayList<>();
                fetchFileList(dir, fileList);
                for (File f : fileList) {
                    String fileName = f.getAbsolutePath();
                    if (fileName.endsWith(".class")) {
                        String noSuffixFileName = fileName.substring(8 + fileName.lastIndexOf("classes"), fileName.indexOf(".class"));
                        String filePackage = noSuffixFileName.replaceAll("\\\\", ".");
                        Class clazz = Class.forName(filePackage);
                        classSet.add(clazz);
                    }
                }
            }
        }
        return classSet;
    }


    /**
     * 查找所有的文件 * * @param dir 路径 * @param fileList 文件集合
     */
    private static void fetchFileList(File dir, List<File> fileList) {
        if (dir.isDirectory()) {
            for (File f : Objects.requireNonNull(dir.listFiles())) {
                fetchFileList(f, fileList);
            }
        } else {
            fileList.add(dir);
        }
    }
}
