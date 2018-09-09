package com.skat.smev.fns.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.skat.smev.fns.domain.*;
import com.skat.smev.fns.model.docs.PRDDOKREGRequest;
import com.skat.smev.fns.transmitter.impl.ResponseTransmitterService;
import com.skat.smev.fns.transmitter.impl.Smev3AdapterService;
import com.skat.smev.fns.util.Base64Util;
import com.skat.smev.fns.util.FnsRequestTransformer;
import com.skat.smev.fns.util.JsonUtil;
import com.skat.smev.fns.util.XmlUtil;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import java.text.ParseException;

/**
 * Сервис для осуществления взаимодействия данного сервиса-трансформатора
 * со СМЭВ-адаптером и с ВИС
 */
@Service
public class Smev3Service {

    private static final Logger LOGGER = Logger.getLogger(Smev3Service.class.getName());

    @Autowired
    private Smev3AdapterService smev3AdapterService;

    @Autowired
    private ResponseTransmitterService responseTransmitterService;

    /**
     * Метод преобразования и отправки запроса от ВИС и отправки в СМЭВ-адаптер
     * @param requestModel модель запроса в формате JSON
     * @return  возвращает сведения об успешности отправки запроса
     * @throws Exception
     */
    public String sendPRDDOCRequest(RequestModel requestModel) throws ParseException, JAXBException, DatatypeConfigurationException, JsonProcessingException {
        final PRDDOKREGRequest element = FnsRequestTransformer.createPRDDOKREGRequest(requestModel);
        final String xml = XmlUtil.jaxbObjectToXML(element, PRDDOKREGRequest.class);
        final String base64request = Base64Util.convertToBase64(xml);
        try {
            return smev3AdapterService.sendRequest(base64request, requestModel.getAttachments());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "Error while sending request";
    }

    /**
     * Метод для приема ответа от СМЭВ-адаптера, его парсинга и отправки в ВИС
     * @param adapterResponse модель ответа от СМЭВ-адаптера
     * @return сведения об успешной отправке либо об ошибке отправки
     * @throws Exception
     */
    public String sendResponse(AdapterResponseModel adapterResponse)  {
        BaseMessageModel baseMessageModel = null;
        String stringMessage = "";
        try {
            baseMessageModel = FnsRequestTransformer.parseResponseFromAdapter(adapterResponse);
            stringMessage = JsonUtil.stringify(baseMessageModel);
        } catch (Exception e) {
            LOGGER.error("Error while parsing adapter response: " + e.getMessage());
        }
        return responseTransmitterService.sendResponse(stringMessage);
    }
}
