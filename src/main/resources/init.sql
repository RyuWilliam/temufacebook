CREATE TABLE Person(
    personId SERIAL primary key,
    name VARCHAR(50) not null,
    lastName VARCHAR(50) not null,
    phone VARCHAR(15)
);

CREATE TABLE Hobie(
    hobieId SERIAL primary key,
    name VARCHAR(50) not null,
    description VARCHAR(250)
);

CREATE TABLE Place(
    placeId SERIAL primary key,
    name VARCHAR(50) not null,
    address VARCHAR(100)
);

CREATE TYPE enum_status AS ENUM ('CANCELLED', 'ONCOURSE', 'CONFIRMED','PENDIENT','DONE');

CREATE TABLE Event(
    eventId SERIAL primary key,
    placeId INT references Place(placeId),
    name VARCHAR(50) not null,
    dateEvent TIMESTAMP,
    status enum_status not null default 'PENDIENT'
);

CREATE TABLE Person_event(
    personId INT references Person(personId),
    eventId INT references Event(eventId)
);

CREATE TABLE Event_hobie(
    eventId INT references Event(eventId),
    hobieId INT references Hobie(hobieId)
);

CREATE TABLE Person_hobie(
    personId INT references Person(personId),
    hobieId INT references Hobie(hobieId)
);



