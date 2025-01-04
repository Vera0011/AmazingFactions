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

    /*public FactionDTO getFactionByLeader(UUID leaderId) throws SQLException {
        return factionDAO.getFactionByLeader(leaderId);
    }

    public FactionDTO getFactionById(int factionId) throws SQLException {
        return factionDAO.getFactionById(factionId);
    }

    public FactionDTO getFactionByName(String factionName) throws SQLException {
        return factionDAO.getFactionByName(factionName);
    }

    public Set<FactionDTO> getFactions() throws SQLException {
        return factionDAO.getFactions();
    }

    public Set<UserDTO> getUsersFromFaction(int factionId) throws SQLException {
        return factionDAO.getUsersFromFaction(factionId);
    }

    public FactionDTO updateFaction(FactionDTO factionDTO) throws SQLException {
        return factionDAO.updateFaction(factionDTO);
    }

    */
    public boolean deleteFaction(Player actualPlayer, int factionId) {
        return factionDAO.deleteFaction(actualPlayer, factionId);
    }
}
