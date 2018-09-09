package com.skat.smev.fns.domain;

import java.util.List;

/**
 * Класс, описывающий модель ответа от СМЭВ3 адаптера
 */
public class AdapterResponseModel {

    /* id исходного запроса */
    private String messageId;

    /* Расшифровка статуса или сообщения об ошибке */
    private String description;

    /* Расшифровка причин, по которым запрос отклонен */
    private List<RejectModel> rejects;

    /* Тело ответа от сервиса вида сведений (xml в base64) */
    private String response;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<RejectModel> getRejects() {
        return rejects;
    }

    public void setRejects(List<RejectModel> rejects) {
        this.rejects = rejects;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "AdapterResponseModel{" +
                "messageId='" + messageId + '\'' +
                ", description='" + description + '\'' +
                ", rejects=" + rejects +
                ", response='" + response + '\'' +
                '}';
    }
}
