package study.spring.soketchat.global.websocket;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Message {
    private String message;
    private String Sender;
}
