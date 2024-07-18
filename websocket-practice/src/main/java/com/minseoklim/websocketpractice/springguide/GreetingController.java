package com.minseoklim.websocketpractice.springguide;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class GreetingController {
    private final AsyncService asyncService;

    @MessageMapping("/hello")
    public Greeting greeting(HelloMessage message, Principal principal) throws Exception {
        asyncService.executeAsync(principal.getName(), message);
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}
