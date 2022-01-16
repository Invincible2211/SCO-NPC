package main.java.de.fynn.customnpcs.control.npc;

import main.java.de.fynn.customnpcs.control.database.DatabaseManager;
import main.java.de.fynn.customnpcs.model.npc.ClientSideNPC;
import net.minecraft.server.v1_16_R1.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author Freddyblitz
 * @version 1.0
 */
public class NPCRegistry {

    /*----------------------------------------------ATTRIBUTE---------------------------------------------------------*/

    private final HashMap<Player,List<ClientSideNPC>> playerNPCListHashmap = new HashMap<>();

    private final HashMap<UUID,EntityPlayer> npcObjectHashmap = new HashMap<>();

    private final DatabaseManager databaseManager = new DatabaseManager();

    private static NPCRegistry instance;

    /*--------------------------------------------KONSTRUKTOREN-------------------------------------------------------*/

    /**
     * Beim Erzeugen einer NPCRegistry werden zunaest alle NPCs aus der Datenbank geladen.
     * Danach werden die NPC Objekte fue alle zu diesem Zeitpunkt sich online befindlichen Spieler erzeugt
     */
    public NPCRegistry(){
        loadNPCsFromDatabase();
        initNPCs();
        instance = this;
    }

    /*----------------------------------------------METHODEN----------------------------------------------------------*/

    /**
     * Laedt alle EntityPlayer Objekte ueber den Datenbank Manager und speichert diese in der npcObjekt Liste
     */
    private void loadNPCsFromDatabase(){
        for (EntityPlayer e:
                databaseManager.loadNPCObjects()) {
            npcObjectHashmap.put(e.getUniqueID(), e);
        }
    }

    /**
     * Erzeugt die NPC Objekte fuer die Spieler anhand der zu diesem Zeitpunkt sich online befindlichen Spieler
     */
    private void initNPCs(){
        for (Player player:
             Bukkit.getOnlinePlayers()) {
            List<ClientSideNPC> npcPlayerList = new ArrayList<>();
            for (EntityPlayer npcObjekt:
                 npcObjectHashmap.values()) {
                npcPlayerList.add(new ClientSideNPC(npcObjekt, player));
            }
            playerNPCListHashmap.put(player, npcPlayerList);
        }
    }

    /**
     * Erzeugt die NPC Objekte fuer Spieler die neu online kommen
     */
    public void addOnlinePlayer(Player player){
        List<ClientSideNPC> npcPlayerList = new ArrayList<>();
        for (EntityPlayer npcObjekt:
                npcObjectHashmap.values()) {
            npcPlayerList.add(new ClientSideNPC(npcObjekt, player));
        }
        playerNPCListHashmap.put(player, npcPlayerList);
    }

    /**
     * Wenn ein Spieler offline geht, wird der Speicher aus der Hashmap entfernt, um Speicher freizugeben
     * @param player der Spieler, der sich ausloggt
     */
    public void removeOnlinePlayer(Player player){
        playerNPCListHashmap.remove(player);
    }

    /**
     * Erstellt ein neuen NPC für jeden Spieler und speichert die NPC Objekte in den NPCListen der Spieler
     * @param creator Der Spieler, der den NPC erstellen moechte
     * @param name Der gewuenschte Name des NPCs
     */
    public void createNPC(Player creator, String name){
        EntityPlayer entityPlayer = EntityPlayerFactory.createNPC(creator, name);
        saveToDatabase(entityPlayer);
        npcObjectHashmap.put(entityPlayer.getUniqueID(), entityPlayer);
        for (Player player:
                Bukkit.getOnlinePlayers()) {
            if (playerNPCListHashmap.get(player) == null){
                List<ClientSideNPC> npcList = new ArrayList<>();
                npcList.add(new ClientSideNPC(entityPlayer, player));
                playerNPCListHashmap.put(player, npcList);
            } else {
                List<ClientSideNPC> npcList =  playerNPCListHashmap.get(player);
                npcList.add(new ClientSideNPC(entityPlayer, player));
            }
        }
    }

    /**
     *
     * @param uuid die UUID des zu loeschenden NPCs
     */
    public void deleteNPC(UUID uuid){
        for (Player player:
             playerNPCListHashmap.keySet()) {
            List<ClientSideNPC> npcPlayerList = playerNPCListHashmap.get(player);
            npcPlayerList.removeIf(npc -> npc.getEntityUUID() == uuid);
        }
        deleteFromDatabase(uuid);
    }

    /**
     * Speichert das EntityPlayer Objekt in der Datenbank
     */
    private void saveToDatabase(EntityPlayer npcObject){
        databaseManager.saveEntityObject(npcObject);
    }

    /**
     * Loescht ein EntityPlayer Object aus der Datenbank anhand der UUID
     * @param npcObjectUUID
     */
    private void deleteFromDatabase(UUID npcObjectUUID){
        databaseManager.deleteEntityObject(npcObjectUUID);
    }

    /**
     * Setzt ein bestimmten NPC eines bestimmten Spielers in den naechsten Zustand.
     * @param player
     * @param parentNPC
     */
    public void nextState(Player player, UUID parentNPC){
        List<ClientSideNPC> npcList = this.playerNPCListHashmap.get(player);
        for (ClientSideNPC npc:
             npcList) {
            if (npc.getEntityUUID().equals(parentNPC)){
                npc.getController().nextState();
            }
        }
    }

    /*-----------------------------------------GETTER AND SETTER------------------------------------------------------*/

    public static NPCRegistry getInstance() {
        return instance;
    }

}
