package com.example.socketStudy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Component
public class WebSocketConfig {
    @Bean//websocket bean 생성
    public ServerEndpointExporter serverEndpointExporter() {
//        스프링에서 빈은 싱글톤이지만 ServerEndpoin클래스는 웹소켓이 생성될 때마다 인스턴스가 생성되고
//                JWA에 의해 관리되기 때문에 스프링의 @Autowired가 설정된 멤버들이 초기화 되지 않기때문에
//                연결해주고 초기화해주는 클래스가 필요하다.
        return new ServerEndpointExporter();
    }
}
