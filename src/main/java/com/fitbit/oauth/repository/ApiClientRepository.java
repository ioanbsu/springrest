package com.fitbit.oauth.repository;

import com.fitbit.oauth.model.ApiClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author ivanbahdanau
 */
public interface ApiClientRepository extends JpaRepository<ApiClient, Long> {

    @Query("FROM ApiClient WHERE consumerKey = :consumerKey")
    public ApiClient findByConsumerKey(@Param("consumerKey") String consumerKey);
}
