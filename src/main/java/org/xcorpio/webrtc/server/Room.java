package org.xcorpio.webrtc.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class Room {
    
    private static Map<Integer, Client> clients = new ConcurrentHashMap<Integer, Client>();

    public static Client addClient(Integer id, Client client) {
        return clients.put(id, client);
    }

    public static Client removeClient(Integer id) {
        return clients.remove(id);
    }

    public static void broadcast(String message) {
        for (Client client : clients.values()) {
            client.getSocket().send(message);
        }
    }
}
