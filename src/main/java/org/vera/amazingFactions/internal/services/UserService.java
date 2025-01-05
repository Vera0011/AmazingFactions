package org.vera.amazingFactions.internal.services;

import org.vera.amazingFactions.internal.dao.UserDAO;
import org.vera.amazingFactions.internal.dto.UserDTO;

import java.util.Set;
import java.util.UUID;

public class UserService {
    private  final UserDAO userDAO = new UserDAO();

    public UserDTO getUserByUUID(UUID uuid) {

    }

    public Set<UserDTO> getUsersFromFaction(int factionId){

    }
}
