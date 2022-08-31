package net.sirobby.mods.islandchamp.Websocket;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class SocketServer extends WebSocketClient {

    public static JsonArray users = new JsonArray();

    public SocketServer(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {

    }

    @Override
    public void onMessage(String message) {
        System.out.println(message);

        try {
            JsonObject jsonObject = new Gson().fromJson(message, JsonObject.class);

            if(jsonObject.get("type").getAsString().equals("users")) {
                users = jsonObject.get("content").getAsJsonArray();
                System.out.println(users);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        users = new JsonArray();
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    public static int getUserAmount(){
        return users.size();
    }

    public static boolean hasUser(String user) {
        return users.toString().contains("\"username\":\""+user+"\"");
    }
}
