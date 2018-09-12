package com.skat.smev.fns.transmitter.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.skat.smev.fns.domain.AdapterRequestModel;
import com.skat.smev.fns.domain.AttachmentModel;
import com.skat.smev.fns.transmitter.RestException;
import com.skat.smev.fns.util.HttpUtil;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;


/**
 * Сервис для работы со СМЭВ-адаптером
 */
@Service
public class Smev3AdapterService {

    private final String serviceUrl;
    private final RestTemplate restTemplate;
    private static final Logger LOGGER = Logger.getLogger(Smev3AdapterService.class.getName());


    public Smev3AdapterService(RestTemplate restTemplate, String serviceUrl) {
        this.restTemplate = restTemplate;
        this.serviceUrl = serviceUrl;
    }

    /**
     * Метод для отправки запроса в СМЭВ-адаптер
     * @param xml xml-апрос в формате base64
     * @param attachments вложения
     * @return возвращает информацию об отправке запроса
     * @throws JSONException
     */
    public String sendRequest(String xml, List<AttachmentModel> attachments) throws JSONException, JsonProcessingException {
        final String sendRequestUrl = serviceUrl + "/smev3/request";
        final ResponseEntity<String> response;
        final AdapterRequestModel adapterRequestModel = new AdapterRequestModel();
        adapterRequestModel.setXml(xml);
        if(!StringUtils.isEmpty(attachments)){
            adapterRequestModel.setAttachments(attachments);
        }
        try {
            LOGGER.info("Send request to adapter");
            LOGGER.info("URL: " + sendRequestUrl);
            LOGGER.info("Request: POST");
            LOGGER.info("Body: " + xml);

            response = restTemplate.exchange(sendRequestUrl, HttpMethod.POST, HttpUtil.getEntity(adapterRequestModel), String.class);
            LOGGER.info("Response: " + response.getBody());
            return response.getBody();
        } catch (RestClientException ex) {
            throw new RestException("Ошибка отправки запроса в СМЭВ адаптер", ex);
        }
    }


}
