package org.xcorpio.webrtc.server;

import java.net.InetSocketAddress;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignalHandler extends WebSocketServer {

    private static Logger logger = LoggerFactory.getLogger(SignalHandler.class);
    private RoomManager roomManager = new RoomManager();

    public SignalHandler(int port) {
        super(new InetSocketAddress(port));
    }

    public SignalHandler(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        logger.info("onOpen: {}", conn.hashCode());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        logger.info("onClose: {}", conn.hashCode());
        roomManager.removeSocket(conn);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        logger.info("onMessage: {}", message);
        roomManager.eventDispatch(conn, message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        logger.info("WebSocketServer onError: {} client {}", roomManager.getSocketClientMap().get(conn).getId(), ex);
    }

    public static void main(String[] args) {
        int port = 8888;
        SignalHandler signalHandler = new SignalHandler(port);
        signalHandler.start();
        System.out.println("Server listen on :" + port);
    }
}
