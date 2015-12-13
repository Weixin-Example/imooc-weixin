package com.weixin.po;

/**
 * @author	Lian
 * @time	2015年12月11日 上午2:44:14
 * @desc	消息文本对象, 含有微信标准要求的6个对象
 */
public class TextMsg extends BaseMsg {
	private String Content;
	private String MsgId;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getMsgId() {
		return MsgId;
	}

	public void setMsgId(String msgId) {
		MsgId = msgId;
	}

}
