package main.java.de.fynn.customnpcs.control.state;

import main.java.de.fynn.customnpcs.control.database.DatabaseManager;
import main.java.de.fynn.customnpcs.model.npc.ClientSideNPC;
import main.java.de.fynn.customnpcs.model.state.State;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Freddyblitz
 * @version 1.0
 */
public class StateController {

    /*----------------------------------------------ATTRIBUTE---------------------------------------------------------*/

    private final ClientSideNPC npc;

    private final List<State> stateList = new ArrayList<>();

    private int currentState = 0;

    private final DatabaseManager databaseManager = DatabaseManager.getInstance();

    /*--------------------------------------------KONSTRUKTOREN-------------------------------------------------------*/

    /**
     * Der Statecontroller bracht zum erstellen den ClientsideNPC des Zustaende er laden und verwalten soll
     * @param npc der zu verwaltende NPC
     */
    public StateController(ClientSideNPC npc){
        this.npc = npc;
        this.stateList.addAll(this.loadStatesFromDatabase());
        this.currentState = this.loadCurrentStateFromDatabase();
    }

    /*----------------------------------------------METHODEN----------------------------------------------------------*/

    /**
     * Laedt alle States des NPCs aus der Datenbank
     * @return alle Zustaende des NPCs als Liste
     */
    private List<State> loadStatesFromDatabase(){
        return this.databaseManager.loadStateList(this.npc.getNpcUUID());
    }

    /**
     * Holt sich den aktuellen Status, den der NPC in der Datenbank hat
     * @return der index des aktuellen Status
     */
    private int loadCurrentStateFromDatabase(){
         return this.databaseManager.loadCurrentState(this.npc.getNpcUUID());
    }

    /**
     * Diese Methode geht zum naechsten Schritt ueber und passt sowohl die Position des NPCs
     * als auch den aktuellen Questschritt im Bezug auf den NPC an
     */
    public void nextState(){
        this.currentState++;
        //TODO NPC auf Position aus dem Stateobjekt setzen und den zugehoerigen Questschritt laden
    }

}
