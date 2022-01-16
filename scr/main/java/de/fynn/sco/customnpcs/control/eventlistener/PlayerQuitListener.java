package main.java.de.fynn.sco.customnpcs.control.eventlistener;

import main.java.de.fynn.sco.customnpcs.control.npc.NPCRegistry;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        NPCRegistry.getInstance().removeOnlinePlayer(event.getPlayer());
    }

}
