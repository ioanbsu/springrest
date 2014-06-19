package com.fitbit.oauth;

import com.fitbit.oauth.error.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ivanbahdanau
 */
@Controller
@RequestMapping("/oauth/**")
public class RestService {


    @Autowired
    private AuthService authService;

    @RequestMapping(value = "authorize")
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    String sayHello(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (authService.validate(request)) {
                return "oauth_token=c5a8b2ff2a20524381083b1fe172fdc1&oauth_token_secret=8508e7c450fc2462ae4932fa63c35b30&oauth_callback_confirmed=true";
            }
        } catch (AuthException e) {
            return "fucked up";
        }
        return "failed";
    }

}
