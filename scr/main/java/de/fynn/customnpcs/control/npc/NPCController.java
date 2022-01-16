package main.java.de.fynn.customnpcs.control.npc;

import main.java.de.fynn.customnpcs.control.database.DatabaseManager;
import main.java.de.fynn.customnpcs.control.state.StateController;
import main.java.de.fynn.customnpcs.model.npc.ClientSideNPC;
import net.minecraft.server.v1_16_R1.EntityPlayer;

import java.util.UUID;

/**
 * @author Freddyblitz
 * @version 1.0
 */
public class NPCController {

    /*----------------------------------------------ATTRIBUTE---------------------------------------------------------*/

    private final ClientSideNPC npc;

    private final StateController stateController;

    private final DatabaseManager databaseManager = DatabaseManager.getInstance();

    /*--------------------------------------------KONSTRUKTOREN-------------------------------------------------------*/

    /**
     * Der Controller bekommt den NPC, fuer den er zustaendig ist, beim erstellen des Controllers uebergeben.
     * @param npc der NPC, den der Controller verwalten soll
     */
    public NPCController(ClientSideNPC npc){
        this.npc = npc;
        this.stateController = new StateController(this.npc);
    }

    /*----------------------------------------------METHODEN----------------------------------------------------------*/

    /**
     * Laedt das EntityPlayer Objekt, welches fuer den Spieler, der zu dem verwalteten NPC gehoert,
     * gespeichert ist
     * @param parentUUID
     * @return
     */
    public EntityPlayer loadFromDatabase(UUID parentUUID){
        return this.databaseManager.getEntiyPlayerObjectForPlayer(parentUUID, npc.getOwner().getUniqueId());
    }

    /**
     * Benutzt die {@link StateController#nextState()} Methode der StateController-Klasse um den NPC in den
     * naechsten Zustand zu bringen.
     */
    public void nextState(){
        this.stateController.nextState();
    }

}
