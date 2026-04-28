use rosatel;

CREATE TABLE Producto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    Nombre CHAR(100) NOT NULL,
    SKU CHAR(20) NOT NULL UNIQUE,
    Stock INT NOT NULL DEFAULT 0,
    Precio DECIMAL(12, 2) NOT NULL,
    Descripcion TEXT,
    idCategoria INT NOT NULL,
    esFavorito BOOLEAN NOT NULL DEFAULT FALSE,
    
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
    
    FOREIGN KEY (idCategoria) REFERENCES Categoria(id)
    ON DELETE CASCADE
);

CREATE TABLE Categoria(
	id INT AUTO_INCREMENT PRIMARY KEY,
    Nombre CHAR(20) NOT NULL,
    idCategoria INT NULL,
    
    FOREIGN KEY (idCategoria) REFERENCES Categoria(id)
    ON DELETE SET NULL
);

CREATE TABLE Resena (
    id INT AUTO_INCREMENT PRIMARY KEY,
    calificacion TINYINT NOT NULL CHECK (calificacion BETWEEN 1 AND 5),
    comentario TEXT,
    idProducto INT NOT NULL,
    
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (idProducto) REFERENCES Producto(id) ON DELETE CASCADE
);

INSERT Categoria(nombre) VALUES ("Arreglos"),
("Peluches"),
("Chocolates"),
("Vinos y Licores"),
("Juguetes");

INSERT INTO Producto 
(Nombre, SKU, Stock, Precio, Descripcion, idCategoria, esFavorito) 
VALUES
("Ramo de Rosas Clásico", "ARR-001", 15, 79.90, "Ramo de rosas rojas frescas", 1, true),
("Arreglo Floral Premium", "ARR-002", 10, 129.90, "Flores variadas de alta calidad", 1, false),
("Caja Sorpresa Floral", "ARR-003", 8, 99.90, "Incluye flores y chocolates", 1, true),
("Peluche Oso Grande", "PEL-001", 20, 59.90, "Oso de peluche suave 60cm", 2, false),
("Peluche Corazón", "PEL-002", 25, 39.90, "Peluche con forma de corazón", 2, true),
("Peluche Unicornio", "PEL-003", 12, 49.90, "Unicornio de colores pastel", 2, false),
("Caja de Chocolates Gourmet", "CHO-001", 30, 45.50, "Selección de chocolates finos", 3, true),
("Chocolate Artesanal 70%", "CHO-002", 40, 25.00, "Chocolate oscuro premium", 3, false),
("Pack Chocolates Variados", "CHO-003", 18, 55.00, "Mix de sabores", 3, true),
("Vino Tinto Reserva", "VIN-001", 10, 89.90, "Vino tinto añejado", 4, false),
("Champagne Brut", "VIN-002", 6, 149.90, "Espumante elegante", 4, true),
("Whisky Premium", "VIN-003", 5, 199.90, "Whisky importado", 4, false),
("Carro de Juguete", "JUG-001", 35, 29.90, "Auto a escala", 5, false),
("Muñeca Clásica", "JUG-002", 22, 49.90, "Muñeca articulada", 5, true),
("Set de Bloques", "JUG-003", 18, 69.90, "Bloques educativos", 5, false),
("Ramo de Tulipanes", "ARR-004", 14, 84.90, "Tulipanes frescos de colores", 1, false),
("Peluche Panda", "PEL-004", 16, 44.90, "Panda suave y tierno", 2, true),
("Chocolate con Almendras", "CHO-004", 28, 27.50, "Chocolate con trozos de almendra", 3, false),
("Licor de Café", "VIN-004", 9, 74.90, "Licor dulce de café", 4, true),
("Rompecabezas Infantil", "JUG-004", 20, 34.90, "Puzzle educativo", 5, false);


