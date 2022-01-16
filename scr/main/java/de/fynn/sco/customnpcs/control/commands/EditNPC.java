package main.java.de.fynn.sco.customnpcs.control.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * @author Freddyblitz
 * @version 1.0
 */
public class EditNPC implements CommandExecutor {

    /**
     * Dieser command bietet Moeglichkeiten, bereits gespawnte NPCs zu bearbeiten
     * @param sender der Spieler oder die Console, der bzw. die diesen Command ausgefuehrt hat
     * @param command der Befehl selbst
     * @param label die Bezeichnung des Befehls
     * @param args die Argumente, die dem Befehl mit uebergeben wurde
     * @return Wahrheitswert, ob der Befehl ausgefuehrt werden konnte
     */
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        return false;
    }

}
