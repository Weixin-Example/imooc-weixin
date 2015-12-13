package com.weixin.po;

/**
 * @author	Lian
 * @time	2015年12月13日 下午4:14:35
 * @desc	AccessToken 实体对象
 */
public class AccessToken {
	private String token;
	private int expiresIn;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	
}
