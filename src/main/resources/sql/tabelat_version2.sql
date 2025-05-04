CREATE TABLE "User"(
    idUser SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phoneNumber VARCHAR(15),
    dateOfBirth DATE,
    hashedPassword VARCHAR(255) NOT NULL,
    salt VARCHAR(255) NOT NULL,
    role VARCHAR(50) CHECK(role IN ('Kandidat', 'Staf', 'Admin')) NOT NULL,
    adresa TEXT,
    gjinia VARCHAR(10) CHECK(gjinia IN ('M', 'F'))
);

CREATE TABLE Stafi (
    idUser INT PRIMARY KEY,
    FOREIGN KEY (idUser) REFERENCES "User"(idUser) ON DELETE CASCADE);

CREATE TABLE Kandidatet (
    idUser INT PRIMARY KEY,
    dataRegjistrimi DATE DEFAULT CURRENT_DATE,
    statusiProcesit VARCHAR(50) CHECK (statusiProcesit IN ('Në proces', 'Përfunduar')),
    FOREIGN KEY (idUser) REFERENCES "User"(idUser) ON DELETE CASCADE
);

CREATE TABLE Admin (
    idUser INT PRIMARY KEY,
    FOREIGN KEY (idUser) REFERENCES "User"(idUser) ON DELETE CASCADE
);
CREATE TABLE Kategorite_Patentes (
    ID_Kategori SERIAL PRIMARY KEY,
    Kategoria VARCHAR(10) UNIQUE NOT NULL,
    Pershkrimi TEXT
);
CREATE TABLE Automjetet (
    ID_Automjet SERIAL PRIMARY KEY,
    Lloji_i_Automjetit VARCHAR(50) CHECK (Lloji_i_Automjetit IN ('Vetura', 'Motoçikletë', 'Kamion')),
    Statusi VARCHAR(50) CHECK (Statusi IN ('Në përdorim', 'Mirëmbajtje', 'Jashtë shërbimit')),
    ID_Staf INT,
    ID_Kategori INT,
    CONSTRAINT fk_Stafi FOREIGN KEY (ID_Staf) REFERENCES Stafi(idUser)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_id_kategori FOREIGN KEY (ID_Kategori) REFERENCES Kategorite_Patentes(ID_Kategori)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE Orari (
    ID_Sesioni SERIAL PRIMARY KEY,
    ID_Kandidat INT REFERENCES Kandidatet(idUser) ON DELETE CASCADE ON UPDATE CASCADE,
    ID_Staf INT REFERENCES Stafi(idUser) ON DELETE CASCADE ON UPDATE CASCADE,
    Data_e_Sesionit DATE NOT NULL,
    Ora_e_Fillimit TIME NOT NULL,
    Ora_e_Perfundimit TIME NOT NULL,
    Lloji_i_Mesimit VARCHAR(50) CHECK (Lloji_i_Mesimit IN ('Teori', 'Praktikë')),
    Statusi VARCHAR(50) CHECK (Statusi IN ('Planifikuar', 'Përfunduar', 'Anuluar')),
    ID_Automjet INT,
    CONSTRAINT fk_id_automjet FOREIGN KEY (ID_Automjet) REFERENCES Automjetet(ID_Automjet)
    ON DELETE SET NULL
    ON UPDATE CASCADE
);


CREATE TABLE Testet (
    ID_Test SERIAL PRIMARY KEY,
    ID_Kandidat INT REFERENCES Kandidatet(idUser) ON DELETE CASCADE ON UPDATE CASCADE,
    ID_Staf INT REFERENCES Stafi(idUser) ON DELETE CASCADE ON UPDATE CASCADE,
    Lloji_i_Testit VARCHAR(50) CHECK (Lloji_i_Testit IN ('Teori', 'Praktikë')),
    Data_e_testit DATE NOT NULL,
    Rezultati VARCHAR(50) CHECK (Rezultati IN ('Kaluar', 'Dështuar')),
    Piket INT CHECK (Piket BETWEEN 0 AND 100) -- Këtu është constrainti për kolonën Piket
);


CREATE TABLE Pagesat(
ID_Pagesat SERIAL PRIMARY KEY,
ID_Kandidat INT REFERENCES Kandidatet(idUser) ON DELETE CASCADE ON UPDATE CASCADE,
Shuma DECIMAL(10,2) NOT NULL,
Data_e_Pageses DATE DEFAULT CURRENT_DATE,
Metoda_e_Pageses VARCHAR(50) CHECK (Metoda_e_Pageses IN ('Cash', 'Kartë', 'Online')),
Statusi_i_Pageses VARCHAR(50) CHECK (Statusi_i_Pageses IN ('Paguar', 'Pjesërisht', 'Mbetur'))
);
CREATE TABLE Regjistrimet (
    ID_Regjistrim SERIAL PRIMARY KEY,
    ID_Kandidat INT REFERENCES Kandidatet(idUser) ON DELETE CASCADE ON UPDATE CASCADE,
    ID_Kategori INT REFERENCES Kategorite_Patentes(ID_Kategori) ON DELETE CASCADE ON UPDATE CASCADE,
    Statusi VARCHAR(50) CHECK (Statusi IN ('Në proces', 'Përfunduar'))
);

CREATE TABLE Patenta (
    ID_Patente SERIAL PRIMARY KEY,
    ID_Kandidat INT REFERENCES Kandidatet(idUser) ON DELETE CASCADE ON UPDATE CASCADE,
    ID_Kategori INT REFERENCES Kategorite_Patentes(ID_Kategori) ON DELETE CASCADE ON UPDATE CASCADE,
    Data_Leshimit DATE DEFAULT CURRENT_DATE,
    Statusi VARCHAR(50) CHECK (Statusi IN ('E lëshuar', 'Në proces'))
);

CREATE TABLE Feedback (
    ID_Feedback SERIAL PRIMARY KEY,
    ID_Kandidat INT NOT NULL REFERENCES Kandidatet(idUser) ON DELETE CASCADE,
    ID_Staf INT NOT NULL REFERENCES Stafi(idUser) ON DELETE CASCADE,
    Data_Feedback DATE DEFAULT CURRENT_DATE,
    Vleresimi INT CHECK (Vleresimi BETWEEN 1 AND 5),
    Koment TEXT);

CREATE TABLE Raporti_Progresit (
    ID_Raport SERIAL PRIMARY KEY,
    ID_Kandidat INT REFERENCES Kandidatet(idUser) ON DELETE CASCADE ON UPDATE CASCADE,
    ID_Staf INT REFERENCES Stafi(idUser) ON DELETE CASCADE ON UPDATE CASCADE,
    Data_Raportit DATE DEFAULT CURRENT_DATE,
    Piket_Teorike INT CHECK (Piket_Teorike BETWEEN 0 AND 100),
    Piket_Praktike INT CHECK (Piket_Praktike BETWEEN 0 AND 100),
    Komentet TEXT,
    Performanca_Gjenerale VARCHAR(50) CHECK (Performanca_Gjenerale IN ('Shkëlqyeshëm', 'Mirë', 'Mesatar', 'Dobët'))
);
CREATE TABLE Dokumentet (
    ID_Dokument SERIAL PRIMARY KEY,
    ID_Kandidat INT NOT NULL REFERENCES Kandidatet(idUser) ON DELETE CASCADE,
    Lloji_Dokumentit VARCHAR(100) NOT NULL CHECK (Lloji_Dokumentit IN ('Leternjoftim', 'Certifikate Mjekësore', 'Aplikim', 'Foto')),
    Emri_Skedari VARCHAR(255),
    Data_Ngarkimit DATE DEFAULT CURRENT_DATE
);