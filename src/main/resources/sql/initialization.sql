PRAGMA foreign_keys = ON;

CREATE TABLE IF NOT EXISTS Factions
(
    id          INTEGER PRIMARY KEY AUTOINCREMENT,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    leader      TEXT         NOT NULL,
    created_at  DATE         NOT NULL,
    updated_at  DATE DEFAULT NULL,
    deleted_at  DATE DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS Users
(
    id         TEXT NOT NULL,
    faction    INTEGER DEFAULT NULL,
    created_at DATE NOT NULL,
    updated_at DATE    DEFAULT NULL,
    deleted_at DATE    DEFAULT NULL,
    FOREIGN KEY (faction) REFERENCES Factions (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id) REFERENCES Factions (leader) ON DELETE CASCADE ON UPDATE CASCADE
);
