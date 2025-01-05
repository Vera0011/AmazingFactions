package org.vera.amazingFactions.interactions.commands.factions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.vera.amazingFactions.handlers.MessageHandler;
import org.vera.amazingFactions.interactions.menus.factions.MainMenu;
import org.vera.amazingFactions.internal.dto.FactionDTO;
import org.vera.amazingFactions.services.FactionService;

public class FactionMain implements CommandExecutor {
    private static FactionService factionService = new FactionService();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player currentPlayer = (Player) sender;

            FactionDTO userFaction = factionService.getFactionByLeader(currentPlayer);

            if (userFaction != null) {
                MainMenu menu = new MainMenu();
                menu.open(currentPlayer);
            }

            return true;
        }

        MessageHandler.sendErrorMessage("This command can only be used by players");
        return true;
    }
}
