package org.vera.amazingFactions.interactions.commands.factions;

import org.bukkit.command.CommandExecutor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.vera.amazingFactions.AmazingFactions;
import org.vera.amazingFactions.interactions.menus.factions.FactionListMenu;
import org.vera.amazingFactions.internal.dto.FactionDTO;
import org.vera.amazingFactions.handlers.MessageHandler;
import org.vera.amazingFactions.internal.services.FactionService;

import java.util.Set;

public class FactionList implements CommandExecutor {
    private static FactionService factionService = new FactionService();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player currentPlayer = (Player) sender;

            Set<FactionDTO> factionList = factionService.getFactionList(currentPlayer);

            if (factionList != null) {
                FactionListMenu menu = new FactionListMenu(factionList);
                AmazingFactions.menus.add(menu);
                menu.open(currentPlayer);

            }

            return true;
        }

        MessageHandler.sendErrorMessage("This command can only be used by players");
        return true;
    }
}
