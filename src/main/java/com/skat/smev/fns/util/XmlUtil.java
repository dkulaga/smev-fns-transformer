package com.skat.smev.fns.util;

import javax.xml.bind.*;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;

/**
 *  Вспомогательный класс для работы с XML
 */
public class XmlUtil {

	private static final String CONTEXT_PATH = "com.skat.smev.fns.model";

	public static <T> String marshal(JAXBElement<T> element)
			throws JAXBException {
		StringWriter sw = new StringWriter();

		JAXBContext jc = JAXBContext.newInstance(CONTEXT_PATH);
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		marshaller.marshal(element, sw);

		return sw.toString();
	}

	public static <T> String  jaxbObjectToXML(Object request, Class<T> type) {
		String xmlString = "";
		try {
			JAXBContext context = JAXBContext.newInstance(type);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML
			StringWriter sw = new StringWriter();
			m.marshal(request, sw);
			xmlString = sw.toString();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return xmlString;
	}

	public static <T> T unmarshal(String xml, Class<T> type)
			throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(type);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		return unmarshaller.unmarshal(new StreamSource(is), type).getValue();
	}

}