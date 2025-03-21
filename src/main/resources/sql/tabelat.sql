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

CREATE TABLE Orari(
ID_Sesioni SERIAL PRIMARY KEY,
ID_Kandidat INT REFERENCES Kandidatet(ID_Kandidat) ON DELETE CASCADE ON UPDATE CASCADE,
ID_Staf INT REFERENCES Stafi(ID_Staf) ON DELETE CASCADE ON UPDATE CASCADE,
Data_e_Sesionit DATE,
Ora_e_Fillimit TIME,
Ora_e_Perfundimit TIME,
Lloji_i_Mesimit VARCHAR(50) CHECK (Lloji_i_Mesimit IN ('Teori', 'Praktikë')),
Statusi VARCHAR(50) CHECK (Statusi IN ('Planifikuar', 'Përfunduar', 'Anuluar'))
);
CREATE TABLE Testet(
ID_Test SERIAL PRIMARY KEY,
ID_Kandidat INT REFERENCES Kandidatet(ID_Kandidat) ON DELETE CASCADE ON UPDATE CASCADE,
ID_Staf INT REFERENCES Stafi(ID_Staf) ON DELETE CASCADE ON UPDATE CASCADE,
Lloji_i_Testit VARCHAR(50) CHECK (Lloji_i_Testit IN ('Teori', 'Praktikë')),
Data_e_testit DATE,
Rezultati VARCHAR(50) CHECK (Rezultati IN ('Kaluar', 'Dështuar')),
Piket INT
);

CREATE TABLE Pagesat(
ID_Pagesat SERIAL PRIMARY KEY,
ID_Kandidat INT REFERENCES Kandidatet(ID_Kandidat) ON DELETE CASCADE ON UPDATE CASCADE,
Shuma DECIMAL(10,2),
Data_e_Pageses DATE,
Metoda_e_Pageses VARCHAR(50) CHECK (Metoda_e_Pageses IN ('Cash', 'Kartë', 'Online')),
Statusi_i_Pageses VARCHAR(50) CHECK (Statusi_i_Pageses IN ('Paguar', 'Pjesërisht', 'Mbetur'))
);

CREATE TABLE Automjetet(
ID_Automjet SERIAL PRIMARY KEY,
Lloji_i_Automjetit VARCHAR(50) CHECK (Lloji_i_Automjetit IN ('Vetura', 'Motoçikletë', 'Kamion')),
Kategoria VARCHAR(10) CHECK (Kategoria IN ('A', 'B', 'C', 'D')),
Nenkategoria VARCHAR(10) CHECK (Nenkategoria IN ('A1', 'A2', 'B1', 'B2', 'C1', 'C2', 'D1', 'D2')),
Statusi VARCHAR(50) CHECK (Statusi IN ('Në përdorim', 'Mirëmbajtje', 'Jashtë shërbimit')),
 ID_Staf INT,
 CONSTRAINT fk_Stafi FOREIGN KEY (ID_Staf) REFERENCES Stafi(ID_Staf)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);