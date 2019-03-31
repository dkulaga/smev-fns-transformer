package com.skat.smev.fns.util;


import com.skat.smev.fns.domain.*;
import com.skat.smev.fns.model.docs.ObjectFactory;
import com.skat.smev.fns.model.docs.PRDDOKREGRequest;
import com.skat.smev.fns.model.docs.PRDDOKREGResponse;
import com.skat.smev.fns.model.statuses.STATDOKREGRequest;
import com.skat.smev.fns.model.statuses.STATDOKREGResponse;
import org.apache.log4j.Logger;

public class FnsRequestTransformer {
    private static final Logger LOGGER = Logger.getLogger(FnsRequestTransformer.class.getName());

    /*Документы ФНС: Запрос*/
    public static PRDDOKREGRequest createPRDDOKREGRequest(RequestModel model){
        ObjectFactory docsOjectFactory = new ObjectFactory();
        PRDDOKREGRequest prddokregRequest = docsOjectFactory.createPRDDOKREGRequest();
        prddokregRequest.setИдЗапрос(model.getRequestId());
        prddokregRequest.setВидГосРег(model.getRegType());
        return prddokregRequest;
    }

  /**
     * Метод выполняет преобразование ответа от СМЭВ-адаптера в формат {@link BaseMessageModel}
     * @param adapterResponseModel ответ от СМЭВ-адаптера по Документам от ФНС
     * @return формированный ответ для дальнейшей отправки в ВИС
     * @throws Exception
     */
    public static BaseMessageModel parsePRDDOKResponseFromAdapter(AdapterResponseModel adapterResponseModel) throws Exception {
        LOGGER.info("Try to parse response from adapter");
        LOGGER.info("Response: " + adapterResponseModel);

        if(adapterResponseModel.getResponse() != null){
            String xml = Base64Util.getXmlFromBase64(adapterResponseModel.getResponse());
            final PRDDOKREGResponse response = XmlUtil.unmarshal(xml, PRDDOKREGResponse.class);
            ResponseMessageModel responseMessageModel = new ResponseMessageModel();
            responseMessageModel.setRequestId(response.getИдЗапрос());
            responseMessageModel.setFnsRequestId(response.getИдЗапросФНС());
            responseMessageModel.setCode(response.getКодОбраб());
            responseMessageModel.setMessageId(adapterResponseModel.getMessageId());
            responseMessageModel.setAttachments(adapterResponseModel.getAttachments());
            return responseMessageModel;
        } else if(adapterResponseModel.getRejects() != null && !adapterResponseModel.getRejects().isEmpty()){
            return createRejectMessageModel(adapterResponseModel);
        } else {
            return createStatusMessageModel(adapterResponseModel);
        }
    }

    /*Статусы ФНС: Запрос*/
    public static STATDOKREGRequest createSTATDOKREGRequest(RequestModel model){
        com.skat.smev.fns.model.statuses.ObjectFactory docsOjectFactory = new com.skat.smev.fns.model.statuses.ObjectFactory();
        STATDOKREGRequest statdokregRequest = docsOjectFactory.createSTATDOKREGRequest();
        statdokregRequest.setИдЗапрос(model.getRequestId());
        statdokregRequest.setИдЗапросФНС(model.getFnsRequestId());
        return statdokregRequest;
    }

    /**
     * Метод выполняет преобразование ответа от СМЭВ-адаптера в формат {@link BaseMessageModel}
     * @param adapterResponseModel ответ от СМЭВ-адаптера по Статусам от ФНС
     * @return формированный ответ для дальнейшей отправки в ВИС
     * @throws Exception
     */
    public static BaseMessageModel parseSTATDOKesponseFromAdapter(AdapterResponseModel adapterResponseModel) throws Exception {
        LOGGER.info("Try to parse response from adapter");
        LOGGER.info("Response: " + adapterResponseModel);

        if(adapterResponseModel.getResponse() != null){
            String xml = Base64Util.getXmlFromBase64(adapterResponseModel.getResponse());
            final STATDOKREGResponse response = XmlUtil.unmarshal(xml, STATDOKREGResponse.class);
            ResponseMessageModel responseMessageModel = new ResponseMessageModel();
            responseMessageModel.setRequestId(response.getИдЗапрос());
            responseMessageModel.setFnsRequestId(response.getИдЗапросФНС());
            responseMessageModel.setStatusDoc(response.getСтатусДок());
            responseMessageModel.setMessageId(adapterResponseModel.getMessageId());
            responseMessageModel.setAttachments(adapterResponseModel.getAttachments());
            return responseMessageModel;
        } else if(adapterResponseModel.getRejects() != null && !adapterResponseModel.getRejects().isEmpty()){
            return createRejectMessageModel(adapterResponseModel);
        } else {
           return createStatusMessageModel(adapterResponseModel);
        }
    }

    /**/
    private static RejectMessageModel createRejectMessageModel(AdapterResponseModel adapterResponseModel){
        RejectMessageModel rejectMessageModel = new RejectMessageModel();
        rejectMessageModel.setMessageId(adapterResponseModel.getMessageId());
        rejectMessageModel.setRejects(adapterResponseModel.getRejects());
        return rejectMessageModel;
    }

    /**/
    private static StatusMessageModel createStatusMessageModel(AdapterResponseModel adapterResponseModel){
        StatusMessageModel statusMessageModel = new StatusMessageModel();
        statusMessageModel.setMessageId(adapterResponseModel.getMessageId());
        statusMessageModel.setDescription(adapterResponseModel.getDescription());
        return statusMessageModel;
    }
}
