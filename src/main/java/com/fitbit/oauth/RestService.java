package com.fitbit.oauth;

import com.fitbit.oauth.error.AuthException;
import com.fitbit.oauth.model.RequestResult;
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

    @RequestMapping(value = "authorize", produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    RequestResult sayHello(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (authService.validate(request)) {
                return new RequestResult("Success");
            }
        } catch (AuthException e) {
            return new RequestResult("Failed " + e.getCause());
        }
        return new RequestResult("Failed");
    }

}
