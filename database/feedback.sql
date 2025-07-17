CREATE TABLE Feedback (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    studentName VARCHAR(100),
    rating INT CHECK (rating BETWEEN 1 AND 5),
    comments VARCHAR(500)
);