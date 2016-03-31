package org.xcorpio.webrtc.server;

import org.java_websocket.WebSocket;

public class Client {

    private String id;
    private String roomId;
    private WebSocket socket;

    public Client() {
    };

    public Client(String id, String roomId, WebSocket socket) {
        this.id = id;
        this.roomId = roomId;
        this.socket = socket;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
