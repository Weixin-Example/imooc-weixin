package com.weixin.trans;

/**
 * @author	Lian
 * @time	2015年12月14日 上午12:53:18
 * @desc	
 */
public class TransResult {
	private String from;
	private String to;
	private Data data;
	private String errno;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public String getErrno() {
		return errno;
	}

	public void setErrno(String errno) {
		this.errno = errno;
	}
}
