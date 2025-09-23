// ------------------------
// Crear Personas
// ------------------------
CREATE (p1:Person {id: 1, name: 'Alice', lastName: 'Smith', phone: '300111222'});
CREATE (p2:Person {id: 2, name: 'Bob', lastName: 'Johnson', phone: '300333444'});
CREATE (p3:Person {id: 3, name: 'Charlie', lastName: 'Brown', phone: '300555666'});

// ------------------------
// Crear Eventos
// ------------------------
CREATE (e1:Event {id: 1, name: 'Concierto de Rock'});
CREATE (e2:Event {id: 2, name: 'Maratón Solidaria'});
CREATE (e3:Event {id: 3, name: 'Feria Tecnológica'});

// ------------------------
// Relaciones Person ↔ Person (amigos)
// ------------------------
CREATE (p1)-[:FRIENDS_WITH]->(p2);
CREATE (p2)-[:FRIENDS_WITH]->(p3);
CREATE (p1)-[:FRIENDS_WITH]->(p3);

// ------------------------
// Relaciones Person ↔ Event (participación)
// ------------------------
CREATE (p1)-[:PARTICIPATES_IN]->(e1);
CREATE (p2)-[:PARTICIPATES_IN]->(e1);
CREATE (p2)-[:PARTICIPATES_IN]->(e2);
CREATE (p3)-[:PARTICIPATES_IN]->(e3);

// ------------------------
// Relaciones Event ↔ Event (asociados)
// ------------------------
CREATE (e1)-[:ASSOCIATED_WITH]->(e2);
CREATE (e2)-[:ASSOCIATED_WITH]->(e3);
