package com.skat.smev.fns.domain;

import java.util.List;

/**
 *  Модель запроса в СМЭВ3 адаптер
 */
public class AdapterRequestModel {

    /* Тело xml-запроса  формате base64 */
    private String xml;

    /* Вложенные файлы */
    private List<AttachmentModel> attachments;

    public String getXml() {
        return xml;
    }
    public void setXml(String xml) {
        this.xml = xml;
    }
    public List<AttachmentModel> getAttachments() {
        return attachments;
    }
    public void setAttachments(List<AttachmentModel> attachments) {
        this.attachments = attachments;
    }


}
