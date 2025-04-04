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

ALTER TABLE Kandidatet
ALTER COLUMN Emri
SET NOT NULL;

ALTER TABLE Kandidatet
ALTER COLUMN Mbiemri
SET NOT NULL;

ALTER TABLE Kandidatet
ALTER COLUMN Datelindja
SET NOT NULL;

ALTER TABLE Kandidatet
ADD CONSTRAINT gjinia_check CHECK (Gjinia IN ('Mashkull', 'Femer'));

ALTER TABLE Kandidatet
ADD CONSTRAINT unique_check UNIQUE (Email);

ALTER TABLE Kandidatet
ALTER COLUMN Data_e_Regjistrimit SET DEFAULT CURRENT_DATE;

ALTER TABLE Kandidatet
ADD CONSTRAINT statusi_i_procesit_check CHECK (Statusi_i_Procesit IN ('Në proces', 'Përfunduar'));

ALTER TABLE Testet
ALTER COLUMN Data_e_testit
SET NOT NULL;

ALTER TABLE Testet
ADD CONSTRAINT piket_check CHECK (Piket BETWEEN 0 AND 100);



ALTER TABLE Pagesat
ALTER COLUMN Shuma
SET NOT NULL;

ALTER TABLE Pagesat
ALTER COLUMN Data_e_Pageses SET DEFAULT CURRENT_DATE;



CREATE TABLE Kategorite_Patentes (
    ID_Kategori SERIAL PRIMARY KEY,
    Kategoria VARCHAR(10) UNIQUE NOT NULL,
    Pershkrimi TEXT
);


ALTER TABLE Automjetet
DROP COLUMN Kategoria;

ALTER TABLE Automjetet
DROP COLUMN Nenkategoria;

ALTER TABLE Stafi
ALTER COLUMN Emri
SET NOT NULL;

ALTER TABLE Stafi
ALTER COLUMN Mbiemri
SET NOT NULL;

ALTER TABLE Stafi
ADD CONSTRAINT unique_check2 UNIQUE (Email);


ALTER TABLE Orari
ADD COLUMN ID_Automjet INT,
ADD CONSTRAINT fk_id_automjet FOREIGN KEY (ID_Automjet) REFERENCES Automjetet(ID_Automjet)
ON DELETE SET NULL
ON UPDATE CASCADE;

ALTER TABLE Orari
ALTER COLUMN Data_E_Sesionit
SET NOT NULL;

ALTER TABLE Orari
ALTER COLUMN Ora_e_Fillimit
SET NOT NULL;

ALTER TABLE Orari
ALTER COLUMN Ora_e_Perfundimit
SET NOT NULL;


ALTER TABLE Automjetet
ADD COLUMN ID_Kategori INT,
ADD CONSTRAINT fk_id_kategori FOREIGN KEY (ID_Kategori) REFERENCES Kategorite_Patentes(ID_Kategori)
ON DELETE CASCADE
ON UPDATE CASCADE;



CREATE TABLE Regjistrimet (
    ID_Regjistrim SERIAL PRIMARY KEY,
    ID_Kandidat INT REFERENCES Kandidatet(ID_Kandidat) ON DELETE CASCADE ON UPDATE CASCADE,
    ID_Kategori INT REFERENCES Kategorite_Patentes(ID_Kategori) ON DELETE CASCADE ON UPDATE CASCADE,
    Statusi VARCHAR(50) CHECK (Statusi IN ('Në proces', 'Përfunduar'))
);

CREATE TABLE Patenta (
    ID_Patente SERIAL PRIMARY KEY,
    ID_Kandidat INT REFERENCES Kandidatet(ID_Kandidat) ON DELETE CASCADE ON UPDATE CASCADE,
    ID_Kategori INT REFERENCES Kategorite_Patentes(ID_Kategori) ON DELETE CASCADE ON UPDATE CASCADE,
    Data_Leshimit DATE DEFAULT CURRENT_DATE,
    Statusi VARCHAR(50) CHECK (Statusi IN ('E lëshuar', 'Në proces'))
);

CREATE TABLE Feedback (
    ID_Feedback SERIAL PRIMARY KEY,
    ID_Kandidat INT NOT NULL REFERENCES Kandidatet(ID_Kandidat) ON DELETE CASCADE,
    ID_Staf INT NOT NULL REFERENCES Stafi(ID_Staf) ON DELETE CASCADE,
    Data_Feedback DATE DEFAULT CURRENT_DATE,
    Vleresimi INT CHECK (Vleresimi BETWEEN 1 AND 5),
    Koment TEXT
);

CREATE TABLE Dokumentet (
    ID_Dokument SERIAL PRIMARY KEY,
    ID_Kandidat INT NOT NULL REFERENCES Kandidatet(ID_Kandidat) ON DELETE CASCADE,
    Lloji_Dokumentit VARCHAR(100) NOT NULL CHECK (Lloji_Dokumentit IN ('Leternjoftim', 'Certifikate Mjekësore', 'Aplikim', 'Foto')),
    Emri_Skedari VARCHAR(255),
    Data_Ngarkimit DATE DEFAULT CURRENT_DATE
);