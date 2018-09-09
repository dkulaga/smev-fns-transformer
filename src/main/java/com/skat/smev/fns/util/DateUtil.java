package com.skat.smev.fns.util;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *  Вспомогательный класс для работы с форматом даты
 */
public class DateUtil {

    public static XMLGregorianCalendar dateToXMLGregorianCalendar(Date date)
            throws DatatypeConfigurationException {
        GregorianCalendar gregory = new GregorianCalendar();
        gregory.setTime(date);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
    }

    private static Date parseDate(String dateStr, String format)
            throws ParseException {
        DateFormat df = new SimpleDateFormat(format);
        return df.parse(dateStr);
    }

    public static XMLGregorianCalendar parseXMLGregorianCalendar(
            String dateStr, String format) throws ParseException,
            DatatypeConfigurationException {
        Date date = parseDate(dateStr, format);
        final XMLGregorianCalendar xmlGregorianCalendar = dateToXMLGregorianCalendar(date);
        xmlGregorianCalendar.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
        return xmlGregorianCalendar;

    }
}
