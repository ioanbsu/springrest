package com.fitbit.oauth.service;

import com.fitbit.oauth.error.AuthErrorMessage;
import com.fitbit.oauth.error.AuthException;
import com.fitbit.oauth.model.ApiClient;
import com.fitbit.oauth.repository.ApiClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author ivanbahdanau
 */
@Service
public class ApiClientService {
    @Autowired
    private ApiClientRepository apiClientRepository;

    @Transactional
    @Cacheable("default")
    public ApiClient getByConsumerKey(String consumerKey) throws AuthException {
        if (consumerKey == null || consumerKey.length() == 0) {
            throw new AuthException(AuthErrorMessage.OAUTH_1_0_CONSUMER_KEY_MISSING);
        }
        return apiClientRepository.findByConsumerKey(consumerKey);
    }
}

