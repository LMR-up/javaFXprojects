package com.lmr.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.Objects;

public class MD5 {
    public static void main(String[] args) {
        System.out.println(md5("1231"));

    }
    public static String md5(String source)  {
        Objects.requireNonNull(source);
        MessageDigest messageDigest= null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        messageDigest.update(source.getBytes());
        byte[] digest=messageDigest.digest();
        BigInteger integer=new BigInteger(1,digest);//将BigInteger的符号大小表示形式转换为BigInteger。 符号表示为整数值：-1表示负数，0表示零，或表示1表示正数。 幅度是大字节序列中的字节数组：
        // 最高有效字节在第零个元素中。 零长度幅度阵列是允许的，并且将导致BigInteger值为0，无论signum为-1,0或1。
        return integer.toString(16).toUpperCase(Locale.ROOT);
    }
    public  static String Md5(String plainText) throws NoSuchAlgorithmException {
        String result = "";
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(plainText.getBytes());
        byte[] s= md.digest();
        for(int i=0; i<s.length; i++) {
            result += Integer.toHexString((0x000000FF & s[i])| 0xFFFFFF00).substring(6);
        }

        return result.toUpperCase();
    }
}
