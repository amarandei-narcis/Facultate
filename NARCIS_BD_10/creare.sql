

CREATE TABLE angajati (
    id_angajat       CHAR(15 CHAR) NOT NULL,
    usernme_angajat  VARCHAR2(30) NOT NULL,
    nume_angajat     VARCHAR2(30) NOT NULL,
    prenume_angajat  VARCHAR2(30) NOT NULL,
    adresa_angajat   VARCHAR2(50) NOT NULL,
    email_angajat    VARCHAR2(30) NOT NULL,
    telefon_angajat  NVARCHAR2(20) NOT NULL,
    cnp_angajat      VARCHAR2(15) NOT NULL
);

ALTER TABLE angajati
    ADD CONSTRAINT angajati_nume_ck CHECK ( REGEXP_LIKE ( nume_angajat,
                                                          '[A-Za-z]+' ) );

ALTER TABLE angajati
    ADD CONSTRAINT angajati_prenume_ck CHECK ( REGEXP_LIKE ( prenume_angajat,
                                                             '[A-Za-z]+' ) );

ALTER TABLE angajati
    ADD CONSTRAINT angajati_email_ck CHECK ( REGEXP_LIKE ( email_angajat,
                                                           '[a-z0-9._%-]+@[a-z0-9._%-]+\.[a-z]{2,4}' ) );

ALTER TABLE angajati
    ADD CONSTRAINT angajati_telefon_ck CHECK ( REGEXP_LIKE ( telefon_angajat,
                                                             '07\d{8}' ) );

ALTER TABLE angajati
    ADD CONSTRAINT angajati_cnp_ck CHECK ( length(cnp_angajat) = 14 );

ALTER TABLE angajati ADD CONSTRAINT angajati_pk PRIMARY KEY ( id_angajat );

ALTER TABLE angajati ADD CONSTRAINT angajati_usernme_angajat_un UNIQUE ( usernme_angajat );

CREATE TABLE clienti (
    id_client  CHAR(15 CHAR) NOT NULL,
    username   VARCHAR2(30) NOT NULL,
    regiune    VARCHAR2(30) NOT NULL
);

ALTER TABLE clienti
    ADD CONSTRAINT clienti_regiune_ck CHECK ( regiune IN ( 'America de N', 'Europa de N ?i E', 'Europa de V', 'Korea', 'Rusia' ) );

ALTER TABLE clienti ADD CONSTRAINT clienti_pk PRIMARY KEY ( id_client );

ALTER TABLE clienti ADD CONSTRAINT clienti_username_un UNIQUE ( username );

CREATE TABLE detalii_clienti (
    cnp                VARCHAR2(20) NOT NULL,
    nume               VARCHAR2(30) NOT NULL,
    prenume            VARCHAR2(30) NOT NULL,
    email              VARCHAR2(30) NOT NULL,
    nr_telefon         NVARCHAR2(20) NOT NULL,
    adresa             VARCHAR2(100) NOT NULL,
    tara               VARCHAR2(30) NOT NULL,
    nr_card            NVARCHAR2(20) NOT NULL,
    clienti_id_client  CHAR(15 CHAR) NOT NULL
);

ALTER TABLE detalii_clienti
    ADD CONSTRAINT clienti_nume_ck CHECK ( REGEXP_LIKE ( nume,
                                                         '[A-Za-z]+' ) );

ALTER TABLE detalii_clienti
    ADD CONSTRAINT clienti_prenume_ck CHECK ( REGEXP_LIKE ( prenume,
                                                            '[A-Za-z]+' ) );

ALTER TABLE detalii_clienti
    ADD CONSTRAINT clienti_email_ck CHECK ( REGEXP_LIKE ( email,
                                                          '[a-z0-9._%-]+@[a-z0-9._%-]+\.[a-z]{2,4}' ) );

CREATE UNIQUE INDEX detalii_clienti__idx ON
    detalii_clienti (
        clienti_id_client
    ASC );

ALTER TABLE detalii_clienti ADD CONSTRAINT detalii_clienti_pk PRIMARY KEY ( cnp );

CREATE TABLE jocuri (
    id_joc               CHAR(15 CHAR) NOT NULL,
    nume                 VARCHAR2(30) NOT NULL,
    pret                 NUMBER(10, 2),
    data_aparitiei       DATE NOT NULL,
    gen                  VARCHAR2(30) NOT NULL,
    tip_joc              VARCHAR2(30) NOT NULL,
    angajati_id_angajat  CHAR(15 CHAR) NOT NULL
);

ALTER TABLE jocuri
    ADD CONSTRAINT jocuri_tip_ck CHECK ( tip_joc IN ( 'multiplayer', 'single player' ) );

ALTER TABLE jocuri ADD CONSTRAINT jocuri_pk PRIMARY KEY ( id_joc,
                                                          angajati_id_angajat );

CREATE TABLE relation_1 (
    clienti_id_client           CHAR(15 CHAR) NOT NULL,
    jocuri_id_joc               CHAR(15 CHAR) NOT NULL,
    jocuri_angajati_id_angajat  CHAR(15 CHAR) NOT NULL
);

ALTER TABLE relation_1
    ADD CONSTRAINT relation_1_pk PRIMARY KEY ( clienti_id_client,
                                               jocuri_id_joc,
                                               jocuri_angajati_id_angajat );

CREATE TABLE specificatii (
    nume_joc                    VARCHAR2(30) NOT NULL,
    stele                       NUMBER(1),
    comentarii                  VARCHAR2(1500),
    nr_descarcari               VARCHAR2(100) NOT NULL,
    jocuri_id_joc               CHAR(15 CHAR) NOT NULL,
    jocuri_angajati_id_angajat  CHAR(15 CHAR) NOT NULL
);

ALTER TABLE specificatii
    ADD CONSTRAINT specifictii_stele_ck CHECK ( stele BETWEEN 1 AND 5 );

CREATE UNIQUE INDEX specificatii__idx ON
    specificatii (
        jocuri_id_joc
    ASC,
        jocuri_angajati_id_angajat
    ASC );

ALTER TABLE detalii_clienti
    ADD CONSTRAINT detalii_clienti_clienti_fk FOREIGN KEY ( clienti_id_client )
        REFERENCES clienti ( id_client );

ALTER TABLE jocuri
    ADD CONSTRAINT jocuri_angajati_fk FOREIGN KEY ( angajati_id_angajat )
        REFERENCES angajati ( id_angajat );

ALTER TABLE relation_1
    ADD CONSTRAINT relation_1_clienti_fk FOREIGN KEY ( clienti_id_client )
        REFERENCES clienti ( id_client );

ALTER TABLE relation_1
    ADD CONSTRAINT relation_1_jocuri_fk FOREIGN KEY ( jocuri_id_joc,
                                                      jocuri_angajati_id_angajat )
        REFERENCES jocuri ( id_joc,
                            angajati_id_angajat );

ALTER TABLE specificatii
    ADD CONSTRAINT specificatii_jocuri_fk FOREIGN KEY ( jocuri_id_joc,
                                                        jocuri_angajati_id_angajat )
        REFERENCES jocuri ( id_joc,
                            angajati_id_angajat );

CREATE SEQUENCE angajati_id_angajat_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER angajati_id_angajat_trg BEFORE
    INSERT ON angajati
    FOR EACH ROW
    WHEN ( new.id_angajat IS NULL )
BEGIN
    :new.id_angajat := angajati_id_angajat_seq.nextval;
END;
/

CREATE SEQUENCE clienti_id_client_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER clienti_id_client_trg BEFORE
    INSERT ON clienti
    FOR EACH ROW
    WHEN ( new.id_client IS NULL )
BEGIN
    :new.id_client := clienti_id_client_seq.nextval;
END;
/

CREATE SEQUENCE jocuri_id_joc_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER jocuri_id_joc_trg BEFORE
    INSERT ON jocuri
    FOR EACH ROW
    WHEN ( new.id_joc IS NULL )
BEGIN
    :new.id_joc := jocuri_id_joc_seq.nextval;
END;
/



