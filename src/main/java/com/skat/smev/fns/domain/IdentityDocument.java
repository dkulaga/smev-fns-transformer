package com.skat.smev.fns.domain;

/**
 * @author daria.kulaga
 * @since 01.06.2018.
 */
public class IdentityDocument {
    private DocType docType;
    private String series;
    private String number;
    private String issueDate;
    private String issuer;

    public DocType getDocType() {
        return docType;
    }

    public String getSeries() {
        return series;
    }

    public String getNumber() {
        return number;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public String getIssuer() {
        return issuer;
    }
}
