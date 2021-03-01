package com.from.jmsdk.tools;

import java.io.UnsupportedEncodingException;

public class ConvertUTF8 {

    /**
     * UTF-8编码 转换为对应的 汉字
     *
     * @param s E69CA8
     * @return 木
     */
    public static String convertUTF8ToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        try {
            s = s.toUpperCase();
            int total = s.length() / 2;
            //标识字节长度
            int pos = 0;
            byte[] buffer = new byte[total];
            for (int i = 0; i < total; i++) {
                int start = i * 2;
                //将字符串参数解析为第二个参数指定的基数中的有符号整数。
                buffer[i] = (byte) Integer.parseInt(s.substring(start, start + 2), 16);
                pos++;
            }
            //通过使用指定的字符集解码指定的字节子阵列来构造一个新的字符串。
            //新字符串的长度是字符集的函数，因此可能不等于子数组的长度。
            return new String(buffer, 0, pos, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }
}
