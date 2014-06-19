package com.fitbit.oauth.providers.OAuth.OAuth1;

import net.oauth.OAuthMessage;
import net.oauth.OAuthProblemException;
import net.oauth.SimpleOAuthValidator;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;


@Service
public class OAuth1Validator extends SimpleOAuthValidator {

    @Override
    protected Date validateNonce(OAuthMessage message, long timestamp, long currentTimeMsec) throws IOException,
            OAuthProblemException {
        return null;
    }
}