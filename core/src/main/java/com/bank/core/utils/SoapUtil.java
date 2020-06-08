package com.bank.core.utils;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import com.bank.core.entity.BizException;
import com.bank.core.entity.HeaderDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 发送soap请求工具类
 */
@Slf4j
public class SoapUtil {

    private static final String remoteUrl = "http://localhost:8080/ws/webservice?wsdl";

    private static StringBuilder builder = new StringBuilder();

    /**
     * 测试使用cxf发送请求
     */
//    @Test
    public void test() {
        Object response = sendDynamic("getWork", "zz", 10);
        System.out.println(response);
    }

    /**
     * 测试http
     */
//    @Test
    public void testSendReport(){
        HashMap<String, Object> map = MapUtil.newHashMap();
        map.put("name", "tom");
        map.put("age", 5);

        HeaderDO headerDO = new HeaderDO();
        headerDO.setChannelId("chinelid");
        headerDO.setUuid("jdskfljsdlafjdslaf");

        System.out.println(sendReport(headerDO, map));
    }
    public static String sendReport(HeaderDO headerDO, Map<String, Object> paramMap){
        StringBuilder builder = new StringBuilder();
        String document = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:pm=\"http://service.webservice.zhao.com/\">\n" +
                "    <soapenv:Header></soapenv:Header>\n" +
                "    <soapenv:Body>\n" +
                "        <pm:hello>\n" +
                "            <friend>zhaozy</friend>\n" +
                "        </pm:hello>\n" +
                "    </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        String xml= builder.append(document)
                .insert(builder.indexOf("</soapenv:Header>"), beanToXmlStr(headerDO))
                .insert(builder.indexOf("</soapenv:Body>"), mapToXmlStr(paramMap)).toString();

        String response = HttpRequest.post(remoteUrl)
                .body(xml, "text/xml").execute().body();
        return response.substring(response.indexOf("<return>") + 8, response.lastIndexOf("</return>"));

    }

    private static String mapToXmlStr(Map<String, Object> map) {
        builder.setLength(0);
        map.forEach((k, v) -> builder.append("<").append(k).append(">").append(v).append("</").append(k).append(">"));
        return builder.toString();
    }

    private static String beanToXmlStr(Object bean){
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
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
