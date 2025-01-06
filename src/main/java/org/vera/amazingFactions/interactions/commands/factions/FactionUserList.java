package org.vera.amazingFactions.interactions.commands.factions;

import org.bukkit.command.CommandExecutor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.vera.amazingFactions.AmazingFactions;
import org.vera.amazingFactions.interactions.menus.users.UserListMenu;
import org.vera.amazingFactions.internal.dto.FactionDTO;
import org.vera.amazingFactions.handlers.MessageHandler;
import org.vera.amazingFactions.internal.dto.UserDTO;
import org.vera.amazingFactions.internal.services.FactionService;

import java.util.Set;

public class FactionUserList implements CommandExecutor {
    private static FactionService factionService = new FactionService();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player currentPlayer = (Player) sender;

            FactionDTO currentFaction = factionService.getFaction(currentPlayer);

            if (currentFaction != null) {
                Set<UserDTO> factionUsers = factionService.getUsers(currentPlayer, currentFaction);

                if (factionUsers != null && !factionUsers.isEmpty()) {
                    UserListMenu menu = new UserListMenu(factionUsers);
                    AmazingFactions.menus.add(menu);
                    menu.open(currentPlayer);
                }
            }

            return true;
        }

        MessageHandler.sendErrorMessage("This command can only be used by players");
        return true;
    }
}
