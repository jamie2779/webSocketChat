package study.spring.soketchat.global.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.ConcurrentHashMap;


public class WebSocketHandler extends TextWebSocketHandler {
    ConcurrentHashMap<String,User> sessions = new ConcurrentHashMap<>();
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String sessionId = session.getId();
        User newUser = User.builder().session(session).build();
        sessions.put(sessionId,newUser);//세션 저장
        newUser.getSession().sendMessage(new TextMessage(sessionId));

        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String sessionId = session.getId();
        User sendUser = sessions.get(sessionId);
        sessions.values().forEach(s -> {
            try{
                if(s.getNickname() != null){
                    s.getSession().sendMessage(new TextMessage("system:none:"+sendUser.getNickname()+"님이 퇴장하셨습니다."));
                }
            }catch(Exception e){
                sessions.remove(s.getSession().getId());
            }
        });
        sessions.remove(session.getId());
        super.afterConnectionClosed(session, status);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String sessionId = session.getId();
        String text = message.getPayload();
        User sendUser = sessions.get(sessionId);
        if(sendUser.getNickname() == null){
            sendUser.setNickname(text);
            sessions.values().forEach(s -> {
                try{
                    if(s.getNickname() != null){
                        s.getSession().sendMessage(new TextMessage("system:none:"+sendUser.getNickname()+"님이 입장하셨습니다."));
                    }
                }catch(Exception e){
                    sessions.remove(s.getSession().getId());
                }
            });
        }
        else{
        sessions.values().forEach(s -> {
            try{
                if(s.getNickname() != null){
                    s.getSession().sendMessage(new TextMessage(sendUser.getNickname()+":"+sendUser.getSession().getId()+":" + text));
                }
            }catch(Exception e){
                throw new RuntimeException(e);
            }
        });
        }
    }
}
