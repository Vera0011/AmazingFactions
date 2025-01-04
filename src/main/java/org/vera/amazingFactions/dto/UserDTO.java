package org.vera.amazingFactions.dto;

import java.util.UUID;

public class UserDTO {
    private UUID uuid;
    private String userName;
    private int faction;
    private String factionRank;
    private int factionXP;

    public UserDTO(UUID uuid, String userName, int faction, String factionRank, int factionXP) {
        this.uuid = uuid;
        this.userName = userName;
        this.faction = faction;
        this.factionRank = factionRank;
        this.factionXP = factionXP;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getFaction() {
        return faction;
    }

    public void setFaction(int faction) {
        this.faction = faction;
    }

    public String getfactionRank() {
        return factionRank;
    }

    public void setfactionRank(String factionRank) {
        this.factionRank = factionRank;
    }

    public int getfactionXP() {
        return factionXP;
    }

    public void setfactionXP(int factionXP) {
        this.factionXP = factionXP;
    }
}
