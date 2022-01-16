package main.java.de.fynn.customnpcs.control.eventlistener;

import main.java.de.fynn.customnpcs.control.npc.NPCRegistry;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event){
        NPCRegistry.getInstance().addOnlinePlayer(event.getPlayer());
    }

}
