package com.fitbit.oauth;

import com.fitbit.oauth.error.AuthErrorMessage;
import com.fitbit.oauth.error.AuthException;
import com.fitbit.oauth.providers.AuthProvider;
import com.fitbit.oauth.providers.OAuth.OAuth1Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ivanbahdanau
 */
@Service
public class AuthService implements AuthProvider {

    public static final String HEADER_AUTHORIZATION = "Authorization";

    @Autowired
    private OAuth1Provider oAuth1Provider;

    public boolean validate(HttpServletRequest request) throws AuthException {
        if (null == request.getHeader(HEADER_AUTHORIZATION)) {
            throw new AuthException(AuthErrorMessage.NO_AUTHORIZATION_HEADER);
        }
        return oAuth1Provider.validate(request);
    }

}
