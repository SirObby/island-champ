package net.sirobby.mods.islandchamp.Websocket;

import net.minecraft.client.MinecraftClient;
import net.sirobby.mods.islandchamp.Utils.Module;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WebsocketModule extends Module {

    @Override
    public void init(MinecraftClient mc) {
        // This starts the websocket interaction with our servers

        System.out.println("Connecting to the island-champ server.");

        Client.connect();

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            try {
                if(SocketServer.users.size() == 0) {
                    Client.connect();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }, 10, 10, TimeUnit.SECONDS);

    }
}
