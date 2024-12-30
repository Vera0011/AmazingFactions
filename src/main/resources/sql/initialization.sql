PRAGMA foreign_keys = ON;

CREATE TABLE IF NOT EXISTS Factions
(
    id          INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 1000,
    name        VARCHAR(255) UNIQUE NOT NULL,
    description VARCHAR(255)        NOT NULL,
    leader      TEXT UNIQUE         NOT NULL,
    createdAt   DATE                NOT NULL      DEFAULT (CURRENT_DATE),
    updatedAt   DATE                              DEFAULT NULL,
    deletedAt   DATE                              DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS FactionRanks
(
    id        INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 1000,
    name      VARCHAR(255) UNIQUE NOT NULL,
    factionId INTEGER             NOT NULL,
    xpStart   INTEGER             NOT NULL,
    xpEnd     INTEGER             NOT NULL,
    createdAt DATE                NOT NULL      DEFAULT (CURRENT_DATE),
    updatedAt DATE                              DEFAULT NULL,

    FOREIGN KEY (factionId) REFERENCES Factions (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Users
(
    id          TEXT PRIMARY KEY    NOT NULL,
    userName    VARCHAR(255) UNIQUE NOT NULL,
    factionId   INTEGER                      DEFAULT NULL,
    factionRank VARCHAR(255)                 DEFAULT NULL,
    factionXp   INTEGER             NOT NULL DEFAULT 0,
    createdAt   DATE                NOT NULL DEFAULT (CURRENT_DATE),
    updatedAt   DATE                         DEFAULT NULL,
    deletedAt   DATE                         DEFAULT NULL,

    FOREIGN KEY (factionId) REFERENCES Factions (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id) REFERENCES Factions (leader) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Items
(
    id          INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 1000,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    factionId   INTEGER      NOT NULL,
    createdAt   DATE         NOT NULL             DEFAULT (CURRENT_DATE),
    updatedAt   DATE                              DEFAULT NULL,
    deletedAt   DATE                              DEFAULT NULL,

    FOREIGN KEY (factionId) REFERENCES Factions (id) ON DELETE CASCADE ON UPDATE CASCADE
);