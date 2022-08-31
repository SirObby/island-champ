package net.sirobby.mods.islandchamp.Websocket;

import net.minecraft.client.MinecraftClient;

import java.net.URI;

public class Client {

    public static SocketServer client;

    public static void connect() {
        try {
            System.out.println(MinecraftClient.getInstance().getSession().getUsername());

            String url = "wss://island-champ.sirobsidian.repl.co/?username=" + MinecraftClient.getInstance().getSession().getUsername() + "&uuid=" + MinecraftClient.getInstance().getSession().getUuid();
            client = new SocketServer(new URI(url));
            client.connect();
            System.out.println("Attempting to connect.");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void sendMessage(String message) {
        /*
        SUSMOD code, ignore.
        try {
            client.send("§§chat" + message);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

}
