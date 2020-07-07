package com.bank.esb.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

/**
 *
 * ClassName: ESBUtil
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/07/07 13:05:01
 */
public class ESBUtil {

    public static Map<String, Object> elementTomap(Element e) {
        Map map = new HashMap();
        List list = e.elements();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Element iter = (Element) list.get(i);
                List mapList = new ArrayList();

                if (iter.elements().size() > 0) {
                    Map m = elementTomap(iter);
                    if (map.get(iter.getName()) != null) {
                        Object obj = map.get(iter.getName());
                        if (!obj.getClass().getName().equals("java.util.ArrayList")) {
                            mapList = new ArrayList();
                            mapList.add(obj);
                            mapList.add(m);
                        }
                        if (obj.getClass().getName().equals("java.util.ArrayList")) {
                            mapList = (List) obj;
                            mapList.add(m);
                        }
                        map.put(iter.getName(), mapList);
                    }
                    else {
                        map.put(iter.getName(), m);
                    }
                }
                else {
                    if (map.get(iter.getName()) != null) {
                        Object obj = map.get(iter.getName());
                        if (!obj.getClass().getName().equals("java.util.ArrayList")) {
                            mapList = new ArrayList();
                            mapList.add(obj);
                            mapList.add(iter.getText());
                        }
                        if (obj.getClass().getName().equals("java.util.ArrayList")) {
                            mapList = (List) obj;
                            mapList.add(iter.getText());
                        }
                        map.put(iter.getName(), mapList);
                    }
                    else {
                        map.put(iter.getName(), iter.getText());
                    }
                }
            }
        }
        else {
            map.put(e.getName(), e.getText());
        }
        return map;
    }

    public static String convert(Map<String, Object> map) {
        StringBuffer buffer = new StringBuffer();
        for (String key : map.keySet()) {
            buffer.append("<" + key + ">");
            if (map.get(key) instanceof Map) {
                String element = convert((Map<String, Object>) map.get(key));
                buffer.append(element);
            }
            else {
                buffer.append(map.get(key));
            }
            buffer.append("</" + key + ">");
        }
        return buffer.toString();
    }
}
