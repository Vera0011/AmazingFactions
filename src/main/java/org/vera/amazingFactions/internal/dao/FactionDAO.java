package org.vera.amazingFactions.internal.dao;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.vera.amazingFactions.AmazingFactions;
import org.vera.amazingFactions.internal.dto.FactionDTO;
import org.vera.amazingFactions.handlers.MessageHandler;
import org.vera.amazingFactions.internal.dto.UserDTO;

import java.sql.*;
import java.util.*;

public class FactionDAO {
    private Connection connection;

    public FactionDAO() {
        connection = AmazingFactions.database.getConnection();
    }

    /**
     * Creates a new faction
     *
     * @param actualPlayer The command executor
     * @param factionDTO   The faction to create
     * @return
     */
    public FactionDTO createFaction(Player actualPlayer, FactionDTO factionDTO) {
        String query = "INSERT INTO Factions (name, description, leaderId) VALUES (?, ?, ?)";
        String userQuery = "INSERT INTO Users (id, userName, factionId, factionRank) VALUES (?, ?, ?, ?)";
        int factionId = 0;

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            MessageHandler.sendErrorMessage(actualPlayer, "Database error: Could not modify autocommit");
            MessageHandler.sendErrorMessage("Database error: Could not modify autocommit");
            return null;
        }

        /**
         * Inserts the new faction
         */
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, factionDTO.getName());
            stmt.setString(2, factionDTO.getDescription());
            stmt.setString(3, factionDTO.getLeader().toString());
            stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                factionId = rs.getInt(1);
            }
        } catch (SQLException e) {
            // If the insert fails, that means that the user is already leader of a faction
            try {
                connection.rollback();
                MessageHandler.sendErrorMessage(actualPlayer, "You are already the leader of a faction");
                return null;
            } catch (SQLException ex) {
                MessageHandler.sendErrorMessage(actualPlayer, "Internal error with database");
                MessageHandler.sendErrorMessage("Internal error with database - " + ex.getMessage());
                return null;
            }
        }

        /**
         * Inserts the users
         */
        try (PreparedStatement stmt = connection.prepareStatement(userQuery)) {

            for (UUID userId : factionDTO.getUsers()) {
                Player currentUser = Bukkit.getPlayer(userId);

                if (currentUser != null) {
                    if (factionId != 0) {
                        stmt.setString(1, userId.toString());
                        stmt.setString(2, currentUser.getName());
                        stmt.setInt(3, factionId);
                        stmt.setString(4, factionDTO.getDescription());
                        stmt.addBatch();
                    }
                }
            }

            stmt.executeBatch();
            connection.commit();
            connection.setAutoCommit(true);

            factionDTO.setId(factionId);

            return factionDTO;
        } catch (SQLException e) {
            try {
                connection.rollback();
                MessageHandler.sendErrorMessage(actualPlayer, "Some users could not be added to the faction");
                return null;
            } catch (SQLException ex) {
                MessageHandler.sendErrorMessage(actualPlayer, "Internal error with database");
                MessageHandler.sendErrorMessage("Internal error with database - " + ex.getMessage());
                return null;
            }
        }
    }

    /**
     * Deletes a specific faction
     *
     * @param actualPlayer The command executor
     * @param faction      The faction to remove
     * @return
     */
    public boolean deleteFaction(Player actualPlayer, FactionDTO faction) {
        String query = "DELETE FROM Factions WHERE id = ?";

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            MessageHandler.sendErrorMessage(actualPlayer, "Database error: Could not modify autocommit");
            MessageHandler.sendErrorMessage("Database error: Could not modify autocommit");
            return false;
        }

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, faction.getId());

            statement.execute();
            connection.commit();

            connection.setAutoCommit(true);

            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
                MessageHandler.sendErrorMessage(actualPlayer, "Unable to delete the faction");
                return false;
            } catch (SQLException ex) {
                MessageHandler.sendErrorMessage(actualPlayer, "Internal error with database");
                MessageHandler.sendErrorMessage("Internal error with database - " + ex.getMessage());
                return false;
            }
        }
    }

    /**
     * Queries the FactionDTO based on the leader UUID
     *
     * @param actualPlayer The command executor
     * @param leaderUUID   The leader UUID
     * @return
     */
    public FactionDTO getFactionByLeader(Player actualPlayer, UUID leaderUUID) {
        String query = "SELECT * FROM Factions WHERE leaderId = ?";
        FactionDTO factionDTO;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, leaderUUID.toString());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return getFactionDTO(resultSet);
                } else {
                    MessageHandler.sendErrorMessage(actualPlayer, "You are not the leader of a faction");
                    return null;
                }
            }
        } catch (SQLException e) {
            MessageHandler.sendErrorMessage(actualPlayer, "Unable to retrieve the faction");
            return null;
        }
    }

    public Set<UserDTO> getUsers(Player actualPlayer, FactionDTO faction) {
        String query = "SELECT * FROM Users WHERE factionId = ?";
        Set<UserDTO> users = new HashSet<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, faction.getId());

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    UserDTO user = new UserDTO();

                    user.setFaction(resultSet.getInt("factionId"));
                    user.setUserName(resultSet.getString("userName"));
                    user.setfactionRank(resultSet.getString("factionRank"));
                    user.setfactionXP(resultSet.getInt("factionXP"));
                    user.setUuid(UUID.fromString(resultSet.getString("id")));

                    users.add(user);
                }
            }

            if (users.isEmpty()) {
                MessageHandler.sendErrorMessage(actualPlayer, "No users found");
            }

            return users;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            MessageHandler.sendErrorMessage(actualPlayer, "Unable to retrieve the faction");
            return null;
        }
    }

    // Retrieves the fraction of a player
    public FactionDTO getFaction(Player actualPlayer) {
        String query = "SELECT Factions.id, Factions.name, Factions.leaderId, Factions.description FROM Factions INNER JOIN Users ON Factions.id = Users.factionId WHERE Users.id = ?";
        FactionDTO factionDTO;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, actualPlayer.getUniqueId().toString());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return getFactionDTO(resultSet);
                } else {
                    MessageHandler.sendErrorMessage(actualPlayer, "You are not in a faction");
                    return null;
                }
            }
        } catch (SQLException e) {
            MessageHandler.sendErrorMessage(actualPlayer, "Unable to retrieve the faction");
            return null;
        }
    }

    /* Transforms the data from result to a valid FactionDTO */
    private FactionDTO getFactionDTO(ResultSet resultSet) throws SQLException {
        FactionDTO factionDTO;
        factionDTO = new FactionDTO();
        factionDTO.setId(resultSet.getInt("id"));
        factionDTO.setName(resultSet.getString("name"));
        factionDTO.setDescription(resultSet.getString("description"));
        factionDTO.setLeader(UUID.fromString(resultSet.getString("leaderId")));

        return factionDTO;
    }
}
