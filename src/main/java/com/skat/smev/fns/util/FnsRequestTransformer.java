package com.skat.smev.fns.util;


import com.skat.smev.fns.domain.*;
import com.skat.smev.fns.model.docs.ObjectFactory;
import com.skat.smev.fns.model.docs.PRDDOKREGRequest;
import com.skat.smev.fns.model.docs.PRDDOKREGResponse;
import org.apache.log4j.Logger;

public class FnsRequestTransformer {
    private static final Logger LOGGER = Logger.getLogger(FnsRequestTransformer.class.getName());


    public static PRDDOKREGRequest createPRDDOKREGRequest(RequestModel model){
        ObjectFactory docsOjectFactory = new ObjectFactory();
        PRDDOKREGRequest prddokregRequest = docsOjectFactory.createPRDDOKREGRequest();
        prddokregRequest.setИдЗапрос(model.getRequestId());
        prddokregRequest.setВидГосРег(model.getRegType());
        return prddokregRequest;
    }

//    public static STATDOKREGRequest createSTATDOKREGRequest(RequestModel model){
//        com.skat.smev.fns.model.statuses.ObjectFactory docsOjectFactory = new com.skat.smev.fns.model.statuses.ObjectFactory();
//        STATDOKREGRequest statdokregRequest = docsOjectFactory.createSTATDOKREGRequest();
//        statdokregRequest.setИдЗапрос(model.getRequestId());
//        statdokregRequest.setИдЗапросФНС(model.getRegType());
//        return statdokregRequest;
//    }

    /**
     * Метод выполняет преобразование ответа от СМЭВ-адаптера в формат {@link BaseMessageModel}
     * @param adapterResponseModel ответ от СМЭВ-адаптера
     * @return формированный ответ для дальнейшей отправки в ВИС
     * @throws Exception
     */
    public static BaseMessageModel parseResponseFromAdapter(AdapterResponseModel adapterResponseModel) throws Exception {
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
            return responseMessageModel;
        } else if(adapterResponseModel.getRejects() != null && !adapterResponseModel.getRejects().isEmpty()){
            RejectMessageModel rejectMessageModel = new RejectMessageModel();
            rejectMessageModel.setMessageId(adapterResponseModel.getMessageId());
            rejectMessageModel.setRejects(adapterResponseModel.getRejects());
            return rejectMessageModel;
        } else {
            StatusMessageModel statusMessageModel = new StatusMessageModel();
            statusMessageModel.setMessageId(adapterResponseModel.getMessageId());
            statusMessageModel.setDescription(adapterResponseModel.getDescription());
            return statusMessageModel;
        }
    }
}
