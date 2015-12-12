package com.weixin.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author	Lian
 * @time	2015年12月11日 上午1:47:53
 * @desc	
 */
public class CheckUtil {
	private static final String TOKEN = "imooc";
	public static boolean check(String signature, String timestamp, String nonce) {
		String[] arr = new String[] {TOKEN, timestamp, nonce};
		// 排序
		Arrays.sort(arr);
		// 生成字符串
		StringBuffer content = new StringBuffer();
		for(int i=0; i<arr.length; i++) {
			content.append(arr[i]);
		}
		// 加密
		String temp = getSha1(content.toString());
		
		return temp.equals(signature);
	}
	
	public static String getSha1(String decript) {
		try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
 
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
	}
}
