package com.skat.smev.fns.config;


import com.skat.smev.fns.transmitter.impl.ResponseTransmitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ResponseTransmitterServiceConfig {

    @Value("${transmitter.service.url:}")
    private String url;

    @Autowired
    private RestTemplate restTmpl;

    @Bean(name = "responseTransmitterService")
    public ResponseTransmitterService getResponseTransmitterService() {
        String transmitterUrl = (url != null) ? url.trim() : "";
        return new ResponseTransmitterService(restTmpl, transmitterUrl);
    }
}
