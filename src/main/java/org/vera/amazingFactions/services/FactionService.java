package org.vera.amazingFactions.services;

import org.vera.amazingFactions.dao.FactionDAO;
import org.vera.amazingFactions.dto.FactionDTO;

import java.util.Set;
import java.util.UUID;

public class FactionService {
    private static final FactionDAO factionDAO = new FactionDAO();

    public static void createFaction(String name, String description, Set<UUID> users, UUID leader) {
        FactionDTO newFaction = new FactionDTO(name, description, users, leader);
        factionDAO.createFaction(newFaction);
    }
}
