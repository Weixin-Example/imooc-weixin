package com.weixin.test;

import net.sf.json.JSONObject;

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

//		String path = "D:/imooc.jpg";
		// 上传图片
//			String mediaId = WeixinUtil.upload(path, token.getToken(), MsgUtil.MESSAGE_IMAGE);
		// 上传缩略图
//			String mediaId = WeixinUtil.upload(path, token.getToken(), "thumb");
//			System.out.println(mediaId);
		
		String menu = JSONObject.fromObject(WeixinUtil.initMenu()).toString();
		System.out.println(menu);
		int result = WeixinUtil.createMenu(token.getToken(), menu);
		if(result == 0) {
			System.out.println("create menu success");
		} else {
			System.out.println("errcode: " + result);
		}
	}
}
