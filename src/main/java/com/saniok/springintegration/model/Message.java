package com.saniok.springintegration.model;

import java.io.Serializable;

/**
 * Created by Oleksandr_Tertyshnyi on 1/6/2017.
 */
public class Message implements Serializable {
    private String headers;
    private String body;

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
