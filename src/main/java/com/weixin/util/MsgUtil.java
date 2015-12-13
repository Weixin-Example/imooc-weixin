package com.weixin.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.weixin.po.Image;
import com.weixin.po.ImageMsg;
import com.weixin.po.News;
import com.weixin.po.NewsMsg;
import com.weixin.po.TextMsg;

/**
 * @author	Lian
 * @time	2015年12月11日 上午2:35:25
 * @desc	进行消息转换的工具类
 */
public class MsgUtil {

	/**
	 * 定义微信事件类型常量
	 */
	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_NEWS = "news";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_EVENT = "event";
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_CLICK = "click";
	public static final String MESSAGE_VIEW = "view";

	/**
	 * xml转为map
	 * 
	 * @param request
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> xmlToMap(HttpServletRequest request) throws DocumentException, IOException {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();

		// 从request中获取输入流
		InputStream ins = request.getInputStream();
		Document doc = reader.read(ins);

		Element root = doc.getRootElement();

		// 得到根元素下的所有节点
		List<Element> list = root.elements();

		// 将遍历的结果保存到map中
		for (Element e : list) {
			map.put(e.getName(), e.getText());
		}

		ins.close();
		return map;
	}

	/**
	 * 将文本消息对象转换为Xml
	 * 
	 * @param textMsg
	 * @return
	 */
	public static String textMsgToXml(TextMsg textMsg) {
		XStream xstream = new XStream();
		// 设置别名, 将返回的信息变成符合微信平台的格式
		xstream.alias("xml", textMsg.getClass());
		return xstream.toXML(textMsg);
	}

	/**
	 * 拼接文本消息
	 * 
	 * @return
	 */
	public static String initText(String toUserName, String fromUserName, String content) {
		TextMsg text = new TextMsg();
		text.setFromUserName(toUserName);
		text.setToUserName(fromUserName);
		text.setMsgType(MsgUtil.MESSAGE_TEXT);
		text.setCreateTime(new Date().getTime());
		text.setContent("您发送的消息是: " + content);
		return MsgUtil.textMsgToXml(text);
	}

	/**
	 * 关注后的菜单显示
	 * 
	 * @return
	 */
	public static String menuText() {
		StringBuffer sb = new StringBuffer();
		sb.append("欢迎您的关注, 请按照菜单提示进行操作: \n\n");
		sb.append("1. 课程介绍\n");
		sb.append("2. 慕课网介绍\n");
		sb.append("回复?调出此菜单.");
		return sb.toString();
	}

	/**
	 * 回复"1"
	 * 
	 * @return
	 */
	public static String firstMenu() {
		StringBuffer sb = new StringBuffer();
		sb.append("本套课程介绍微信公众号开发, 主要涉及公众号介绍.编辑模式介绍,开发模式介绍等");
		return sb.toString();
	}

	/**
	 * 回复"2"
	 * 
	 * @return
	 */
	public static String secondMenu() {
		StringBuffer sb = new StringBuffer();
		sb.append("慕课网是垂直的互联网IT技能免费学习网站。以独家视频教程、在线编程工具、学习计划、问答社区为核心特色。在这里，你可以找到最好的互联网技术牛人，也可以通过免费的在线公开视频课程学习国内领先的互联网IT技术。");
		sb.append("慕课网课程涵盖前端开发、PHP、Html5、Android、iOS、Swift等IT前沿技术语言，包括基础课程、实用案例、高级分享三大类型，适合不同阶段的学习人群。以纯干货、短视频的形式为平台特点，为在校学生、职场白领提供了一个迅速提升技能、共同分享进步的学习平台。");
		return sb.toString();
	}

	/**
	 * 将图文消息转换为Xml
	 * 
	 * @param newsMsg
	 * @return
	 */
	public static String newsMsgToXml(NewsMsg newsMsg) {
		XStream xstream = new XStream();
		// 设置别名, 将返回的信息变成符合微信平台的格式
		xstream.alias("xml", newsMsg.getClass());
		xstream.alias("item", new News().getClass());
		return xstream.toXML(newsMsg);
	}

	/**
	 * 将图片消息转换为Xml
	 * 
	 * @param imageMsg
	 * @return
	 */
	public static String imageMsgToXml(ImageMsg imageMsg) {
		XStream xstream = new XStream();
		// 设置别名, 将返回的信息变成符合微信平台的格式
		xstream.alias("xml", imageMsg.getClass());
		return xstream.toXML(imageMsg);
	}

	/**
	 * 图文消息的组装
	 * 
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public static String initNewsMsg(String toUserName, String fromUserName) {
		String msg = null;
		List<News> newsList = new ArrayList<News>();
		NewsMsg newsMsg = new NewsMsg();

		News news = new News();
		news.setTitle("慕课网介绍");
		news.setDescription("慕课网是垂直的互联网IT技能免费学习网站。以独家视频教程、在线编程工具、学习计划、问答社区为核心特色。");
		news.setPicUrl("http://imooc.ngrok.natapp.cn/imooc-weixin/image/imooc.jpg");
		news.setUrl("www.imooc.com");

		newsList.add(news);

		// 设置图文消息的属性
		newsMsg.setToUserName(fromUserName);
		newsMsg.setFromUserName(toUserName);
		newsMsg.setCreateTime(new Date().getTime());
		newsMsg.setMsgType(MESSAGE_NEWS);
		newsMsg.setArticles(newsList);
		newsMsg.setArticleCount(newsList.size());

		msg = newsMsgToXml(newsMsg);
		return msg;
	}

	/**
	 * 组装图片消息
	 * 
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public static String initImageMsg(String toUserName, String fromUserName) {
		String msg = null;
		Image image = new Image();
		image.setMediaId("v6uYSCw8afO-EC7-Olw7xkmu-Eg8E8l2ftdIsnDjnoa6sCkOpDct-h_OyGo6VDug");

		ImageMsg imageMsg = new ImageMsg();
		imageMsg.setFromUserName(toUserName);
		imageMsg.setToUserName(fromUserName);
		imageMsg.setMsgType(MESSAGE_IMAGE);
		imageMsg.setCreateTime(new Date().getTime());
		imageMsg.setImage(image);
		msg = imageMsgToXml(imageMsg);
		return msg;
	}
}
