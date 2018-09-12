package com.skat.smev.fns.controllers;


import com.skat.smev.fns.domain.AdapterResponseModel;
import com.skat.smev.fns.domain.RequestModel;
import com.skat.smev.fns.services.Smev3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/fns")
public class Smev3Controller {


    @Autowired
    private Smev3Service smev3Service;

    /**
     * Метод преобразования и отправки запроса от ВИС и отправки в СМЭВ-адаптер
     * @param request модель запроса Документов в ФНС в формате JSON
     * @return  возвращает сведения об успешности отправки запроса
     * @throws Exception
     */
    @PostMapping("/docs/request")
    public String sendDocRequest(@RequestBody RequestModel request) throws Exception {
        return smev3Service.sendPRDDOCRequest(request);
    }

    /**
     * Метод преобразования и отправки запроса от ВИС и отправки в СМЭВ-адаптер
     * @param request модель запроса Статусов в ФНС в формате JSON
     * @return  возвращает сведения об успешности отправки запроса
     * @throws Exception
     */
    @PostMapping("/statuses/request")
    public String sendStatusesRequest(@RequestBody RequestModel request) throws Exception {
        return smev3Service.sendSTATDOKRequest(request);
    }

    /**
     * Метод для приема ответа от СМЭВ-адаптера, его парсинга и отправки в ВИС
     * @param adapterResponse модель ответа от СМЭВ-адаптера по Документам ФНС
     * @return сведения об успешной отправке либо об ошибке отправки
     * @throws Exception
     */
        @PostMapping("/docs/response")
    public String sendDocResponse(@RequestBody AdapterResponseModel adapterResponse) throws Exception {
        return smev3Service.sendPRDDOCResponse(adapterResponse);
    }

    /**
     * Метод для приема ответа от СМЭВ-адаптера, его парсинга и отправки в ВИС
     * @param adapterResponse модель ответа от СМЭВ-адаптера по Статусам ФНС
     * @return сведения об успешной отправке либо об ошибке отправки
     * @throws Exception
     */
    @PostMapping("/statuses/response")
    public String sendStatusResponse(@RequestBody AdapterResponseModel adapterResponse) throws Exception {
        return smev3Service.sendSTATDOCResponse(adapterResponse);
    }
}
