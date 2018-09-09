package com.skat.smev.fns.util;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * Вспомогательный класс для работы с DOM
 */
public class DomUtil {

    private static final String ENCODING = "UTF-8";

    public static String elementToString(Element element)
            throws TransformerFactoryConfigurationError, TransformerException {
        StringWriter writer = new StringWriter();
        Transformer transformer = TransformerFactory.newInstance()
                .newTransformer();
        transformer.transform(new DOMSource(element), new StreamResult(writer));
        return writer.toString();
    }

    public static Element stringToElement(String xml)
            throws ParserConfigurationException, UnsupportedEncodingException,
            SAXException, IOException {
        DocumentBuilder documentBuilder = createDocumentBuilder();
        Document document = documentBuilder.parse(new ByteArrayInputStream(xml
                .getBytes(ENCODING)));
        return document.getDocumentElement();
    }

    private static DocumentBuilder createDocumentBuilder()
            throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setCoalescing(true);
        factory.setIgnoringElementContentWhitespace(true);
        return factory.newDocumentBuilder();
    }

}
