package com.md.demo.pay.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 对map的key进行ASCII排序
 */
public class MapUtils {

	/**
	 * 对map根据key进行排序 ASCII 顺序
	 * 
	 * @param 无序的map
	 * @return
	 */
	public static SortedMap<String, Object> sortMap(Map<String, Object> map) {
		List<Map.Entry<String, Object>> infoIds = new ArrayList<Map.Entry<String, Object>>(map.entrySet());
		// 排序
		Collections.sort(infoIds, new Comparator<Map.Entry<String, Object>>() {
			public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
				return (o1.getKey()).toString().compareTo(o2.getKey());
			}
		});
		// 排序后
		SortedMap<String, Object> sortmap = new TreeMap<String, Object>();
		System.out.println("根据key进行排序ASCII顺序，infoIds=" + infoIds.toString());
		for (int i = 0; i < infoIds.size(); i++) {
			String[] split = infoIds.get(i).toString().split("=");
			sortmap.put(split[0], split[1]);
		}
		return sortmap;
	}

	/**
	 * map to String
	 * 
	 * @param map
	 * @return
	 */
	public static String toString(Map<String, Object> map) {
		StringBuffer buf = new StringBuffer();
		buf.append("{");
		Iterator<Entry<String, Object>> i = map.entrySet().iterator();
		boolean hasNext = i.hasNext();
		while (hasNext) {
			Entry<String, Object> e = i.next();
			Object key = e.getKey();
			Object value = e.getValue();
			if (key == MapUtils.class)
				buf.append("(this Map)");
			else
				buf.append(key);
			buf.append("=");
			if (value == MapUtils.class)
				buf.append("(this Map)");
			else
				buf.append(value);
			hasNext = i.hasNext();
			if (hasNext)
				buf.append(", ");
		}
		buf.append("}");
		return buf.toString();
	}

}
