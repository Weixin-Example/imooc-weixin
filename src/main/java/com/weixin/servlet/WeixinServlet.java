package com.weixin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.weixin.po.TextMsg;
import com.weixin.util.CheckUtil;
import com.weixin.util.MsgUtil;

/**
 * @author	Lian
 * @time	2015年12月11日 上午1:44:15
 * @desc	
 */
public class WeixinServlet extends HttpServlet {
	private static final long serialVersionUID = -2836724667530087285L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");

		PrintWriter out = resp.getWriter();
		if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置接收和响应字符串的格式
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		try {
			// 使用集合接收消息
			Map<String, String> map = MsgUtil.xmlToMap(req);
			// 从集合中获取消息属性
			String fromUserName = map.get("FromUserName");
			String toUserName = map.get("ToUserName");
			String msgType = map.get("MsgType");
			String content = map.get("Content");

			String msg = null;
			// 判断消息是否为文本消息
			if ("text".equals(msgType)) {
				TextMsg text = new TextMsg();
				text.setFromUserName(toUserName);
				text.setToUserName(fromUserName);
				text.setMsgType("text");
				text.setCreateTime(new Date().getTime());
				text.setContent("您发送的消息是: " + content);
				msg = MsgUtil.textMsgToXml(text);
			}
			// 返回消息
			out.print(msg);

		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
}
