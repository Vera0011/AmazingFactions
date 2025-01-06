PRAGMA foreign_keys = ON;

CREATE TABLE IF NOT EXISTS Factions
(
    id          INTEGER PRIMARY KEY AUTOINCREMENT,
    name        VARCHAR(255) UNIQUE NOT NULL,
    description VARCHAR(255)        NOT NULL,
    leaderId    TEXT UNIQUE         NOT NULL,
    createdAt   DATE                NOT NULL DEFAULT (CURRENT_DATE),
    updatedAt   DATE                         DEFAULT NULL,
    deletedAt   DATE                         DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS Users
(
    id          TEXT PRIMARY KEY    NOT NULL,
    userName    VARCHAR(255) UNIQUE NOT NULL,
    factionId   INTEGER                      DEFAULT NULL,
    createdAt   DATE                NOT NULL DEFAULT (CURRENT_DATE),
    updatedAt   DATE                         DEFAULT NULL,
    deletedAt   DATE                         DEFAULT NULL,

    FOREIGN KEY (factionId) REFERENCES Factions (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id) REFERENCES Factions (leaderId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Items
(
    id          INTEGER PRIMARY KEY AUTOINCREMENT,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    factionId   INTEGER      NOT NULL,
    createdAt   DATE         NOT NULL DEFAULT (CURRENT_DATE),
    updatedAt   DATE                  DEFAULT NULL,
    deletedAt   DATE                  DEFAULT NULL,

    FOREIGN KEY (factionId) REFERENCES Factions (id) ON DELETE CASCADE ON UPDATE CASCADE
);