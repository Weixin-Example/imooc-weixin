package com.weixin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.weixin.util.CheckUtil;
import com.weixin.util.MsgUtil;
import com.weixin.util.WeixinUtil;

/**
 * @author	Lian
 * @time	2015年12月11日 上午1:44:15
 * @desc	微信后台调用的Get和Post方法
 */
public class WeixinServlet extends HttpServlet {
	private static final long serialVersionUID = -2836724667530087285L;

	/**
	 * doGet方法
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 得到微信传过来的参数
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");

		PrintWriter out = resp.getWriter();
		// 绑定微信校验
		if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
	}

	/**
	 * doPost方法
	 */
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
			if (MsgUtil.MESSAGE_TEXT.equals(msgType)) { // 接收文本消息
				// 用户回复内容判断
				if ("1".equals(content)) {
					msg = MsgUtil.initText(toUserName, fromUserName, MsgUtil.firstMenu());
				} else if ("2".equals(content)) {
					msg = MsgUtil.initNewsMsg(toUserName, fromUserName);
				} else if ("3".equals(content)) {
					msg = MsgUtil.initText(toUserName, fromUserName, MsgUtil.threeMenu());
				} else if ("4".equals(content)) {
					msg = MsgUtil.initImageMsg(toUserName, fromUserName);
				} else if ("5".equals(content)) {
					msg = MsgUtil.initMusicMsg(toUserName, fromUserName);
				} else if ("?".equals(content) || "？".equals(content)) {
					msg = MsgUtil.initText(toUserName, fromUserName, MsgUtil.menuText());
				} else if (content.startsWith("翻译")) {
					String word = content.replaceAll("^翻译", "").trim();
					if ("".endsWith(word)) {
						msg = MsgUtil.initText(toUserName, fromUserName, MsgUtil.threeMenu());
					} else {
						msg = MsgUtil.initText(toUserName, fromUserName, WeixinUtil.translate(word));
					}
				}
			} else if (MsgUtil.MESSAGE_EVENT.equals(msgType)) { // 接收事件推送
				String eventType = map.get("Event");
				if (MsgUtil.MESSAGE_SUBSCRIBE.equals(eventType)) { // 关注逻辑
					msg = MsgUtil.initText(toUserName, fromUserName, MsgUtil.menuText());
				} else if (MsgUtil.MESSAGE_CLICK.equals(eventType)) { // 点击菜单获取消息
					msg = MsgUtil.initText(toUserName, fromUserName, MsgUtil.menuText());
				} else if (MsgUtil.MESSAGE_VIEW.equals(eventType)) { // 点击菜单挑中连接
					String url = map.get("EventKey");
					msg = MsgUtil.initText(toUserName, fromUserName, url);
				} else if (MsgUtil.MESSAGE_SCANCODE.equals(eventType)) { // 扫描二维码
					String key = map.get("EventKey");
					msg = MsgUtil.initText(toUserName, fromUserName, key);
				}
			} else if (MsgUtil.MESSAGE_LOCATION.equals(msgType)) { // 接收地理位置
				String Label = map.get("Label");
				msg = MsgUtil.initText(toUserName, fromUserName, Label);
			}
			System.out.println(msg);
			// 返回消息
			out.print(msg);

		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
}
