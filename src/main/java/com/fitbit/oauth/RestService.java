package com.fitbit.oauth;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author ivanbahdanau
 */
@Controller
@RequestMapping("/oauth/**")
public class RestService {


    @RequestMapping(value = "{userId}/{sayingHello}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody HelloWorldModel sayHello(@PathVariable String userId, @PathVariable String sayingHello) {
        return new HelloWorldModel(userId, sayingHello);
    }
}
