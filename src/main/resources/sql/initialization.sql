PRAGMA foreign_keys = ON;

CREATE TABLE IF NOT EXISTS Factions
(
    id          INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 1000,
    name        VARCHAR(255) UNIQUE NOT NULL,
    description VARCHAR(255)        NOT NULL,
    leader      TEXT UNIQUE         NOT NULL,
    created_at  DATE                NOT NULL      DEFAULT (CURRENT_DATE),
    updated_at  DATE                              DEFAULT NULL,
    deleted_at  DATE                              DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS Faction_Ranks
(
    id         INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 1000,
    name       VARCHAR(255) UNIQUE NOT NULL,
    faction_id INTEGER             NOT NULL,
    xp_start   INTEGER             NOT NULL,
    xp_end     INTEGER             NOT NULL,
    created_at DATE                NOT NULL      DEFAULT (CURRENT_DATE),
    updated_at DATE                              DEFAULT NULL,

    FOREIGN KEY (faction_id) REFERENCES Factions (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Users
(
    id           TEXT PRIMARY KEY    NOT NULL,
    userName     VARCHAR(255) UNIQUE NOT NULL,
    faction_id   INTEGER                      DEFAULT NULL,
    faction_rank VARCHAR(255)                 DEFAULT NULL,
    faction_xp   INTEGER             NOT NULL DEFAULT 0,
    created_at   DATE                NOT NULL DEFAULT (CURRENT_DATE),
    updated_at   DATE                         DEFAULT NULL,
    deleted_at   DATE                         DEFAULT NULL,
    FOREIGN KEY (faction_id) REFERENCES Factions (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id) REFERENCES Factions (leader) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Items
(
    id          INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 1000,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    faction_id  INTEGER      NOT NULL,
    created_at  DATE         NOT NULL             DEFAULT (CURRENT_DATE),
    updated_at  DATE                              DEFAULT NULL,
    deleted_at  DATE                              DEFAULT NULL,

    FOREIGN KEY (faction_id) REFERENCES Factions (id) ON DELETE CASCADE ON UPDATE CASCADE
);