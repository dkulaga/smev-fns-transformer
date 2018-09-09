package com.skat.smev.fns.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


/**
 * Класс конфигруации HTTP-клиента
 */
@Configuration
public class HttpClientConfig {

    @Value("${httpclient.connectTimeout:30000}")
    private int connectTimeout;

    @Value("${httpclient.connectRequestTimeout:30000}")
    private int connectRequestTimeout;

    @Value("${httpclient.readTimeout:30000}")
    private int readTimeout;

    @Value("${httpclient.bufferRequestBody:true}")
    private boolean bufferRequestBody;

    @Value("${httpclient.connectionTTL:120000}")
    private int connectionTTL;

    @Value("${httpclient.maxPoolSize:1000}")
    private int maxPoolSize;

    @Bean(name = "restTmpl")
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
        factory.setConnectionRequestTimeout(connectRequestTimeout);
        factory.setConnectTimeout(connectTimeout);
        factory.setReadTimeout(readTimeout);
        factory.setBufferRequestBody(bufferRequestBody);
        return new RestTemplate();
    }
}
