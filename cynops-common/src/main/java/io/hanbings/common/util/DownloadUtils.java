package io.hanbings.common.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@SuppressWarnings("unused ResultOfMethodCallIgnored")
public class DownloadUtils {
    /**
     * 从网络Url中下载文件
     *
     * @param url  URL
     * @param name 保存下来的名字
     * @param path 保存目录
     * @throws IOException 抛出IO错误 懂的都懂
     */
    public static void download(String url, String name, String path) throws IOException {
        URL source = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) source.openConnection();
        //设置超时间为3秒
        connection.setConnectTimeout(3000);
        //得到输入流
        InputStream inputStream = connection.getInputStream();
        //获取自己数组
        byte[] getData = readInputStream(inputStream);
        //文件保存位置
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File file = new File(dir + File.separator + name);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(getData);
        fileOutputStream.close();
        inputStream.close();
        connection.disconnect();
        System.out.println("Download " + url + " Done.");
    }

    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream 输入流
     * @return 返回流
     * @throws IOException 抛出IO错误 懂的都懂
     */
    private static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, len);
        }
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
}
