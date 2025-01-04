package org.vera.amazingFactions.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.vera.amazingFactions.dto.FactionDTO;
import org.vera.amazingFactions.handlers.MessageHandler;
import org.vera.amazingFactions.services.FactionService;

public class Delete implements CommandExecutor {
    private static FactionService factionService = new FactionService();

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player currentPlayer = (Player) sender;

        if (currentPlayer != null) {

            if (args.length != 1) {
                MessageHandler.sendInfoMessage(currentPlayer, "Usage: /delete");
                return true;
            }

            FactionDTO faction = factionService.getFactionByLeader(currentPlayer);

            if (faction == null) {
                return true;
            }

            boolean result = factionService.deleteFaction(currentPlayer, faction);

            if (result) {
                MessageHandler.sendInfoMessage(currentPlayer, "The faction has been deleted");
                return true;
            }

            return true;
        }

        MessageHandler.sendErrorMessage("This command can only be used by players");
        return true;
    }
}
