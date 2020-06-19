package com.bank.icop.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.bank.core.entity.BizException;
import com.bank.core.entity.HeaderDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 发送soap请求工具类
 */
@Slf4j
public class SoapUtil {

    private static final String remoteUrl = "http://dev-icop-qdzh.prod.bcs:43294/icop/services/JTService?wsdl";

    private static StringBuilder builder = new StringBuilder();

    public static Map sendReport(String serviceCode, Map<String, Object> paramMap) {
        HeaderDO headerDO = new HeaderDO();
        headerDO.setServiceCode(serviceCode);
        return sendReport(headerDO,paramMap);
    }

    public static Map sendReport(HeaderDO headerDO, Map<String, Object> paramMap) {
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

        HttpResponse response = HttpRequest.post(remoteUrl).header("SOAPAction", "application/soap+xml;charset=utf-8")
                .body(xml, "text/xml").execute();


        Map<String, Object> domParse = null;
        try {
            domParse = domParse(response.body());
        } catch (DocumentException e) {
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
        elementTomap(list.get(0), headMap);
        Map<String, Object> response = (Map<String, Object>) headMap.get("Response");
        if (!response.get("ReturnCode").equals("00000000")){
            throw new BizException(response.get("ErrorMsg").toString());
        }
        elementTomap(list.get(1), map);
        return (Map<String, Object>) map.get("Response");
    }

    /***
     * XmlToMap核心方法，里面有递归调用
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> elementTomap(Element outele,
                                                   Map<String, Object> outmap) {
        List<Element> list = outele.elements();
        int size = list.size();
        if (size == 0) {
            outmap.put(outele.getName(), outele.getTextTrim());
        } else {
            Map<String, Object> innermap = new HashMap<String, Object>();
            for (Element ele1 : list) {
                String eleName = ele1.getName();
                Object obj = innermap.get(eleName);
                if (obj == null) {
                    elementTomap(ele1, innermap);
                } else {
                    if (obj instanceof java.util.Map) {
                        List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
                        list1.add((Map<String, Object>) innermap
                                .remove(eleName));
                        elementTomap(ele1, innermap);
                        list1.add((Map<String, Object>) innermap
                                .remove(eleName));
                        innermap.put(eleName, list1);
                    } else {
                        elementTomap(ele1, innermap);
                        ((List<Map<String, Object>>) obj).add(innermap);
                    }
                }
            }
            outmap.put(outele.getName(), innermap);
        }
        return outmap;
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
        } catch (Exception e) {
            String errMsg = e.getMessage();
            log.info("{}", errMsg);
            throw new BizException(errMsg);
        }
        return builder.toString();
    }

    public static Object sendDynamic(String methodName, Object... params) {
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(remoteUrl);
        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
        Object[] objects;
        try {
            //如果有命名空间的话
//            QName operationName = new QName("http://service.webservice.zhao.com/",methodName); //如果有命名空间需要加上这个，第一个参数为命名空间名称，第二个参数为WebService方法名称
            objects = client.invoke(methodName, params);
            return objects[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
