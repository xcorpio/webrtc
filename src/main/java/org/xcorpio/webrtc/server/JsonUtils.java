package org.xcorpio.webrtc.server;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    static {
        objectMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    }

    public static String stringify(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.warn("object to json string is failed : {}", e.getMessage());
        }
        return "{}";
    }

    public static JsonNode parse(String content) {
        try {
            return objectMapper.readTree(content);
        } catch (IOException e) {
            logger.warn("string to object is failed : {}", e.getMessage());
        }
        return null;
    }
}
