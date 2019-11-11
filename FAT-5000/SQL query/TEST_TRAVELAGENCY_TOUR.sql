create table TOUR
(
    ID        INTEGER default (NEXT VALUE FOR TRAVELAGENCY.SYSTEM_SEQUENCE_EDC3EC63_961E_41F1_A672_2506AC48723F) auto_increment,
    NAMETOUR  VARCHAR(50) not null,
    IDCOUNTRY INTEGER     not null,
    IDHOTEL   INTEGER,
    constraint TOUR_PK
        primary key (ID)
);

INSERT INTO TRAVELAGENCY.TOUR (ID, NAMETOUR, IDCOUNTRY, IDHOTEL) VALUES (2, 'Beautiful forest travel', 1, 1);