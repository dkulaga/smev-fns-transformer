package com.skat.smev.fns.transmitter.impl;


import com.skat.smev.fns.transmitter.RestException;
import com.skat.smev.fns.util.HttpUtil;
import org.apache.log4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;



/**
 * Сервис для отправки ответа от СМЭВ-адаптера в ВИС
 */
@Service
public class ResponseTransmitterService {
    private final String serviceUrl;
    private final RestTemplate restTemplate;
    private static final Logger LOGGER = Logger.getLogger(ResponseTransmitterService.class.getName());

    public ResponseTransmitterService(RestTemplate restTemplate, String serviceUrl) {
        this.serviceUrl = serviceUrl;
        this.restTemplate = restTemplate;
    }

    /**
     * Метод выполняет отправку ответа от СМЭВ-адаптера в сервис ВИС
     * @param smev3Response ответ от СМЭВ-адаптера, преобразованный к нужному формату
     * @return
     */
    public String sendResponse(String smev3Response){
        final ResponseEntity<String> response;
        try {
            LOGGER.info("Sending response from adapter to receiver");
            LOGGER.info("URL: " + serviceUrl);
            LOGGER.info("Request: POST");
            LOGGER.info("Body: " + smev3Response);
            response = restTemplate.exchange(serviceUrl, HttpMethod.POST, HttpUtil.getEntity(smev3Response), String.class);

            LOGGER.info("Response: " + response.getBody());
            return response.getBody();
        } catch (RestClientException ex) {
            throw new RestException("Ошибка отправки ответа в ВИС", ex);
        }
    }
}
