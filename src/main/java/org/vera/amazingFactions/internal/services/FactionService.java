package org.vera.amazingFactions.internal.services;

import org.bukkit.entity.Player;
import org.vera.amazingFactions.internal.dao.FactionDAO;
import org.vera.amazingFactions.internal.dto.FactionDTO;
import org.vera.amazingFactions.internal.dto.UserDTO;

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

    public Set<UserDTO> getUsers(Player actualPlayer, FactionDTO faction) {
        return factionDAO.getUsers(actualPlayer, faction);
    }

    public FactionDTO getFaction(Player actualPlayer) {
        return factionDAO.getFaction(actualPlayer);
    }

    public Set<FactionDTO> getFactions(Player actualPlayer) {
        return factionDAO.getFactions(actualPlayer);
    }
}
