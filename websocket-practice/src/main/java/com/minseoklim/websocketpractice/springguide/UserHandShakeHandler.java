package com.minseoklim.websocketpractice.springguide;

import com.sun.security.auth.UserPrincipal;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

public class UserHandShakeHandler extends DefaultHandshakeHandler {

    // 평소에는 그냥 SecurityContextHolder에서 username 뽑아다가 convertAndSendUser 호출하면 될 듯
    // path 가 /topic/greetings -> /user/topic/greetings 로 자동으로 바뀐다는 것 기억해야 함
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        return new UserPrincipal(UUID.randomUUID().toString());
    }
}
