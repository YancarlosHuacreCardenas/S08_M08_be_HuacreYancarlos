CREATE TABLE movies (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    title NVARCHAR(255) NOT NULL,
    description NVARCHAR(1000),
    director NVARCHAR(255),
    year INT,
    genre NVARCHAR(255),
    duration INT,
    image_url NVARCHAR(255),
    available BIT,
    release_date DATE
);

-- Datos de ejemplo
INSERT INTO movies (title, description, director, year, genre, duration, image_url, available, release_date)
VALUES 
('Avengers: Endgame', 'Los Vengadores se reúnen por última vez', 'Anthony Russo', 2019, 'Acción', 181, '', 1, '2019-04-26'),
('Inception', 'Un ladrón que roba secretos de los sueños', 'Christopher Nolan', 2010, 'Ciencia Ficción', 148, '', 1, '2010-07-16'),
('The Dark Knight', 'Batman enfrenta al Joker en Gotham City', 'Christopher Nolan', 2008, 'Acción', 152, '', 1, '2008-07-18');