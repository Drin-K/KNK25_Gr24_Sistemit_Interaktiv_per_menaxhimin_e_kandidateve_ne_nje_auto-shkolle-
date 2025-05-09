CREATE TABLE "User"(
    id SERIAL PRIMARY KEY,
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
    id INT PRIMARY KEY REFERENCES "User"(id) ON DELETE CASCADE
);


CREATE TABLE Kandidatet (
    id INT PRIMARY KEY,
    dataRegjistrimi DATE DEFAULT CURRENT_DATE,
    statusiProcesit VARCHAR(50) CHECK (statusiProcesit IN ('Në proces', 'Përfunduar')),
    FOREIGN KEY (id) REFERENCES "User"(id) ON DELETE CASCADE
);

CREATE TABLE Admin (
    id INT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES "User"(id) ON DELETE CASCADE
);
CREATE TABLE Kategorite_Patentes (
    id SERIAL PRIMARY KEY,
    Kategoria VARCHAR(10) UNIQUE NOT NULL,
    Pershkrimi TEXT
);
CREATE TABLE Automjetet (
    id SERIAL PRIMARY KEY,
    Lloji_i_Automjetit VARCHAR(50) CHECK (Lloji_i_Automjetit IN ('Vetura', 'Motoçikletë', 'Kamion')),
    Statusi VARCHAR(50) CHECK (Statusi IN ('Në përdorim', 'Mirëmbajtje', 'Jashtë shërbimit')),
    ID_Staf INT,
    ID_Kategori INT,-- id e kategorise e cila mundet me qene A B C edhe pse tek kategoria nuk ka constraint, ne punojme me keto tri sepse nuk jemi t avancuar
    CONSTRAINT fk_Stafi FOREIGN KEY (ID_Staf) REFERENCES Stafi(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_id_kategori FOREIGN KEY (ID_Kategori) REFERENCES Kategorite_Patentes(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE Orari (
    id SERIAL PRIMARY KEY,
    ID_Kandidat INT REFERENCES Kandidatet(id) ON DELETE CASCADE ON UPDATE CASCADE,
    ID_Staf INT REFERENCES Stafi(id) ON DELETE CASCADE ON UPDATE CASCADE,
    Data_e_Sesionit DATE NOT NULL,
    Ora_e_Fillimit TIME NOT NULL,
    Ora_e_Perfundimit TIME NOT NULL,
    Lloji_i_Mesimit VARCHAR(50) CHECK (Lloji_i_Mesimit IN ('Teori', 'Praktikë')),
    Statusi VARCHAR(50) CHECK (Statusi IN ('Planifikuar', 'Përfunduar', 'Anuluar')),
    ID_Automjet INT,
    CONSTRAINT fk_id_automjet FOREIGN KEY (ID_Automjet) REFERENCES Automjetet(id)
    ON DELETE SET NULL
    ON UPDATE CASCADE
);


CREATE TABLE Testet (
    id SERIAL PRIMARY KEY,
    ID_Kandidat INT REFERENCES Kandidatet(id) ON DELETE CASCADE ON UPDATE CASCADE,
    ID_Staf INT REFERENCES Stafi(id) ON DELETE CASCADE ON UPDATE CASCADE,
    Lloji_i_Testit VARCHAR(50) CHECK (Lloji_i_Testit IN ('Teori', 'Praktikë')),
    Data_e_testit DATE NOT NULL,
    Rezultati VARCHAR(50) CHECK (Rezultati IN ('Kaluar', 'Dështuar')),
    Piket INT CHECK (Piket BETWEEN 0 AND 100) -- Këtu është constrainti për kolonën Piket
);


CREATE TABLE Pagesat(
id SERIAL PRIMARY KEY,
ID_Kandidat INT REFERENCES Kandidatet(id) ON DELETE CASCADE ON UPDATE CASCADE,
Shuma DECIMAL(10,2) NOT NULL,
Numri_Xhirollogarise INT,
Data_e_Pageses DATE DEFAULT CURRENT_DATE,
Metoda_e_Pageses VARCHAR(50) CHECK (Metoda_e_Pageses IN ('Cash','Online')),
Statusi_i_Pageses VARCHAR(50) CHECK (Statusi_i_Pageses IN ('Paguar', 'Pjesërisht', 'Mbetur'))
);

CREATE TABLE Regjistrimet (
    id SERIAL PRIMARY KEY,
    ID_Kandidat INT REFERENCES Kandidatet(id) ON DELETE CASCADE ON UPDATE CASCADE,
    ID_Kategori INT REFERENCES Kategorite_Patentes(id) ON DELETE CASCADE ON UPDATE CASCADE,
    Statusi VARCHAR(50) CHECK (Statusi IN ('Në proces', 'Përfunduar'))
);

CREATE TABLE Patenta (
    id SERIAL PRIMARY KEY,
    ID_Kandidat INT REFERENCES Kandidatet(id) ON DELETE CASCADE ON UPDATE CASCADE,
    ID_Kategori INT REFERENCES Kategorite_Patentes(id) ON DELETE CASCADE ON UPDATE CASCADE,
    Data_Leshimit DATE DEFAULT CURRENT_DATE,
    Statusi VARCHAR(50) CHECK (Statusi IN ('E lëshuar', 'Në proces'))
);

CREATE TABLE Feedback (
    id SERIAL PRIMARY KEY,
    ID_Kandidat INT NOT NULL REFERENCES Kandidatet(id) ON DELETE CASCADE,
    ID_Staf INT NOT NULL REFERENCES Stafi(id) ON DELETE CASCADE,
    Data_Feedback DATE DEFAULT CURRENT_DATE,
    Vleresimi INT CHECK (Vleresimi BETWEEN 1 AND 5),
    Koment TEXT);

CREATE TABLE Raporti_Progresit (
    id SERIAL PRIMARY KEY,
    ID_Kandidat INT REFERENCES Kandidatet(id) ON DELETE CASCADE ON UPDATE CASCADE,
    ID_Staf INT REFERENCES Stafi(id) ON DELETE CASCADE ON UPDATE CASCADE,
    Data_Raportit DATE DEFAULT CURRENT_DATE,
    Piket_Teorike INT CHECK (Piket_Teorike BETWEEN 0 AND 100),
    Piket_Praktike INT CHECK (Piket_Praktike BETWEEN 0 AND 100),
    Komentet TEXT,
    Performanca_Gjenerale VARCHAR(50) CHECK (Performanca_Gjenerale IN ('Shkelqyeshem', 'Mire', 'Mesatar', 'Dobet'))
);
CREATE TABLE Dokumentet (
    id SERIAL PRIMARY KEY,
    ID_Kandidat INT NOT NULL REFERENCES Kandidatet(id) ON DELETE CASCADE,
    Lloji_Dokumentit VARCHAR(100) NOT NULL CHECK (Lloji_Dokumentit IN ('Leternjoftim', 'Certifikate Mjekesore', 'Aplikim', 'Foto')),
    Emri_Skedari VARCHAR(255),
    Data_Ngarkimit DATE DEFAULT CURRENT_DATE
);