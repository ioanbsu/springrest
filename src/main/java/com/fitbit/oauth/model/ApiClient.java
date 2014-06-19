package com.fitbit.oauth.model;

/**
 * @author ivanbahdanau
 */

import javax.persistence.*;

/**
 * Created by dchen on 6/14/14.
 */
@Entity
@Table(name = "api_client")
public class ApiClient {
    @Id
    public long id;

    @Column(name = "consumer_key")
    private String consumerKey;

    @Column
    private String secret;

    @Column
    private String callback;

    public long getId() {
        return id;
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public String getSecret() {
        return secret;
    }

    public String getCallback() {
        return callback;
    }
}
