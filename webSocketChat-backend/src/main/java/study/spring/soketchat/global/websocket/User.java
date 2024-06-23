package study.spring.soketchat.global.websocket;

import lombok.*;
import org.springframework.web.socket.WebSocketSession;

@Getter
@Setter
@Builder
public class User {
    private String nickname;
    private WebSocketSession session;
}
