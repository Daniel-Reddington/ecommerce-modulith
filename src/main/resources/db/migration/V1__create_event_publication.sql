CREATE TABLE event_publication (
    id BINARY(16) NOT NULL PRIMARY KEY,
    publication_date TIMESTAMP NOT NULL,
    event_type VARCHAR(255) NOT NULL,
    serialized_event CLOB NOT NULL,
    completed BOOLEAN NOT NULL
);