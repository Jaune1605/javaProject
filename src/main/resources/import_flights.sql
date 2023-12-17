INSERT INTO flight (id,number, origin, destination, departure_date, departure_time, arrival_date, arrival_time, plane_id)
VALUES (NEXTVAL('flight_sequence'),'AF123', 'Paris', 'New York', '2023-12-31', '12:00:00', '2024-01-01', '15:00:00', 1);

INSERT INTO flight (id,number, origin, destination, departure_date, departure_time, arrival_date, arrival_time, plane_id)
VALUES (NEXTVAL('flight_sequence'),'AF124', 'Paris', 'Singapour', '2023-12-31', '12:00:00', '2024-01-01', '15:00:00', 1);