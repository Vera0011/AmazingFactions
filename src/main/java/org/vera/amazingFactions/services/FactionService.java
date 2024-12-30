package org.vera.amazingFactions.services;

import org.vera.amazingFactions.daos.FactionDAO;
import org.vera.amazingFactions.dto.FactionDTO;

import java.util.Set;

public class FactionService {
    private static final FactionDAO factionDAO = new FactionDAO();

    public static void createFaction(String name, String description, Set<String> users, String leader) {
        FactionDTO newFaction = new FactionDTO(name, description, users, leader);
        factionDAO.createFaction(newFaction);
    }
}
