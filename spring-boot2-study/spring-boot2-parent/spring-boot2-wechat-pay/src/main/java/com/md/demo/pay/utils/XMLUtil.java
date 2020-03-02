package com.md.demo.pay.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * XML解析工具
 */
@SuppressWarnings("all")
public class XMLUtil {
	
	/**
	 * 解析xml,返回第一级元素键值对。 如果第一级元素有子节点， 则此节点的值是子节点的xml数据。
	 * 
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static SortedMap<String, Object> doXMLParse(String strxml) throws JDOMException, IOException {
		strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");
		if (null == strxml || "".equals(strxml)) {
			return null;
		}
		SortedMap<String, Object> map = new TreeMap<String, Object>();
		InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Element e = (Element) it.next();
			String key = e.getName();
			String value = "";
			List children = e.getChildren();
			if (children.isEmpty()) {
				value = e.getTextNormalize();
			} else {
				value = XMLUtil.getChildrenText(children);
			}
			map.put(key, value);
		}
		// 关闭流
		in.close();
		return map;
	}

	/**
	 * 获取子结点的xml
	 * 
	 * @param children
	 * @return
	 */
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if (!children.isEmpty()) {
			Iterator it = children.iterator();
			while (it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if (!list.isEmpty()) {
					sb.append(XMLUtil.getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}
		return sb.toString();
	}

}
