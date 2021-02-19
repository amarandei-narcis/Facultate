
select* from Clienti;
select* from Detalii_clienti;
select* from Jocuri;
select* from Angajati;
select* from Relation_1;
select* from Specificatii;

insert into Clienti values(null,'Sabuc', 'Korea');
insert into Clienti values(null,'DarkLorde', 'America de N');
insert into Clienti values(null,'KingOfGames', 'Europa de N ?i E');
insert into Clienti values(null,'Faker', 'Korea');
insert into Clienti values(null,'Kalashnikov', 'Rusia');
insert into Clienti values(null,'Hitler', 'Europa de V');
insert into Clienti values(null,'DonDinero', 'Rusia');
insert into Clienti values(null,'Costel96', 'Europa de N ?i E');


insert into detalii_clienti VALUES(2314762491267,'My?ng', 'Duck-Young','poligma@yahoo.com','08236451892','Puraesan-rodongjagu','Korea','1284291',
    (SELECT id_client from Clienti WHERE (id_client=1)));
    
insert into detalii_clienti VALUES(12649326701253,'Blue', 'Amanda','cinicica61@yahoo.com','0036754321','Bentley Ave','New-york','245575321', 
    (SELECT id_client from Clienti WHERE (id_client=2)));
    
insert into detalii_clienti VALUES(23627369414379,'Kangj?n', 'Hyo','mario13@yahoo.com','08248576362','Hamgyong','Korea de Nord','65392732', 
    (SELECT id_client from Clienti WHERE (id_client=4)));
    
insert into detalii_clienti VALUES(98263478567439,'Afanasy', 'Votka','polosnica@yahoo.com','4323459836','Vkusvill','Rusia','834727321', 
    (SELECT id_client from Clienti WHERE (id_client=5)));
    
insert into detalii_clienti VALUES(92465283653765,'Lavrenti', 'Igor','Kuzma80@yahoo.com','4376984592','Moscova','Rusia','247354374', 
    (SELECT id_client from Clienti WHERE (id_client=7)));
    
insert into detalii_clienti VALUES(23442424327637,'Marcelino', 'Francesco','romeodelmorto@yahoo.com','00323432454534','Via degli Olmi, 9','Italia','534256352', 
    (SELECT id_client from Clienti WHERE (id_client=3)));
    
insert into detalii_clienti VALUES(71209573947984,'Iacob', 'Vasile','parleanumarcel@yahoo.com','0753896446','Str. Plt. Ion Nedelcu','Romania','656347632', 
    (SELECT id_client from Clienti WHERE (id_client=8)));
    
insert into detalii_clienti VALUES(80675037431437,'Miho', 'Bengiamin','moniho@yahoo.com','03234532162','Bishop s Gate St','Irlanda','2455632561', 
    (SELECT id_client from Clienti WHERE (id_client=6)));
    

    



insert into Angajati values(null,'Dumitru_Alexandru','Dumitru','Alexandru','Iasi, std. Olari, nr. 5','alex98@gmail.com','0745673645',12342193267547);
insert into Angajati values(null,'Popovici_Andrei','Popovici','Andrei','Iasi, Aleea Sucidava, nr. 7','andreutz@gmail.com','0755767364',56293456392514);
insert into Angajati values(null,'Cola_Adriana','Cola','Adriana','Iasi, std. Roman Voda, nr. 4','cocacola34@yahoo.com','0746948556',45362356739456);
insert into Angajati values(null,'Paduraru_Marian','Paduraru','Marian','Iasi, std. Stejar, nr. 8','paduremare@gmail.com','0747649812',53426273645362);
insert into Angajati values(null,'Pipirau_Monica','Pipirau','Monica','Iasi, std. Libertatii, nr. 5','mumibumi@yahoo.com','0745673645',12342193267547);
   
insert into Jocuri values(null,'Cyberpunk 2077',199.99,TO_DATE('13-07-2012','DD-MM-YYYY'),'Action','single player',
    (SELECT id_angajat from Angajati WHERE (id_angajat=3)));
insert into Jocuri values(null,'League of Legends',0,TO_DATE('05-07-1998','DD-MM-YYYY'),'battle arena','multiplayer',
    (SELECT id_angajat from Angajati WHERE (id_angajat=5)));  
insert into Jocuri values(null,'God of War',450,TO_DATE('12-03-2018','DD-MM-YYYY'),'Action and Adventure','single player',
    (SELECT id_angajat from Angajati WHERE (id_angajat=1)));
insert into Jocuri values(null,'Rocket League',100,TO_DATE('23-12-2015','DD-MM-YYYY'),'Battle Arena','multiplayer',
    (SELECT id_angajat from Angajati WHERE (id_angajat=4)));
insert into Jocuri values(null,'Fortnite',379.99,TO_DATE('05-10-2012','DD-MM-YYYY'),'Battle Royale','multiplayer',
    (SELECT id_angajat from Angajati WHERE (id_angajat=2)));
    


insert into Specificatii values('Cyberpunk 2077',4,null,30870,
    (SELECT id_joc from Jocuri WHERE (id_joc=1)),
    (SELECT Angajati_id_angajat from Jocuri WHERE (Angajati_id_angajat=3)));   
insert into Specificatii values('League of Legends',3,null,17254989,
    (SELECT id_joc from Jocuri WHERE (id_joc=2)),
    (SELECT Angajati_id_angajat from Jocuri WHERE (Angajati_id_angajat IN 5))); 
insert into Specificatii values('God of War',5,null,564897,
    (SELECT id_joc from Jocuri WHERE (id_joc=3)),
    (SELECT Angajati_id_angajat from Jocuri WHERE (Angajati_id_angajat=1)));   
insert into Specificatii values('Rocket League',3,'Un joc distractiv',6589,
    (SELECT id_joc from Jocuri WHERE (id_joc=4)),
    (SELECT Angajati_id_angajat from Jocuri WHERE (Angajati_id_angajat=4)));    
insert into Specificatii values('Fortnite',2,null,6546876,
    (SELECT id_joc from Jocuri WHERE (id_joc=5)),
    (SELECT Angajati_id_angajat from Jocuri WHERE (Angajati_id_angajat=2)));
    
insert into relation_1 values((select id_client from Clienti where (id_client=1)),
    (select id_joc from Jocuri where (id_joc=1)),
    (select id_angajat from Angajati where (id_angajat=3)));
insert into relation_1 values((select id_client from Clienti where (id_client=1)),
    (select id_joc from Jocuri where (id_joc=2)),
    (select id_angajat from Angajati where (id_angajat=5)));    
insert into relation_1 values((select id_client from Clienti where (id_client=2)),
    (select id_joc from Jocuri where (id_joc=1)),
    (select id_angajat from Angajati where (id_angajat=3)));
    
insert into relation_1 values((select id_client from Clienti where (id_client=3)),
    (select id_joc from Jocuri where (id_joc=2)),
    (select id_angajat from Angajati where (id_angajat=5)));
insert into relation_1 values((select id_client from Clienti where (id_client=4)),
    (select id_joc from Jocuri where (id_joc=3)),
    (select id_angajat from Angajati where (id_angajat=1)));
insert into relation_1 values((select id_client from Clienti where (id_client=6)),
    (select id_joc from Jocuri where (id_joc=5)),
    (select id_angajat from Angajati where (id_angajat=2)));
insert into relation_1 values((select id_client from Clienti where (id_client=7)),
    (select id_joc from Jocuri where (id_joc=4)),
    (select id_angajat from Angajati where (id_angajat=4)));
    
    