package com.minseoklim.websocketpractice.springguide;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

@RequiredArgsConstructor
@Service
public class AsyncService {
    private final SimpMessagingTemplate messagingTemplate;

    @Async
    public void executeAsync(String username, HelloMessage message) throws InterruptedException {
        Thread.sleep(5000);
        messagingTemplate.convertAndSendToUser(username, "/topic/greetings", new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!"));
    }
}
