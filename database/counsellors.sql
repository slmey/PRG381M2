CREATE TABLE Counsellors (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(100),
    specialization VARCHAR(100),
    availability VARCHAR(50)
);