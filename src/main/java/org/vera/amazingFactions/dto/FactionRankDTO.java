package org.vera.amazingFactions.dto;

public class FactionRankDTO {
    private int id;
    private String name;
    private int factionId;
    private int xpStart;
    private int xpEnd;

    public FactionRankDTO(int id, String name, int factionId, int xpStart, int xpEnd) {
        this.id = id;
        this.name = name;
        this.factionId = factionId;
        this.xpStart = xpStart;
        this.xpEnd = xpEnd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFactionId() {
        return factionId;
    }

    public void setFactionId(int factionId) {
        this.factionId = factionId;
    }

    public int getXpStart() {
        return xpStart;
    }

    public void setXpStart(int xpStart) {
        this.xpStart = xpStart;
    }

    public int getXpEnd() {
        return xpEnd;
    }

    public void setXpEnd(int xpEnd) {
        this.xpEnd = xpEnd;
    }
}
