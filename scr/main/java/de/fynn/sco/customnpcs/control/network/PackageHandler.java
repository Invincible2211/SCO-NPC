package main.java.de.fynn.sco.customnpcs.control.network;

import main.java.de.fynn.sco.customnpcs.model.npc.ClientSideNPC;
import net.minecraft.server.v1_16_R1.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_16_R1.PlayerConnection;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;

/**
 * @author Freddyblitz
 * @version 1.0
 */
public class PackageHandler {

    /**
     * Spawnt den NPC fuer den jeweils zugeordneten Spieler
     * @param npc der Npc, der gespawnt werden soll
     */
    public static void spawnNPC(ClientSideNPC npc){
        PlayerConnection connection = ( (CraftPlayer) npc.getOwner()).getHandle().playerConnection;
        connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc.getNpc()));
    }

}
