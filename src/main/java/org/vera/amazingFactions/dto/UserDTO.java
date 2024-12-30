package org.vera.amazingFactions.dto;

import java.util.UUID;

public class UserDTO {
    private UUID uuid;
    private String userName;
    private int faction;
    private String faction_rank;
    private int faction_xp;

    public UserDTO(UUID uuid, String userName, int faction, String faction_rank, int faction_xp) {
        this.uuid = uuid;
        this.userName = userName;
        this.faction = faction;
        this.faction_rank = faction_rank;
        this.faction_xp = faction_xp;
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

    public String getFaction_rank() {
        return faction_rank;
    }

    public void setFaction_rank(String faction_rank) {
        this.faction_rank = faction_rank;
    }

    public int getFaction_xp() {
        return faction_xp;
    }

    public void setFaction_xp(int faction_xp) {
        this.faction_xp = faction_xp;
    }
}
