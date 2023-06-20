package com.example.socketStudy.webserver;

import jakarta.websocket.OnOpen;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;

import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(value = "/chat")
@Service
@Slf4j
public class WebSocketChatting {
    private static Set<Session> CLIENTS = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {
        log.info(session.toString());
        if(CLIENTS.contains(session)) {
            log.info("이미 연결되 세션입니다. > " + session);
        } else {
            CLIENTS.add(session);
            log.info("새로운 세션입니다 > " + session);
        }
    }

    @OnClose
    public void onClose(Session session) throws Exception {
        CLIENTS.remove(session);
        log.info("세션을 닫습니다 : " + session);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws Exception {
        log.info("입력된 메시지입니다. > " + message);

        for(Session client: CLIENTS) {
            log.info("메시지를 전달합니다. > " + message);
            client.getBasicRemote().sendText(message);
        }
    }
}
