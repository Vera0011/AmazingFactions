package org.vera.amazingFactions.services;

import org.bukkit.entity.Player;
import org.vera.amazingFactions.dao.FactionDAO;
import org.vera.amazingFactions.dto.FactionDTO;
import org.vera.amazingFactions.dto.UserDTO;

import java.sql.SQLException;
import java.util.Set;
import java.util.UUID;

public class FactionService {
    private final FactionDAO factionDAO = new FactionDAO();

    public FactionDTO createFaction(Player actualPlayer, String name, String description, Set<UUID> users, UUID leader) {
        FactionDTO newFaction = new FactionDTO(name, description, users, leader);
        return factionDAO.createFaction(actualPlayer, newFaction);
    }

    public boolean deleteFaction(Player actualPlayer, FactionDTO faction) {
        return factionDAO.deleteFaction(actualPlayer, faction);
    }

    public FactionDTO getFactionByLeader(Player actualPlayer) {
        return factionDAO.getFactionByLeader(actualPlayer, actualPlayer.getUniqueId());
    }
}
