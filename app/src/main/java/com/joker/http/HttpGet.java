
package com.joker.http;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * @author yuege
 *         2014年3月12日 上午11:32:53
 */
public class HttpGet {
    public static String httpGet(String urls) {
        String result = "";
        URL url = null;
        try {
            url = new URL(urls);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5 * 1000);
            conn.setRequestMethod("GET");
            InputStream inStream = conn.getInputStream();
            BufferedInputStream inputStream = new BufferedInputStream(inStream);
            result = new String(readInputStream(inputStream));
            System.out.println("result------>"+result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();//网页的二进制数据
        outStream.close();
        inStream.close();
        return data;
    }
}
