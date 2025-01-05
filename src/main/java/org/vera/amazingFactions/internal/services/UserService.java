package org.vera.amazingFactions.internal.services;

import org.bukkit.entity.Player;
import org.vera.amazingFactions.internal.dao.UserDAO;
import org.vera.amazingFactions.internal.dto.UserDTO;

import java.util.UUID;

public class UserService {
    private final UserDAO userDAO = new UserDAO();

    public UserDTO getUser(Player player) {
        UUID uuid = player.getUniqueId();

        return userDAO.getUser(player, uuid);
    }
}
