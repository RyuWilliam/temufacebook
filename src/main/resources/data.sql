-- Insertar Lugares
INSERT INTO place_entity (place_id, name, address) VALUES
(1, 'Auditorio Principal', 'Calle 123 #45-67'),
(2, 'Parque Central', 'Carrera 7 #56-89'),
(3, 'Sala de Conferencias', 'Av. Siempre Viva 742');

-- Insertar Eventos
INSERT INTO event_entity (event_id, name, date, place_id, status) VALUES
(1, 'Concierto Rock', '2025-10-01 19:00:00', 1, 'CONFIRMED'),
(2, 'Feria de Hobbies', '2025-10-15 10:00:00', 2, 'PENDING'),
(3, 'Congreso de Tecnología', '2025-11-05 08:30:00', 3, 'ONCOURSE');
