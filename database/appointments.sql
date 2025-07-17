CREATE TABLE Appointments (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    studentName VARCHAR(100),
    counselorId INT,
    date DATE,
    time TIME,
    status VARCHAR(20)
);