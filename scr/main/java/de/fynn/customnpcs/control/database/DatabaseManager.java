package main.java.de.fynn.customnpcs.control.database;

import main.java.de.fynn.customnpcs.CustomNPCs;
import main.java.de.fynn.customnpcs.model.state.State;
import main.java.de.fynn.customnpcs.utils.IOUtils;
import main.java.de.fynn.customnpcs.utils.interfaces.Messages;
import main.java.de.fynn.customnpcs.utils.interfaces.SQLStatements;
import net.minecraft.server.v1_16_R1.EntityPlayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author Freddyblitz
 * @version 1.0
 */
public class DatabaseManager {

    //TODO Javadoc

    /*----------------------------------------------ATTRIBUTE---------------------------------------------------------*/

    private static DatabaseManager instance;

    private HashMap<String, PreparedStatement> preparedStatements = new HashMap<>();

    private DatabaseConnector databaseConnector;

    /*--------------------------------------------KONSTRUKTOREN-------------------------------------------------------*/

    /**
     *
     */
    public DatabaseManager(){
        instance = this;
        try {
            databaseConnector = new DatabaseConnector();
            init();
        } catch (SQLException e) {
            e.printStackTrace();
            IOUtils.printError(Messages.DATABASE_CONNECTOR_CREATE_ERROR);
            CustomNPCs.getPlugin().onDisable();
        }
    }

    /*----------------------------------------------METHODEN----------------------------------------------------------*/

    /**
     * Erstellt alle prepared Statements
     */
    private void init() throws SQLException {
        preparedStatements.put(SQLStatements.CREATE_NPC, databaseConnector.createPreparedStatement(SQLStatements.CREATE_NPC));
        preparedStatements.put(SQLStatements.LOAD_NPC_OBJECTS, databaseConnector.createPreparedStatement(SQLStatements.LOAD_NPC_OBJECTS));
        preparedStatements.put(SQLStatements.GET_ENTITYPLAYER_FOR_PLAYER, databaseConnector.createPreparedStatement(SQLStatements.GET_ENTITYPLAYER_FOR_PLAYER));
        preparedStatements.put(SQLStatements.SAVE_ENTITY_OBJECT, databaseConnector.createPreparedStatement(SQLStatements.SAVE_ENTITY_OBJECT));
        preparedStatements.put(SQLStatements.DELETE_ENTITY_OBJECT, databaseConnector.createPreparedStatement(SQLStatements.DELETE_ENTITY_OBJECT));
        preparedStatements.put(SQLStatements.LOAD_STATE_LIST, databaseConnector.createPreparedStatement(SQLStatements.LOAD_STATE_LIST));
        preparedStatements.put(SQLStatements.LOAD_CURRENT_STATE, databaseConnector.createPreparedStatement(SQLStatements.LOAD_CURRENT_STATE));
    }

    /**
     *
     * @param parentObject
     */
    public void createNPC(EntityPlayer parentObject){
        this.preparedStatements.get(SQLStatements.CREATE_NPC).execute();
    }

    /**
     *
     * @return
     */
    public List<EntityPlayer> loadNPCObjects(){
        ResultSet resultSet = this.preparedStatements.get(SQLStatements.LOAD_NPC_OBJECTS).executeQuery();
    }

    /**
     *
     * @param npcUUID
     * @param playerUUID
     * @return
     */
    public EntityPlayer getEntiyPlayerObjectForPlayer(UUID npcUUID, UUID playerUUID){
        ResultSet resultSet = this.preparedStatements.get(SQLStatements.GET_ENTITYPLAYER_FOR_PLAYER).executeQuery();
    }

    /**
     *
     * @param npcObject
     */
    public void saveEntityObject(EntityPlayer npcObject){
        this.preparedStatements.get(SQLStatements.SAVE_ENTITY_OBJECT).execute();
    }

    /**
     *
     * @param npcUUID
     */
    public void deleteEntityObject(UUID npcUUID){
        this.preparedStatements.get(SQLStatements.DELETE_ENTITY_OBJECT).execute();
    }

    /**
     *
     * @param npcUUID
     * @return
     */
    public List<State> loadStateList(UUID npcUUID) {
        ResultSet resultSet = this.preparedStatements.get(SQLStatements.LOAD_STATE_LIST).executeQuery();
    }

    /**
     *
     * @param npcUUID
     * @return
     */
    public int loadCurrentState(UUID npcUUID) {
        ResultSet resultSet = this.preparedStatements.get(SQLStatements.LOAD_CURRENT_STATE).executeQuery();
    }

    /*-----------------------------------------GETTER AND SETTER------------------------------------------------------*/

    public static DatabaseManager getInstance() {
        return instance;
    }

}
