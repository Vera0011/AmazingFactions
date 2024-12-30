package org.vera.amazingFactions.dto;

import java.util.Set;
import java.util.UUID;

public class FactionDTO {
    private String name;
    private String description;
    private Set<UUID> users;
    private UUID leader;
    private int id;

    public FactionDTO(int id, String name, String description, Set<UUID> users, UUID leader) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.users = users;
        this.leader = leader;
    }

    public void setLeader(UUID leader) {
        this.leader = leader;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void modifyLeader(UUID newLeader) {
        this.leader = newLeader;
    }

    public UUID getLeader() {
        return this.leader;
    }

    public void removeUser(UUID user) {
        this.users.remove(user);
    }

    public void addUser(UUID user) {
        this.users.add(user);
    }

    public String getDescription() {
        return description;
    }

    public Set<UUID> getUsers() {
        return users;
    }

    public void setUsers(Set<UUID> users) {
        this.users = users;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
