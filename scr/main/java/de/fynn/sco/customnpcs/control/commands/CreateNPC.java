package main.java.de.fynn.sco.customnpcs.control.commands;

import main.java.de.fynn.sco.customnpcs.control.npc.NPCRegistry;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Freddyblitz
 * @version 1.0
 */
public class CreateNPC implements CommandExecutor {

    /**
     * Dieser command erzeugt einen neuen ClientsideNPC
     * @param sender der Spieler oder die Console, der bzw. die diesen Command ausgefuehrt hat
     * @param command der Befehl selbst
     * @param label die Bezeichnung des Befehls
     * @param args die Argumente, die dem Befehl mit uebergeben wurde
     * @return Wahrheitswert, ob der Befehl ausgefuehrt werden konnte
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (sender.hasPermission("customNPC.create")){     //TODO Magicnumber
                if (args.length == 1){
                    NPCRegistry.getInstance().createNPC((Player) sender, args[0]);
                    return true;
                }
            }
        }
        return false;
    }

}
