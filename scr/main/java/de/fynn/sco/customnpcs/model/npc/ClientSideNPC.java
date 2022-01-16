package main.java.de.fynn.sco.customnpcs.model.npc;

import main.java.de.fynn.sco.customnpcs.control.network.PackageHandler;
import main.java.de.fynn.sco.customnpcs.control.npc.NPCController;
import net.minecraft.server.v1_16_R1.EntityPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * @author Freddyblitz
 * @version 1.0
 */
public class ClientSideNPC {

    /*----------------------------------------------ATTRIBUTE---------------------------------------------------------*/

    private final NPCController controller;

    private final UUID entityUUID;
    private final UUID npcUUID;

    private final EntityPlayer parentNPC;
    private final EntityPlayer npc;

    private final Player owner;

    /*--------------------------------------------KONSTRUKTOREN-------------------------------------------------------*/

    /**
     * Der Konstruktor laedt die Attributwerte aus der Datenbank fuer die jeweilige Kombination aus Spieler und
     * EntityyPlayer Object
     * @param npcObject
     * @param owner
     */
    public ClientSideNPC(EntityPlayer npcObject, Player owner){
        this.parentNPC = npcObject;
        this.entityUUID = parentNPC.getUniqueID();
        this.owner = owner;
        PackageHandler.spawnNPC(this);
        this.controller = new NPCController(this);
        this.npc = this.controller.loadFromDatabase(this.parentNPC.getUniqueID());
        this.npcUUID = npcObject.getUniqueID();
    }

    /*-----------------------------------------GETTER AND SETTER------------------------------------------------------*/

    public UUID getEntityUUID() {
        return this.entityUUID;
    }

    public UUID getNpcUUID() {
        return this.npcUUID;
    }

    public Player getOwner() {
        return this.owner;
    }

    public EntityPlayer getNpc() {
        return this.npc;
    }

    public NPCController getController() {
        return controller;
    }

}
