package com.yun.library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 文件压缩
 *
 * @author yun
 * @date 2018/12/29 11:37
 * @Description:
 */
public class ZipUtil {

    /**
     * 文件压缩
     *
     * @param srcFile
     * @param desFile
     * @throws IOException
     */
    public static void fileZip(File srcFile, File desFile) throws IOException {
        GZIPOutputStream zos = null;
        FileInputStream fis = null;
        try {
            //创建压缩输出流,将目标文件传入
            zos = new GZIPOutputStream(new FileOutputStream(desFile));
            //创建文件输入流,将源文件传入
            fis = new FileInputStream(srcFile);
            byte[] buffer = new byte[1024];
            int len = -1;
            //利用IO流写入写出的形式将源文件写入到目标文件中进行压缩
            while ((len = (fis.read(buffer))) != -1) {
                zos.write(buffer, 0, len);
            }
        } finally {
            if (zos != null) {
                zos.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
    }

    /**
     * 文件解压
     *
     * @param srcFile
     * @param desFile
     * @throws IOException
     */
    public static void fileUnZip(File srcFile, File desFile) throws IOException {
        GZIPInputStream zis = null;
        FileOutputStream fos = null;
        try {
            //创建压缩输入流,传入源文件
            zis = new GZIPInputStream(new FileInputStream(srcFile));
            //创建文件输出流,传入目标文件
            fos = new FileOutputStream(desFile);
            byte[] buffer = new byte[1024];
            int len = -1;
            //利用IO流写入写出的形式将压缩源文件解压到目标文件中
            while ((len = (zis.read(buffer))) != -1) {
                fos.write(buffer, 0, len);
            }
        } finally {
            if (zis != null) {
                zis.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }
}
