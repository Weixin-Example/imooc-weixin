package com.weixin.test;

import java.io.IOException;

import com.weixin.po.AccessToken;
import com.weixin.util.MsgUtil;
import com.weixin.util.WeixinUtil;

/**
 * @author	Lian
 * @time	2015年12月13日 下午4:24:46
 * @desc	
 */
public class WeixinTest {
	public static void main(String[] args) {
		try {
			AccessToken token = WeixinUtil.getAccessToken();
			System.out.println("票据: " + token.getToken());
			System.out.println("有效时间: " + token.getExpiresIn());

			String path = "D:/imooc.jpg";
			String mediaId = WeixinUtil.upload(path, token.getToken(), MsgUtil.MESSAGE_IMAGE);
			System.out.println(mediaId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
