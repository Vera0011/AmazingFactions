package org.vera.amazingFactions.internal.dao;

import org.bukkit.entity.Player;
import org.vera.amazingFactions.AmazingFactions;
import org.vera.amazingFactions.handlers.MessageHandler;
import org.vera.amazingFactions.internal.dto.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserDAO {
    private Connection connection;

    public UserDAO() {
        connection = AmazingFactions.database.getConnection();
    }

    public UserDTO getUser(Player actualPlayer, UUID id) {
        String query = "SELECT * FROM Users WHERE id = ?";
        UserDTO user;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id.toString());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new UserDTO();
                    user.setUuid(id);
                    user.setUserName(resultSet.getString("userName"));
                    user.setfactionXP(resultSet.getInt("factionXp"));
                    user.setFaction(resultSet.getInt("factionId"));
                    user.setfactionRank(resultSet.getString("factionRank"));

                    return user;
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
}
