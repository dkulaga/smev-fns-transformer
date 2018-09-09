package com.skat.smev.fns.util;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.Charset;

public class Base64Util {

    /**
     * Метод выполняет преобразование строки данный в формат base64
     * @param request строка данных
     * @return строка данный в формате base64
     */
    public static String convertToBase64(String request){
        byte[] bytesInfo = request.getBytes(Charset.forName("UTF-8"));
        return bytesInfo != null ? DatatypeConverter.printBase64Binary(bytesInfo) : "";
    }

    /**
     * Метод выполняет преобразование строки данный из формата base64
     * в cтроку UTF-8
     * @param xmlBase64 строка данных
     * @return строка данный UTF-8
     */
    public static String getXmlFromBase64(String xmlBase64) {
        String request = "";
        if (xmlBase64.isEmpty()) {
            return request;
        }
        final String requestInBase64 = new String(DatatypeConverter.parseBase64Binary(xmlBase64), Charset.forName("UTF-8"));
        return requestInBase64;
    }
}
