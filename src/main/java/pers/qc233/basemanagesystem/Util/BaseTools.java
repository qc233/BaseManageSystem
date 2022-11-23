package pers.qc233.basemanagesystem.Util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class BaseTools {
    /**
     * 将字符串进行SHA256算法加密
     * @param str 原始字符串
     * @return 加密字符串
     */
    public static String getSHA(String str){
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    /**
     * 将byte转为16进制
     * @param bytes 初始字节信息
     * @return 16进制数据
     */
    private static String byte2Hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<bytes.length;i++){
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length()==1){
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    /**
     * 获取任意位数的随机字符串，包含a-z、A-Z、0-9以及 ’*‘ ’-‘ ’/‘ ’+’ ‘=’
     * @param len 获取的随机字符串长度
     * @return 随机字符串
     */
    public static String getRandomStr(int len){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789/*-+=";
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < len; i++) {
            int ch = random.nextInt(str.length());
            stringBuffer.append(str.charAt(ch));
        }
        return stringBuffer.toString();
    }
}
