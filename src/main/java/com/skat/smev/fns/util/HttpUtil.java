package com.skat.smev.fns.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.nio.charset.Charset;

/**
 *  Вспомогательный класс для работы с HttpClient
 */
public class HttpUtil {

    public final static <T extends Object> HttpEntity<T> getEntity(final T obj) {
        final HttpHeaders headers = new HttpHeaders();
        final MediaType mediaType = new MediaType("application", "json", Charset.forName("UTF-8"));
        headers.setContentType(mediaType);
        final HttpEntity<T> result = obj == null ? new HttpEntity<T>(headers) : new HttpEntity<>(obj, headers);
        return result;
    }
}
