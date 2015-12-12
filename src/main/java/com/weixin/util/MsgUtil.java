package com.weixin.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.weixin.model.TextMsg;

/**
 * @author	Lian
 * @time	2015年12月11日 上午2:35:25
 * @desc	
 */
public class MsgUtil {
	/**
	 * xml转为map
	 * @param request
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static Map<String, String> xmlToMap(HttpServletRequest request) throws DocumentException, IOException {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		
		InputStream ins = request.getInputStream();
		Document doc = reader.read(ins);
		
		Element root = doc.getRootElement();
		
		List<Element> list = root.elements();
		
		for(Element e : list) {
			map.put(e.getName(), e.getText());
		}
		
		ins.close();
		return map;
	}
	
	/**
	 * 将文本消息对象转换为Xml
	 * @param textMsg
	 * @return
	 */
	public static String textMsgToXml(TextMsg textMsg) {
		XStream xstream = new XStream();
		xstream.alias("xml", textMsg.getClass());
		return xstream.toXML(textMsg);
	}
}
