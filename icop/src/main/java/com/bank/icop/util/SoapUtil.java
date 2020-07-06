package com.bank.icop.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.core.env.Environment;

import com.alibaba.fastjson.JSON;
import com.bank.core.entity.BizException;
import com.bank.core.entity.HeaderDO;
import com.bank.core.utils.ApplicationContextUtil;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 发送soap请求工具类
 */
@Slf4j
public class SoapUtil {

    private static StringBuilder builder = new StringBuilder();

    public static Map sendReport(String serviceCode, String channelId, Map<String, Object> paramMap) {
        HeaderDO headerDO = new HeaderDO();
        headerDO.setServiceCode(serviceCode);
        headerDO.setChannelId(channelId);
        return sendReport(headerDO, paramMap);
    }

    public static Map sendReport(String serviceCode, Map<String, Object> paramMap) {
        HeaderDO headerDO = new HeaderDO();
        headerDO.setServiceCode(serviceCode);
        return sendReport(headerDO, paramMap);
    }

    public static Map sendReport(HeaderDO headerDO, Map<String, Object> paramMap) {
        Environment env = (Environment) ApplicationContextUtil.getBeanByClass(Environment.class);
        StringBuilder builder = new StringBuilder();
        String document = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.framework.platform\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <ser:request soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                "         <xml xsi:type=\"soapenc:string\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\">\n" +
                "     <![CDATA[    \n" +
                "<Service>" +
                "<Header>\n" +
                "</Header>\n" +
                "<Body>\n" +
                "<Request>\n" +
                "</Request>\n" +
                "</Body>\n" +
                "</Service>]]>\n" +
                "</xml>\n" +
                "      </ser:request>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        String xml = builder.append(document)
                .insert(builder.indexOf("</Header>"), beanToXmlStr(headerDO))
                .insert(builder.indexOf("</Request>"), mapToXmlStr(paramMap)).toString();
        log.info("ICOP请求流水：[{}]，请求服务编码：[{}]，请求参数：[{}]", headerDO.getExternalReference(), headerDO.getServiceCode(), xml);
        HttpResponse response = HttpRequest.post(env.getProperty("ICOP.PATH")).header("SOAPAction", "application/soap+xml;charset=utf-8")
                .body(xml, "text/xml").timeout(10000).execute();

        Map<String, Object> domParse = null;
        try {
            domParse = domParse(response.body());
            log.info("ICOP返回数据：[{}]", JSON.toJSONString(domParse));
        }
        catch (DocumentException e) {
            throw new BizException("解析dom失败");
        }
        return domParse;

    }

    /**
     * 通过XML转换为Map<String,Object>
     *
     * @param xml 为String类型的Xml
     * @return 第一个为Root节点，Root节点之后为Root的元素，如果为多层，可以通过key获取下一层Map
     */
    public static Map<String, Object> domParse(String xml) throws DocumentException {
        Document doc = null;
        doc = DocumentHelper.parseText(replaceALL(xml));

        Map<String, Object> map = new HashMap();
        if (doc == null) {
            return map;
        }
        Element rootElement = doc.getRootElement();
        List<Element> list = rootElement.selectNodes("//Response");
        Map<String, Object> headMap = new HashMap<>();
        headMap = elementTomap(list.get(0));
        if (!headMap.get("ReturnCode").equals("00000000")) {
            throw new BizException(headMap.get("ReturnMessage").toString());
        }
        map = elementTomap(list.get(1));
        return map;
    }

    /***
     * XmlToMap核心方法，里面有递归调用
     */
    @SuppressWarnings("unchecked")
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

    private static String replaceALL(String xml) {
        return xml.replaceAll("&gt;", ">").replaceAll("&lt;", "<");
    }

    private static String mapToXmlStr(Map<String, Object> map) {
        builder.setLength(0);
        map.forEach((k, v) -> builder.append("<").append(k).append(">").append(v).append("</").append(k).append(">"));
        return builder.toString();
    }

    private static String beanToXmlStr(Object bean) {
        builder.setLength(0);
        Class type = bean.getClass();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(type);

            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (!propertyName.equals("class")) {
                    Method readMethod = descriptor.getReadMethod();
                    Object result = readMethod.invoke(bean, new Object[0]);
                    if (result != null) {
                        propertyName = StrUtil.upperFirst(propertyName);
                        builder.append("<").append(propertyName).append(">").append(result).append("</").append(propertyName).append(">");
                    }
                }
            }
        }
        catch (Exception e) {
            String errMsg = e.getMessage();
            log.info("{}", errMsg);
            throw new BizException(errMsg);
        }
        return builder.toString();
    }
}
