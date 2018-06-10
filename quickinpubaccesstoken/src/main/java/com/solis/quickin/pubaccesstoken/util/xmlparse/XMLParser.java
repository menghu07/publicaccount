package com.solis.quickin.pubaccesstoken.util.xmlparse;

import com.solis.quickin.pubaccesstoken.util.crypto.AesException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

/**
 * Created by monis on 2018/6/9.
 */
public class XMLParser {

    /**
     * 提取加密中数据包中的加密消息
     * @param xmlText 待提取的xml字符串
     * @return 提取出的加密字符串
     * @throws Exception
     */
    public static Object[] extract(String xmlText) throws AesException {
        Object[] result = new Object[3];
        try {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            StringReader reader = new StringReader(xmlText);
            InputSource inputSource = new InputSource(reader);
            Document document = documentBuilder.parse(inputSource);
            Element root = document.getDocumentElement();
            NodeList nodeList1 = root.getElementsByTagName("Encrypt");
            NodeList nodeList2 = root.getElementsByTagName("ToUserName");
            result[0] = 0;
            result[1] = nodeList1.item(1).getTextContent();
            result[2] = nodeList2.item(2).getTextContent();

        } catch (Exception e){
            e.printStackTrace();
            throw new AesException(AesException.ParseXmlError);
        }
        return result;
    }

    /**
     * 生成xml消息
     * @param encrypt 加密后的消息密文
     * @param signature 安全签名
     * @param timestamp 时间戳
     * @param nonce 随机字符串
     * @return
     */
    public static String generate(String encrypt, String signature, String timestamp, String nonce) {
        String format = "<xml>\n" + "<Encrypt><![CDATA%1$s]]></Encrypt>\n"
                + "<MsgSignature><![CDATA%2$s]]></MsgSignature>\n"
                + "<TimeStamp><![CDATA%3$s]]</TimeStamp>\n"
                + "<Nonce><![CDATA%4$s]]</Nonce>\n" + "</xml>";
        return String.format(format, encrypt, signature, timestamp, nonce);
    }
}
