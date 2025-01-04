package org.vera.amazingFactions.interactions.commands.factions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.vera.amazingFactions.internal.dto.FactionDTO;
import org.vera.amazingFactions.handlers.MessageHandler;
import org.vera.amazingFactions.services.FactionService;

import java.util.Set;

public class FactionList implements CommandExecutor {
    private static FactionService factionService = new FactionService();

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player currentPlayer = (Player) sender;
            Set<FactionDTO> factionList = factionService.getFactions();

            if (factionList.isEmpty()) {
                MessageHandler.sendInfoMessage(currentPlayer, "No factions found");
                return true;
            }

            for (FactionDTO faction : factionList) {

            }

            return true;
        }

        MessageHandler.sendErrorMessage("This command can only be used by players");
        return true;
    }
}
