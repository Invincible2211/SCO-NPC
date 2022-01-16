package main.java.de.fynn.customnpcs;

import main.java.de.fynn.customnpcs.control.commands.CreateNPC;
import main.java.de.fynn.customnpcs.utils.IOUtils;
import main.java.de.fynn.customnpcs.utils.interfaces.Messages;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomNPCs extends JavaPlugin {

    private static Plugin plugin;

    public void onEnable(){
        IOUtils.printMessage(Messages.ENABLE_PLUGIN);
        plugin = this;

        this.getCommand("CREATE_NPC").setExecutor(new CreateNPC());
    }

    public void onDisable(){
        IOUtils.printMessage(Messages.DISABLE_PLUGIN);
    }

    public static Plugin getPlugin() {
        return plugin;
    }

}
