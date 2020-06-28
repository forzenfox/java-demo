package NIO;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo {
    private final static String sys_path = System.getProperty("user.dir");

    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile(sys_path + "/jdk-demo/src/main/resources/test.properties", "rw");
        FileChannel inChannel = aFile.getChannel();

        // 创建一个48字节大小的缓存区
        ByteBuffer buf = ByteBuffer.allocate(48);

        // 读取数据到buffer
        // 读取数据到buffer(aFile, inChannel, buf);

        // BufferDemo(inChannel, buf);

        //通道间的数据传输
        通道间的数据传输(aFile);
    }

    private static void 通道间的数据传输(RandomAccessFile aFile) throws IOException {
        FileChannel src = aFile.getChannel();

        File temp1 = File.createTempFile("tem-", ".txt");
        RandomAccessFile bFile = new RandomAccessFile(temp1, "rw");
        FileChannel dst = bFile.getChannel();
        dst.transferFrom(src, 0, src.size());

        src.transferTo(0, src.size(), dst);
    }

    private static void 读取数据到buffer(RandomAccessFile aFile, FileChannel inChannel, ByteBuffer buf) throws IOException {
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {

            // 翻转buffer 将Buffer从写模式切换到读模式
            buf.flip();

            // 一个字节读取一次
            while (buf.hasRemaining()) {
                System.out.print(( char ) buf.get());
            }

            // 清除缓存区
            // clear():清空整个缓冲区
            // compact():只会清除已经读过的数据。任何未读的数据都被移到缓冲区的起始处，新写入的数据将放到缓冲区未读数据的后面。
            buf.clear();

            // 从通道中再次读取数据
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }

    private static void BufferDemo(FileChannel inChannel, ByteBuffer buf) throws IOException {
    /*
        Buffer 三个属性：
            capacity 表示Buffer的固定大小
            position 类比指针 表示当前位置；
                写模式下，初始为0，最大为capacity-1；
                读模式下，当将Buffer从写模式切换到读模式，position会被重置为0. 当从Buffer的position处读取数据时，position向前移动到下一个可读的位置
            limit 可理解为position的最大限制值
                写模式下，表示你最多能往Buffer里写多少数据，等于Buffer的capacity；
                读模式下，表示最多能读到多少数据，当切换Buffer到读模式时，limit会被设置成写模式下的position值；
        Buffer 的类型：
            ByteBuffer
            MappedByteBuffer
            CharBuffer
            DoubleBuffer
            FloatBuffer
            IntBuffer
            LongBuffer
            ShortBuffer
     */

        ByteBuffer buffer = ByteBuffer.allocate(48); // 分配空间 allocate()
        CharBuffer charBuffer = CharBuffer.allocate(1024);

        // 写数据到Buffer
        int bytes = inChannel.read(buffer);//从Channel写到Buffer
        buffer.put(Byte.parseByte("acb"));//通过put方法写Buffer

        // 切换Buffer的读写模式
        buffer.flip();

        // 从Buffer中读取数据
        int bytesWritten = inChannel.write(buf);//从Buffer中写数据到Channel
        byte b = buffer.get();//从buffer中读取数据

        buffer.rewind();//重设position位置为0，可以重新读取一次数据

        // 清除缓存区
        buffer.clear();//position将被设回0，limit被设置成 capacity的值;数据还在，并未清除
        buffer.compact();//将所有未读的数据拷贝到Buffer起始处。然后将position设到最后一个未读元素正后面。limit属性依然像clear()方法一样，设置成capacity。现在Buffer准备好写数据了，但是不会覆盖未读的数据。

        buffer.mark();//标记Buffer中的一个特定position
        buffer.reset();//恢复到标记的position

        /*
        当满足下列条件时，表示两个Buffer相等：
            有相同的类型（byte、char、int等）。
            Buffer中剩余的byte、char等的个数相等。
            Buffer中所有剩余的byte、char等都相同。
         */
        buffer.equals(buf);
    }
}
