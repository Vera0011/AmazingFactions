package org.vera.amazingFactions.dto;

import java.util.ArrayList;
import java.util.Set;

public class FactionDTO {
    private String name;
    private String description;
    private Set<String> users;
    private String leader;

    public FactionDTO(String name, String description, Set<String> users, String leader) {
        this.name = name;
        this.description = description;
        this.users = users;
        this.leader = leader;
    }

    public void modifyLeader(String newLeader) {
        this.leader = newLeader;
    }

    public String getLeader() {
        return this.leader;
    }

    public void removeUser(String user) {
        this.users.remove(user);
    }

    public void addUser(String user) {
        this.users.add(user);
    }

    public String getDescription() {
        return description;
    }

    public Set<String> getUsers() {
        return users;
    }

    public void setUsers(Set<String> users) {
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
