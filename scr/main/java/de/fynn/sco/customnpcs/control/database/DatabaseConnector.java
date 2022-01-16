package main.java.de.fynn.sco.customnpcs.control.database;

import main.java.de.fynn.sco.customnpcs.utils.PropertiesFileUtils;
import main.java.de.fynn.sco.customnpcs.utils.interfaces.PropertiesFilePaths;
import main.java.de.fynn.sco.customnpcs.utils.interfaces.PropertiesKeys;
import main.java.de.fynn.sco.customnpcs.utils.interfaces.Strings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Freddyblitz
 * @version 1.0
 */
public class DatabaseConnector {

    /*----------------------------------------------ATTRIBUTE---------------------------------------------------------*/

    private final PropertiesFileUtils propertiesFileUtils = new PropertiesFileUtils(PropertiesFilePaths.DATABASE_PROPERTIES);

    private Connection connection;

    /*--------------------------------------------KONSTRUKTOREN-------------------------------------------------------*/

    /**
     *Erstellt ein DatabaseConnector Objekt und verbindet sich direkt mit der MySQL Datenbank.
     * @throws SQLException Tritt eine SQl Exception in der {@link #connect()} Methode auf, wird diese weitergegeben
     */
    protected DatabaseConnector() throws SQLException {
        connect();
    }

    /*----------------------------------------------METHODEN----------------------------------------------------------*/

    /**
     * Baut eine Verbindung zur Datenbank auf mithilfe der Werte die in der database.properties Datei stehen.
     * @throws SQLException Wenn der Verbindungsaufbau fehlschlaegt, wird eine SQl Exception geschmissen
     */
    public void connect() throws SQLException {
        connection = DriverManager.getConnection(String.format(Strings.DATABASE_CONNECTION_URL,
                        propertiesFileUtils.getFileValue(PropertiesKeys.DATABASE_IP), propertiesFileUtils.getFileValue(PropertiesKeys.DATABASE_PORT)),
                propertiesFileUtils.getFileValue(PropertiesKeys.DATABASE_USER), propertiesFileUtils.getFileValue(PropertiesKeys.DATABASE_PASSWORD));
    }

    /**
     * Gibt ein PreparedStatement zurueck basierend auf der Connection des DatabaseConnectors.
     * @param sql Das SQL Statement, welches vorbereitet werden soll
     * @return Ein prepared Statement, das den SQL Befehl enthaelt
     * @throws SQLException Treten fehler beim pre-kompilieren auf, wird eine SQL Exception geworfen
     */
    public PreparedStatement createPreparedStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

}
