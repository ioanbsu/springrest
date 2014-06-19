package com.fitbit.oauth.error;

import javax.servlet.http.HttpServletResponse;

/**
 * @author ivanbahdanau
 */

public enum AuthErrorMessage {
    ///oauth 1.0 errors
    OAUTH_1_0_TEMPORARY_TOKEN_INVALID(HttpServletResponse.SC_UNAUTHORIZED, "oauth_access_token", "Invalid OAuth temporary token"),
    OAUTH_1_0_TEMPORARY_TOKEN_NOT_VERIFIED(HttpServletResponse.SC_UNAUTHORIZED, "oauth_access_token", "permission_denied"),
    OAUTH_1_0_ACCESS_TOKEN_MISSING(HttpServletResponse.SC_UNAUTHORIZED, "oauth_access_token", "This endpoint should be signed with user's access token and secret"),
    OAUTH_1_0_ACCESS_TOKEN_EXPIRED(HttpServletResponse.SC_UNAUTHORIZED, "oauth_access_token", "Invalid/expired user token: %s"),
    OAUTH_1_0_CONSUMER_KEY_MISSING(HttpServletResponse.SC_UNAUTHORIZED, "oauth_consumer_key", "Invalid consumer key"),
    OAUTH_1_0_CONSUMER_KEY_NOT_FOUND(HttpServletResponse.SC_UNAUTHORIZED, "oauth_consumer_key", "Invalid consumer key: %s"),
    OAUTH_1_0_SIGNATURE_INVALID(HttpServletResponse.SC_UNAUTHORIZED, "oauth_signature", "Invalid signature: %s"),
    OAUTH_1_0_SIGNATURE_OR_TOKEN_INVALID(HttpServletResponse.SC_UNAUTHORIZED, "oauth_access_token", "Invalid signature or token '%s' or token '%s'"),
    OAUTH_1_0_TIMESTAMP_UNACCEPTABLE(HttpServletResponse.SC_UNAUTHORIZED, "oauth_timestamp", "oauth_acceptable_timestamps=%s"),
    OAUTH_1_0_TIMESTAMP_REFUSED(HttpServletResponse.SC_UNAUTHORIZED, "oauth_timestamp", "oauth_problem=timestamp_refused"),
    OAUTH_1_0_NONCE_USED(HttpServletResponse.SC_UNAUTHORIZED, "oauth_nonce", "oauth_problem=nonce_used"),
    OAUTH_1_0_SIGNATURE_METHOD_INVALID(HttpServletResponse.SC_UNAUTHORIZED, "oauth_signature_method", "Invalid signature method: %s. Fitbit API currently supports: HMAC-SHA1"),
    OAUTH_1_0_BODY_HASH_INVALID(HttpServletResponse.SC_UNAUTHORIZED, "oauth_body_hash", "Invalid body hash."),
    OAUTH_1_0_BODY_HASH_MISSING(HttpServletResponse.SC_UNAUTHORIZED, "oauth_body_hash", "This endpoint requires a body hash."),
    ///oauth 2.0 errors
    OAUTH_2_0_CLIENT_ID_MISSING(HttpServletResponse.SC_UNAUTHORIZED, "client_id", "Empty client key"),
    OAUTH_2_0_CLIENT_ID_NOT_FOUND(HttpServletResponse.SC_UNAUTHORIZED, "client_id", "Client key not found: %s"),
    OAUTH_2_0_CLIENT_ID_TOKEN_MISMATCH(HttpServletResponse.SC_UNAUTHORIZED, "Unathorized", "Client authorization data does not match access token"),
    OAUTH_2_0_CLIENT_SECRET_INVALID(HttpServletResponse.SC_UNAUTHORIZED, "client_secret", "Client secret invalid"),
    OAUTH_2_0_HEADER_FORMAT_INVALID(HttpServletResponse.SC_UNAUTHORIZED, "Authorization", "Invalid authorization header format: %s"),
    OAUTH_2_0_ACCESS_TOKEN_MISSING(HttpServletResponse.SC_UNAUTHORIZED, "access_token", "Access token required"),
    OAUTH_2_0_ACCESS_TOKEN_INVALID(HttpServletResponse.SC_UNAUTHORIZED, "access_token", "Access token invalid or expired: %s"),
    OAUTH_2_0_GRANT_TYPE_INVALID(HttpServletResponse.SC_UNAUTHORIZED, "grant_type", "Invalid Grant Type"),
    OAUTH_2_0_GRANT_TYPE_UNSUPPORTED(HttpServletResponse.SC_UNAUTHORIZED, "grant_type", "Unsupported Grant Type: %s"),
    OAUTH_2_0_USER_CREDENTIALS_INVALID(HttpServletResponse.SC_UNAUTHORIZED, "invalid user name/password", "Invalid username/password"),
    OAUTH_2_0_UNAVAILABLE(HttpServletResponse.SC_UNAUTHORIZED, "oauth 2 not available", "OAuth 2.0 is available only for partners"),
    //general errors
    OAUTH_DATA_NOT_FOUND(HttpServletResponse.SC_BAD_REQUEST, "valid error should be here", "No Authorization header provided in the request. Each call to Fitbit API should be OAuth signed"),
    REQUEST_CLIENT_READ_ONLY(HttpServletResponse.SC_UNAUTHORIZED, "valid error should be here", "Read-only API client is not authorized to update resources"),
    REQUEST_CLIENT_ACCESS_VIOLATION(HttpServletResponse.SC_UNAUTHORIZED, "valid error should be here", "API client is not authorized to access the resource requested."),
    REQUEST_DOMAIN_INVALID(HttpServletResponse.SC_BAD_REQUEST, "valid error should be here", "OAuth request to invalid domain: %s"),
    REQUEST_HTTPS_REQUIRED(HttpServletResponse.SC_BAD_REQUEST, "valid error should be here", "This request should use https protocol."),
    REQUEST_FEATURE_ACCESS_DENIED(HttpServletResponse.SC_BAD_REQUEST, "valid error should be here", "You should be partner to make this call."),
    UNAUTHORIZED(HttpServletResponse.SC_UNAUTHORIZED, "n/a", "%s"),
    NO_AUTHORIZATION_HEADER(HttpServletResponse.SC_BAD_REQUEST, "n/a", "No Authorization header provided in the request. Each call to the Fitbit API should be OAuth signed."),
    //field validation errors
    VALIDATION_OWNER_ID_INVALID(HttpServletResponse.SC_BAD_REQUEST, "resource owner", "Ghost user! Please, provide valid user id in the resource path. Received: %s");


    private final int httpResponseStatus;
    private final String fieldName;
    private final String messagePattern;

    private AuthErrorMessage(int httpResponseStatus, String fieldName, String messagePattern) {
        this.fieldName = fieldName;
        this.messagePattern = messagePattern;
        this.httpResponseStatus = httpResponseStatus;
    }

    public String getMessage() {
        return messagePattern;
    }

    public String getMessage(String msgArg) {
        return String.format(messagePattern, msgArg);
    }

    public String getMessage(String msgArg1, String msgArg2) {
        return String.format(messagePattern, msgArg1, msgArg2);
    }

    public String getMessage(Object... msgArgs) {
        return String.format(messagePattern, msgArgs);
    }


    public String getFieldName() {
        return fieldName;
    }

    public String getMessagePattern() {
        return messagePattern;
    }

    public int getHttpResponseStatus() {
        return httpResponseStatus;
    }

}