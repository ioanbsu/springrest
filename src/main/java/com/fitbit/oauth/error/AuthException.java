package com.fitbit.oauth.error;

import javax.servlet.http.HttpServletResponse;

/**
 * @author ivanbahdanau
 */
public class AuthException extends Exception {

    private final int httpResponseCode;
    private final String fieldName;
    private final String message;

    public AuthException(AuthErrorMessage authError) {
        httpResponseCode = authError.getHttpResponseStatus();
        fieldName = authError.getFieldName();
        message = authError.getMessage();
    }

    public AuthException(Throwable t) {
        httpResponseCode = HttpServletResponse.SC_UNAUTHORIZED;
        fieldName = "n/a";
        message = t.getMessage();
    }

    public String toResult() {
        String jsonMessage = "{\"errors\":[{\"errorType\":\"request\",\"fieldName\":\"" + fieldName + "\",\"message\":\"" + message + "\"}],\"success\":false}";
        return jsonMessage;
    }
}