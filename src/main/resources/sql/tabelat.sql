CREATE TABLE Kandidatet(
ID_Kandidat SERIAL PRIMARY KEY,
Emri VARCHAR(100),
Mbiemri VARCHAR(100),
Datelindja DATE,
Gjinia VARCHAR(10),
Numri_Telefonit VARCHAR(20),
Email VARCHAR(100),
Adresa TEXT,
Data_e_Regjistrimit DATE,
Statusi_i_Procesit VARCHAR(100)
);

CREATE TABLE Stafi(
ID_Staf SERIAL PRIMARY KEY,
Emri VARCHAR(100),
Mbiemri VARCHAR(100),
Numri_Telefonit VARCHAR(20),
Email VARCHAR(100),
Adresa TEXT,
Roli VARCHAR(50) CHECK (Roli IN('Instruktor', 'Ekzaminues'))
);