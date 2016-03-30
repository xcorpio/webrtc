package org.xcorpio.webrtc.server;

import com.fasterxml.jackson.databind.JsonNode;

public class TestJsonUtils {

    public static void main(String[] args) {
        String content = "{\"eventName\":\"__join\",\"data\":{\"room\":\"\"}}";
        JsonNode node = JsonUtils.parse(content);
        long startTime = System.nanoTime();
        node = JsonUtils.parse(content);
        System.out.println(node);
        System.out.println("time takes :" + (System.nanoTime() - startTime) + "ns");
    }
}
