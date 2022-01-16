package main.java.de.fynn.sco.customnpcs.utils;

import main.java.de.fynn.sco.customnpcs.CustomNPCs;

import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtils {

    private final Properties PROPERTIES = new Properties();

    public PropertiesFileUtils(String path){
        try {
            this.PROPERTIES.load(CustomNPCs.class.getClassLoader().getResourceAsStream(path));
        } catch (IOException e) {
            IOUtils.printError(e.getMessage());
            e.printStackTrace();
        }
    }

    public String getFileValue(String key){
        return PROPERTIES.getProperty(key);
    }

    public boolean containsKey(String key){
        return PROPERTIES.containsKey(key);
    }

}
