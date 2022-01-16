package main.java.de.fynn.customnpcs.utils;

import main.java.de.fynn.customnpcs.utils.interfaces.PropertiesFilePaths;
import main.java.de.fynn.customnpcs.utils.interfaces.PropertiesKeys;

import java.util.Scanner;

public class IOUtils {

    private final static String ANSI_DEFAULT_MESSAGE_COLOR;
    private final static String ANSI_WARNING_MESSAGE_COLOR;
    private final static String ANSI_ERROR_MESSAGE_COLOR;

    private final static PropertiesFileUtils FILE_UTILS = new PropertiesFileUtils(PropertiesFilePaths.CONSOLE_COLORS_PROPERTIES);

    static {
        ANSI_DEFAULT_MESSAGE_COLOR = loadDefaultMessageColor();
        ANSI_WARNING_MESSAGE_COLOR = loadWarningMessageColors();
        ANSI_ERROR_MESSAGE_COLOR = loadErrorMessageColors();
    }

    public static void printMessage(String message){
        System.out.println(ANSI_DEFAULT_MESSAGE_COLOR + message);
    }

    public static void printWarning(String message){
        System.out.println(ANSI_WARNING_MESSAGE_COLOR + message + ANSI_DEFAULT_MESSAGE_COLOR);
    }

    public static void printError(String message){
        System.out.println(ANSI_ERROR_MESSAGE_COLOR + message + ANSI_DEFAULT_MESSAGE_COLOR);
    }

    private static String loadDefaultMessageColor(){
        return FILE_UTILS.getFileValue(PropertiesKeys.COLOR_DEFAULT);
    }

    private static String loadWarningMessageColors(){
        return FILE_UTILS.getFileValue(PropertiesKeys.COLOR_WARNING);
    }

    private static String loadErrorMessageColors(){
        return FILE_UTILS.getFileValue(PropertiesKeys.COLOR_ERROR);
    }

}
