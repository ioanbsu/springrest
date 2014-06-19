package com.fitbit.oauth.model;

/**
 * @author ivanbahdanau
 */
public class RequestResult {

    private String result;

    public RequestResult(String result) {
        this.result = result;
    }

    public RequestResult() {
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
