package com.fitbit.oauth.providers.OAuth;


import com.fitbit.oauth.adaptors.HttpRequestMessage;
import com.fitbit.oauth.error.AuthErrorMessage;
import com.fitbit.oauth.error.AuthException;
import com.fitbit.oauth.model.ApiClient;
import com.fitbit.oauth.providers.AuthProvider;
import com.fitbit.oauth.providers.OAuth.OAuth1.OAuth1Validator;
import com.fitbit.oauth.service.ApiClientService;
import net.oauth.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author ivanbahdanau
 */
@Service
public class OAuth1Provider implements AuthProvider {

    @Autowired
    private OAuth1Validator oAuthValidator;

    @Autowired
    private ApiClientService apiClientService;

    public boolean validate(HttpServletRequest request) throws AuthException {
        OAuthMessage oAuthMessage = new HttpRequestMessage(request, "http://localapi.fitbit.com:28080/oauth/authorize");
        ApiClient apiClient;
        try {
            apiClient = apiClientService.getByConsumerKey(oAuthMessage.getConsumerKey());
        } catch (IOException e) {
            throw new AuthException(AuthErrorMessage.OAUTH_1_0_CONSUMER_KEY_MISSING);
        }
        try {
            OAuthConsumer oAuthConsumer = new OAuthConsumer(
                    apiClient.getCallback(),
                    apiClient.getConsumerKey(),
                    apiClient.getSecret(),
                    null
            );
            OAuthAccessor oAuthAccessor = new OAuthAccessor(oAuthConsumer);
            oAuthValidator.validateMessage(oAuthMessage, oAuthAccessor);
            return true;
        } catch (OAuthException e) {
            throw new AuthException(convertException(e));
        } catch (Exception e) {
            throw new AuthException(e);
        }
    }

    private AuthErrorMessage convertException(OAuthException oauthException) {
        String oauthMessage = oauthException.getMessage();
        if (oauthMessage.equals(OAuth.Problems.SIGNATURE_INVALID)) {
            return AuthErrorMessage.OAUTH_1_0_SIGNATURE_INVALID;
        } else if (oauthMessage.equals(OAuth.Problems.TIMESTAMP_REFUSED)) {
            // the timestamp was outside of the allowable range
            if (oauthException instanceof OAuthProblemException) {
                OAuthProblemException problem = (OAuthProblemException) oauthException;
                if (problem.getParameters() != null) {
                    //Look for the acceptable timestamp parameter and set it in the body of the error message
                    if (problem.getParameters().containsKey(OAuth.Problems.OAUTH_ACCEPTABLE_TIMESTAMPS)) {
                        String acceptTimestamps = (String) problem.getParameters().get(OAuth.Problems.OAUTH_ACCEPTABLE_TIMESTAMPS);
                        return AuthErrorMessage.OAUTH_1_0_TIMESTAMP_UNACCEPTABLE;
                    }
                }
            }
            return AuthErrorMessage.OAUTH_1_0_TIMESTAMP_REFUSED;
        } else if (oauthMessage.equals(OAuth.Problems.NONCE_USED)) {
            return AuthErrorMessage.OAUTH_1_0_NONCE_USED;
        } else if (oauthMessage.equals(OAuth.Problems.SIGNATURE_METHOD_REJECTED)) {
            return AuthErrorMessage.OAUTH_1_0_SIGNATURE_METHOD_INVALID;
        }
        return AuthErrorMessage.UNAUTHORIZED;
    }

    private String getSignatureNullSafe(OAuthMessage requestMessage) {
        try {
            String signature = requestMessage.getSignature();
            return signature != null ? signature : "";
        } catch (IOException t) {
            return "";
        }
    }

    private String getAccessTokenNullSafe(OAuthMessage requestMessage) {
        try {
            String token = requestMessage.getToken();
            return token != null ? token : "";
        } catch (IOException t) {
            return "";
        }
    }

    private String getSignatureMethodNullSafe(OAuthMessage requestMessage) {
        try {
            String signatureMethod = requestMessage.getSignatureMethod();
            return signatureMethod != null ? signatureMethod + "." : "";
        } catch (IOException t) {
            return "";
        }
    }

}