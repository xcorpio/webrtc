package org.xcorpio.webrtc.server;

import org.java_websocket.WebSocket;

public class Client {

    private Integer id;
    private String roomId;
    private WebSocket socket;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getRoomId() {
        return roomId;
    }
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    public WebSocket getSocket() {
        return socket;
    }
    public void setSocket(WebSocket socket) {
        this.socket = socket;
    }
}
