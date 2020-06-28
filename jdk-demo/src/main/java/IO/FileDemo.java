package IO;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
    File对象既可以表示文件，也可以表示目录。
    构造File对象并不会报错，因为不会进行任何磁盘操作。
    当调用File对象的某些方法时，才会进行真正的磁盘操作。
 */
public class FileDemo {
    public static void main(String[] args) throws IOException {
        File f1 = new File("E:\\代码");
        File f2 = new File("E:\\代码\\git-demo\\pom.xml");
        File f3 = new File("E:\\代码\\不存在的目录");

        // isFile():判断是否是文件 isDirectory():判断是否是目录
        System.out.println(f1.isDirectory());
        System.out.println(f2.isDirectory());
        System.out.println(f3.isDirectory());
        System.out.println(f1.isFile());
        System.out.println(f2.isFile());
        System.out.println(f3.isFile());

        // boolean canRead()：是否可读；
        // boolean canWrite()：是否可写；
        // boolean canExecute()：是否可执行； 当File代表目录时，该方法表示能否列出它包含的文件和子目录
        // long length()：文件字节大小。

        // 创建文件 中间目录必须存在 否则报错：java.io.IOException: 系统找不到指定的路径
        // File newFile = new File("E:\\代码\\test.txt");
        // if (!newFile.exists()) {
        //     newFile.createNewFile();
        // }


        // 创建目录
        File newDir = new File("E:\\代码\\test\\test11");
        if (!newDir.exists()) {
            newDir.mkdirs(); // 创建目标目录 如果中间目录不存在 一并创建
            // newDir.mkdir();//创建目标目录 创建失败返回false
        }

        // 删除
        // newFile.delete();
        // newDir.delete();//立刻删除 当前目录必须为空才能删除成功

        // newDir.deleteOnExit();// JVM退出时自动删除 通过打断点调试

        // 创建临时文件
        File temp1 = File.createTempFile("tem-", ".txt");
        File temp2 = File.createTempFile("tem-", ".txt", newDir);//指定目录

        System.out.println(temp1.isFile());
        System.out.println(temp1.getAbsolutePath());
        System.out.println(temp2.isFile());
        System.out.println(temp2.getAbsolutePath());

        //遍历文件和目录
        File f = new File("C:\\Windows");
        File[] fs1 = f.listFiles(); // 列出所有文件和子目录
        printFiles(fs1);
        File[] fs2 = f.listFiles(new FilenameFilter() { // 仅列出.exe文件
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".exe"); // 返回true表示接受该文件
            }
        });
        printFiles(fs2);

        // Path
        Path p = Paths.get(".");
        System.out.println(p);
        System.out.println(p.toAbsolutePath());
        System.out.println(p.normalize());

        for (Path path : Paths.get("..").toAbsolutePath()) {
            System.out.println(path);
        }
    }

    static void printFiles(File[] files) {
        System.out.println("==========");
        if (files != null) {
            for (File f : files) {
                System.out.println(f);
            }
        }
        System.out.println("==========");
    }
}
