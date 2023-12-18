/*example of flight data */
INSERT INTO flights (id, number, origin, destination, departure_date, departure_time, arrival_date, arrival_time, planeId)
VALUES (NEXTVAL('flights_sequence_in_database'), 'AF102', 'New York', 'Paris', '2023-12-01', '12:00', '2023-12-01', '15:00', 1);

INSERT INTO flights (id, number, origin, destination, departure_date, departure_time, arrival_date, arrival_time, planeId)
VALUES (NEXTVAL('flights_sequence_in_database'), 'BA203', 'Tokyo', 'London', '2023-12-02', '12:00', '2023-12-03', '12:00', 2);