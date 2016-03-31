package org.xcorpio.webrtc.server;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Room {

    private static Logger logger = LoggerFactory.getLogger(Room.class);
    private String id;
    private Map<String, Client> clients = new ConcurrentHashMap<String, Client>();
    private int clientsLimit;

    public Room(String id) {
        clientsLimit = Config.DEFAULT_ROOM_CLIENTS_LIMIT;
    }

    public Room(String id, int clientsLimit) {
        this.setClientsLimit(clientsLimit);
    }

    public Client addClient(String id, Client client) {
        if (isRoomFull()) {
            logger.error("the room {} is already full", id);
            return null;
        } else {
            return clients.put(id, client);
        }
    }

    public Client removeClient(String id) {
        return clients.remove(id);
    }

    public void broadcastToAll(String message) {
        for (Client client : clients.values()) {
            client.getSocket().send(message);
        }
    }

    public void broadcastToAllExcept(String message, Client... exclusions) {
        for (Client client : clients.values()) {
            if (!isClientIn(client, exclusions)) {
                client.getSocket().send(message);
            }
        }
    }

    public boolean isClientIn(Client client, Client... exclusions) {
        for (Client exclusion : exclusions) {
            if (client.equals(exclusion)) {
                return true;
            }
        }
        return false;
    }

    public boolean isRoomFull() {
        return clients.size() >= clientsLimit;
    }

    public Set<String> getAllClientId() {
        return clients.keySet();
    }

    public int getClientsLimit() {
        return clientsLimit;
    }

    public void setClientsLimit(int clientsLimit) {
        this.clientsLimit = clientsLimit;
    }

    public int getClientCount() {
        return clients.size();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
