package com.yongin.complaint.Service.WebSocket.Impl;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler {
     HashMap<String, WebSocketSession> sessionHashMap = new HashMap<>(); // 웹소켓 세션을 담아둘 맵
//    private List<HashMap<String, Object>> roomListSessions = new ArrayList<>(); // 웹소켓 세션을 담아둘 리스트

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 소켓 연결시
        super.afterConnectionEstablished(session);
        sessionHashMap.put(session.getId(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 소켓 종료시
        sessionHashMap.remove(session.getId());
        super.afterConnectionClosed(session, status);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        // 메시지 발송시
        String msg = message.getPayload();

        for(String key : sessionHashMap.keySet()) {
            WebSocketSession wss = sessionHashMap.get(key);
            try {
                wss.sendMessage(new TextMessage(msg));
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

//        JSONObject jsonObject = jsonToObjectParser(msg);
//
//        // 채팅중인 방번호
//        String roomID = (String)jsonObject.get("roomId");
//        HashMap<String, Object> tempSession = new HashMap<String, Object>();
//
//        // 채팅방이 존재할 때
//        if(roomListSessions.size() > 0){
//            for(int i = 0; i < roomListSessions.size(); ++i){
//                String tempRoomID = (String)roomListSessions.get(i).get("roomID");
//
//                // 해당하는 방번호를 가진 세션리스트의 Object 검색
//                if(tempRoomID.equals(roomID)){
//                    tempSession = roomListSessions.get(i);
//                    break;
//                }
//
//                for(String key : tempSession.keySet()){
//                    if(key.equals("roomId")) continue; // 방번호 스킵
//
//                    WebSocketSession wss = (WebSocketSession) tempSession.get(key);
//                    if(wss != null){
//                        try {
//                            wss.sendMessage(new TextMessage(jsonObject.toJSONString()));
//                        }
//                        catch (IOException e){
//                            e.printStackTrace();
//                        }
//                    }
//
//                }
//            }
//        }
    }

    private static JSONObject jsonToObjectParser(String jsonStr) {
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        try {
            obj = (JSONObject)parser.parse(jsonStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
