package com.weixin.test;

import com.weixin.po.AccessToken;
import com.weixin.util.WeixinUtil;

/**
 * @author	Lian
 * @time	2015年12月13日 下午4:24:46
 * @desc	
 */
public class WeixinTest {
	public static void main(String[] args) {
		AccessToken token = WeixinUtil.getAccessToken();
		System.out.println("票据: " + token.getToken());
		System.out.println("有效时间: " + token.getExpiresIn());
	}
}
