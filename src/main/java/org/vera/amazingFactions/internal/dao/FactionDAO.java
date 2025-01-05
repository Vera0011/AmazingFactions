package org.vera.amazingFactions.internal.dao;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.vera.amazingFactions.AmazingFactions;
import org.vera.amazingFactions.internal.dto.FactionDTO;
import org.vera.amazingFactions.handlers.MessageHandler;

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
                    factionDTO = new FactionDTO();
                    factionDTO.setId(resultSet.getInt("id"));
                    factionDTO.setName(resultSet.getString("name"));
                    factionDTO.setLeader(UUID.fromString(resultSet.getString("leaderId")));

                    return factionDTO;
                } else {
                    MessageHandler.sendErrorMessage(actualPlayer, "You are not the leader of a faction");
                    return null;
                }
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
                MessageHandler.sendErrorMessage(actualPlayer, "Unable to retrieve the faction");
                return null;
            } catch (SQLException ex) {
                MessageHandler.sendErrorMessage(actualPlayer, "Internal error with database");
                MessageHandler.sendErrorMessage("Internal error with database - " + ex.getMessage());
                return null;
            }
        }
    }

    public Set<FactionDTO> getFactions() {
        return new HashSet<>();
    }
}
