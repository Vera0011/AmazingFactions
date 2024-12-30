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

CREATE TABLE IF NOT EXISTS Users
(
    id           TEXT PRIMARY KEY NOT NULL,
    faction      INTEGER                   DEFAULT NULL,
    faction_rank INTEGER                   DEFAULT NULL,
    faction_xp   INTEGER                   DEFAULT NULL,
    created_at   DATE             NOT NULL DEFAULT (CURRENT_DATE),
    updated_at   DATE                      DEFAULT NULL,
    deleted_at   DATE                      DEFAULT NULL,
    FOREIGN KEY (faction) REFERENCES Factions (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id) REFERENCES Factions (leader) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Items
(
    id           INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 1000,
    name         VARCHAR(255) NOT NULL,
    description  VARCHAR(255) NOT NULL,
    from_faction VARCHAR(255) NOT NULL,
    created_at   DATE         NOT NULL             DEFAULT (CURRENT_DATE),
    updated_at   DATE                              DEFAULT NULL,
    deleted_at   DATE                              DEFAULT NULL,

    FOREIGN KEY (from_faction) REFERENCES Factions (id) ON DELETE CASCADE ON UPDATE CASCADE
);