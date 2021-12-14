package com.liuting.androidspecial.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.pudutech.base.Pdlog;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @Date: 2019-10-31 19:45
 */
public class ZipFile {

    /**
     * 要压缩的图片文件所在所存放位置
     */
    public static String JPG_FILE_PATH = "/Users/yuanlai/test/123.jpg";

    /**
     * zip压缩包所存放的位置
     */
    public static String ZIP_FILE = "/Users/yuanlai/test/hu.zip";

    /**
     * 所要压缩的文件
     */
    public static File JPG_FILE = null;

    /**
     * 文件大小
     */
    public static long FILE_SIZE = 0;

    /**
     * 文件名
     */
    public static String FILE_NAME = "";

    /**
     * 文件后缀名
     */
    public static String SUFFIX_FILE = "";

    static {

        File file = new File(JPG_FILE_PATH);

        JPG_FILE = file;

        FILE_NAME = file.getName();

        FILE_SIZE = file.length();

        SUFFIX_FILE = file.getName().substring(file.getName().indexOf('.'));
    }

    /**
     * Version 1
     * 没有使用Buffer
     */
    public static void zipFileNoBuffer() {
        File zipFile = new File(ZIP_FILE);
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile))) {
            //开始时间
            long beginTime = System.currentTimeMillis();

            for (int i = 0; i < 10; i++) {
                try (InputStream input = new FileInputStream(JPG_FILE)) {
                    zipOut.putNextEntry(new ZipEntry(FILE_NAME + i));
                    int temp = 0;
                    while ((temp = input.read()) != -1) {
                        zipOut.write(temp);
                    }
                }
            }
            System.out.println((System.currentTimeMillis() - beginTime) / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Version 2
     * 使用了Buffer
     */
    public static void zipFileBuffer() {
        //开始时间
        long beginTime = System.currentTimeMillis();
        File zipFile = new File(ZIP_FILE);
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(zipOut)) {
            for (int i = 0; i < 100; i++) {
                try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(JPG_FILE))) {
                    zipOut.putNextEntry(new ZipEntry(FILE_NAME + i));
                    int temp = 0;
                    while ((temp = bufferedInputStream.read()) != -1) {
                        bufferedOutputStream.write(temp);
                    }
                }
            }
            System.out.println((System.currentTimeMillis() - beginTime) / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 100张 2.2M ，8秒
     */
    public static void zipFileChannelBuffer(ArrayList<File> srcList, String dest) {
        //开始时间
        long beginTime = System.currentTimeMillis();
        File zipFile = new File(dest);
        try (ZipOutputStream zipOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));
             WritableByteChannel writableByteChannel = Channels.newChannel(zipOut)) {
            for (int i = 0; i < srcList.size(); i++) {
                try (FileChannel fileChannel = new FileInputStream(srcList.get(i)).getChannel()) {
                    zipOut.putNextEntry(new ZipEntry(srcList.get(i).getName()));
                    fileChannel.transferTo(0, srcList.get(i).length(), writableByteChannel);
                }
            }
            System.out.println((System.currentTimeMillis() - beginTime) / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Version 3 使用Channel
     * 100张 2.2M ，11秒
     */
    public static void zipFileChannel(ArrayList<File> srcList, String dest) {
        File zipFile = new File(dest);
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
             WritableByteChannel writableByteChannel = Channels.newChannel(zipOut)) {
            for (int i = 0; i < srcList.size(); i++) {
                zipFile(srcList.get(i), null,zipOut, writableByteChannel);
            }
        } catch (Exception e) {
            Pdlog.d("zipcomprss","压缩文件时出现异常");
            e.printStackTrace();
        }
    }

    public static void zipFile(File file, String zipFolderName,ZipOutputStream zipOut, WritableByteChannel writableByteChannel) throws IOException {
            if (file.isDirectory()) {
                String[] fileList = file.list();
                ZipEntry zipEntry = new ZipEntry(file.getName() + File.separator);
                zipOut.putNextEntry(zipEntry);
                for (int j = 0; j < fileList.length; j++) {
                    zipFile(new File(file.getPath()+File.separator+fileList[j]),zipEntry.getName(), zipOut, writableByteChannel);
                }
            } else {
                FileChannel fileChannel = new FileInputStream(file).getChannel();
                if(zipFolderName!=null){
                    zipOut.putNextEntry(new ZipEntry(zipFolderName+file.getName()));
                }else{
                    zipOut.putNextEntry(new ZipEntry(file.getName()));
                }
                fileChannel.transferTo(0, file.length(), writableByteChannel);
            }

    }

    /**
     * 100张 2.2M ，10秒
     */
    public static void zipFileMapBuffer() {
        //开始时间
        long beginTime = System.currentTimeMillis();
        File zipFile = new File(ZIP_FILE);
        try (ZipOutputStream zipOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));
             WritableByteChannel writableByteChannel = Channels.newChannel(zipOut)) {
            for (int i = 0; i < 100; i++) {
                zipOut.putNextEntry(new ZipEntry(i + SUFFIX_FILE));

                //内存中的映射文件
                MappedByteBuffer mappedByteBuffer = new RandomAccessFile(JPG_FILE_PATH, "r").getChannel()
                        .map(FileChannel.MapMode.READ_ONLY, 0, FILE_SIZE);

                writableByteChannel.write(mappedByteBuffer);
            }
            System.out.println((System.currentTimeMillis() - beginTime) / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Version 4 使用Map映射文件
     * 100张 2.2M ，12秒
     */
    public static void zipFileMap() {
        //开始时间
        long beginTime = System.currentTimeMillis();
        File zipFile = new File(ZIP_FILE);
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
             WritableByteChannel writableByteChannel = Channels.newChannel(zipOut)) {
            for (int i = 0; i < 100; i++) {
                zipOut.putNextEntry(new ZipEntry(i + SUFFIX_FILE));

                //内存中的映射文件
                MappedByteBuffer mappedByteBuffer = new RandomAccessFile(JPG_FILE_PATH, "r").getChannel()
                        .map(FileChannel.MapMode.READ_ONLY, 0, FILE_SIZE);

                writableByteChannel.write(mappedByteBuffer);
            }
            System.out.println((System.currentTimeMillis() - beginTime) / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Version 5 使用Pip
     * 100张 2.2M ，9秒
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void zipFilePip() {

        long beginTime = System.currentTimeMillis();
        try (WritableByteChannel out = Channels.newChannel(new FileOutputStream(ZIP_FILE))) {
            Pipe pipe = Pipe.open();
            //异步任务
            CompletableFuture.runAsync(() -> runTask(pipe));

            //获取读通道
            ReadableByteChannel readableByteChannel = pipe.source();
            ByteBuffer buffer = ByteBuffer.allocate(((int) FILE_SIZE) * 10);
            while (readableByteChannel.read(buffer) >= 0) {
                buffer.flip();
                out.write(buffer);
                buffer.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println((System.currentTimeMillis() - beginTime) / 1000);

    }

    /**
     * 异步任务
     */
    private static void runTask(Pipe pipe) {
        try (ZipOutputStream zos = new ZipOutputStream(Channels.newOutputStream(pipe.sink()));
             WritableByteChannel out = Channels.newChannel(zos)) {
            System.out.println("Begin");
            for (int i = 0; i < 100; i++) {
                zos.putNextEntry(new ZipEntry(i + SUFFIX_FILE));

                FileChannel jpgChannel = new FileInputStream(new File(JPG_FILE_PATH)).getChannel();

                jpgChannel.transferTo(0, FILE_SIZE, out);

                jpgChannel.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Version 6 使用Pip+Map
     * 100张 2.2M ，10秒
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void zipFilePipMap() {
        long beginTime = System.currentTimeMillis();
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(ZIP_FILE));
             WritableByteChannel out = Channels.newChannel(fileOutputStream)) {
            Pipe pipe = Pipe.open();
            //异步任务往通道中塞入数据
            CompletableFuture.runAsync(() -> runTaskMap(pipe));
            //读取数据
            ReadableByteChannel workerChannel = pipe.source();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (workerChannel.read(buffer) >= 0) {
                buffer.flip();
                out.write(buffer);
                buffer.clear();
            }
            System.out.println((System.currentTimeMillis() - beginTime) / 1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //异步任务
    private static void runTaskMap(Pipe pipe) {

        try (WritableByteChannel channel = pipe.sink();
             ZipOutputStream zos = new ZipOutputStream(Channels.newOutputStream(channel));
             WritableByteChannel out = Channels.newChannel(zos)) {
            for (int i = 0; i < 100; i++) {
                zos.putNextEntry(new ZipEntry(i + SUFFIX_FILE));

                MappedByteBuffer mappedByteBuffer = new RandomAccessFile(
                        JPG_FILE_PATH, "r").getChannel()
                        .map(FileChannel.MapMode.READ_ONLY, 0, FILE_SIZE);
                out.write(mappedByteBuffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}