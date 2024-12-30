package org.vera.amazingFactions.services;

import org.vera.amazingFactions.dao.FactionDAO;
import org.vera.amazingFactions.dto.FactionDTO;
import org.vera.amazingFactions.dto.UserDTO;

import java.sql.SQLException;
import java.util.Set;
import java.util.UUID;

public class FactionService {
    private final FactionDAO factionDAO = new FactionDAO();

    public void createFaction(String name, String description, Set<UUID> users, UUID leader) {
        FactionDTO newFaction = new FactionDTO(name, description, users, leader);
        factionDAO.createFaction(newFaction);
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
    }*/
}
