package main.java.de.fynn.customnpcs.control.npc;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_16_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R1.CraftServer;
import org.bukkit.craftbukkit.v1_16_R1.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * @author Freddyblitz
 * @version 1.0
 */
public class EntityPlayerFactory {

    /*----------------------------------------------METHODEN----------------------------------------------------------*/

    /**
     * Diese Methode wird verwendet, um ein neues EntityPlayer Objekt für die erstellung von neuen NPCs zu erstellen.
     * Hinweis: Das EntityPlayer Objekt, was den NPC selbst repraesentiert,
     * sollte mit der anderen {@link #createNPC(EntityPlayer, UUID)} Methode erstellt werden.
     * @param creator Der Spieler, welcher den NPC erstellt
     * @param name Der name des NPCs
     * @return ein EntityPlayer Object, welches für die erzeugung von NPCs genutzt wird
     */
    protected static EntityPlayer createNPC(Player creator, String name){
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer world = ((CraftWorld) Bukkit.getWorld(creator.getWorld().getName())).getHandle();
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), name);
        EntityPlayer npc = new EntityPlayer(server, world, gameProfile, new PlayerInteractManager(world));
        npc.setLocation(creator.getLocation().getX(), creator.getLocation().getY(), creator.getLocation().getZ(),
                creator.getLocation().getYaw(), creator.getLocation().getPitch());
        return npc;
    }

    /**
     * Erstellt ein neues EntityPlayer Objekt mit einem EntityPlayer Objekt als vorlage.
     * Diese Methode wird fuer die Erstellung der EntityPlayer Objekte in der NPC Klasse benutzt
     * @param parent Das EnitiyPlayer Objekt, von dem die Position und ausrichtung uebernommen werden soll
     * @param npcUUID Die UUID des NPCs der diesen EntityPlayer benutzen soll
     * @return ein neues EntityPlayer Objekt, welches die Position und Ausrichtung vom uebergebenen EntityPlayer
     * Objekt uebernommen hat
     */
    protected static EntityPlayer createNPC(EntityPlayer parent, UUID npcUUID){
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer world = ((CraftWorld) Bukkit.getWorld(parent.getWorld().getWorld().getName())).getHandle();
        GameProfile gameProfile = new GameProfile(npcUUID, parent.getName());
        EntityPlayer npc = new EntityPlayer(server, world, gameProfile, new PlayerInteractManager(world));
        Entity parentEntity = (Entity) parent;
        npc.setLocation(parentEntity.getLocation().getX(), parentEntity.getLocation().getY(), parentEntity.getLocation().getZ(),
                parentEntity.getLocation().getYaw(), parentEntity.getLocation().getPitch());
        return npc;
    }

}
