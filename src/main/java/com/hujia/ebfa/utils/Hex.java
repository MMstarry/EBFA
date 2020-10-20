package com.hujia.ebfa.Utils;

/**
 * @author bin.lee
 * @date 2020/8/18 0018 14:29
 * @Email: libinjava@163.com
 */

public class Hex {

    /**
     * 用于建立十六进制字符的输出的小写字符数组
     */
    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 用于建立十六进制字符的输出的大写字符数组
     */
    private static final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};


    /**
     * 补位时 要填补的字符串
     */
    private static final String FILL="0";

    /**
     * 补位后字符串总长度
     */
    private static final int LENGTH=16;
  /**
     * 字符串转换成十六进制字符串
     *
     * @param str 待转换的ASCII字符串
     * @return String 每个Byte之间空格分隔，如: [61 6C 6B]
     */
    public static String str2HexStr(String str) {

        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = fillString(str,LENGTH).getBytes();
        int bit;

        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
        }
        return sb.toString().trim();
    }

    /**
     * 十六进制转换字符串
     *
     * @param  hexStr Byte字符串(Byte之间无分隔符 如:[616C6B])
     * @return String 对应的字符串
     */
    public static String hexStr2Str(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;

        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }

        String newString=new String(bytes);
        return removeFill(newString);
    }


    /**
     * 字符串补位操作
     * @param str 需补位的字符串
     * @param length 补位后字符串长度
     * @return
     */
    public static String fillString(String str , int length) {
        return fillStringBeforeString(str,FILL,length);
    }

    /**
     * 指定补位字符 不满足指定长度的进行补位
     * @param str 需补位的字符串
     * @param fill 填补的字符串
     * @param length 指定补位后字符串的位数
     * @return
     */
    public static String fillStringBeforeString(String str ,String fill, int length) {
        if(str.length() < length) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < length - str.length() ; i++) {
                sb.append(fill);
            }
            sb.append(str);
            return sb.toString();
        }else {
            return str;
        }
    }


    /**
     * 去除补位字符串
     * @param str 去除补位前字符串
     * @return
     */
    public static String removeFill(String str){

        return str.replaceFirst("^"+FILL+"*","");
    }







}
