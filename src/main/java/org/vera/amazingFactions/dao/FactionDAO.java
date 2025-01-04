package org.vera.amazingFactions.dao;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.vera.amazingFactions.AmazingFactions;
import org.vera.amazingFactions.dto.FactionDTO;
import org.vera.amazingFactions.dto.UserDTO;
import org.vera.amazingFactions.handlers.MessageHandler;
import org.vera.amazingFactions.internal.DatabaseConnector;

import java.sql.*;
import java.util.*;

import static org.bukkit.Bukkit.getLogger;

public class FactionDAO {
    private Connection connection;

    public FactionDAO() {
        connection = AmazingFactions.database.getConnection();
    }

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

    public boolean deleteFaction(Player actualPlayer, int factionId) {
        String query = "DELETE FROM Factions WHERE id = ?";

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            MessageHandler.sendErrorMessage(actualPlayer, "Database error: Could not modify autocommit");
            MessageHandler.sendErrorMessage("Database error: Could not modify autocommit");
            return false;
        }

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, factionId);

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

    /*public FactionDTO getFactionByLeader(UUID leaderId) throws SQLException {

    }

    public FactionDTO getFactionById(int factionId) throws SQLException {

    }

    public FactionDTO getFactionByName(String factionName) throws SQLException {
    }

    public Set<FactionDTO> getFactions() throws SQLException {
    }

    public Set<UserDTO> getUsersFromFaction(int factionId) throws SQLException {
    }*/
}
