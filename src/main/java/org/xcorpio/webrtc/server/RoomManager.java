package org.xcorpio.webrtc.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.java_websocket.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;

public class RoomManager {

    private static Logger logger = LoggerFactory.getLogger(RoomManager.class);
    private static Map<String, Room> rooms = new ConcurrentHashMap<String, Room>();

    public static Room getRoom(String key) {
        return rooms.get(key);
    }

    public static Room clearRoom(String key) {
        return rooms.remove(key);
    }

    public static void clearAllRoom() {
        rooms.clear();
    }

    private static void joinEvent(WebSocket socket, JsonNode data) {

    }

    private static void iceCandidateEvent(WebSocket socket, JsonNode data) {

    }

    private static void offerEvent(WebSocket socket, JsonNode data) {

    }

    private static void answerEvent(WebSocket socket, JsonNode data) {

    }

    public static void eventDispatch(WebSocket socket, String message) {
        JsonNode node = JsonUtils.parse(message);
        String eventMethod = node.get(NodeKey.EVENT_KEY).asText();
        JsonNode data = node.get(NodeKey.DATA_KEY);
        if (NodeKey.EVENT_JOIN.equals(eventMethod)) {
            joinEvent(socket, data);
        } else if (NodeKey.EVENT_ICE_CANDIDATE.equals(eventMethod)) {
            iceCandidateEvent(socket, data);
        } else if (NodeKey.EVENT_OFFER.equals(eventMethod)) {
            offerEvent(socket, data);
        } else if (NodeKey.EVENT_ANSWER.equals(eventMethod)) {
            answerEvent(socket, data);
        } else {
            logger.error("unknown event :{}", eventMethod);
            // TODO socket 返回什么消息
        }
    }
}
