# Apteka
Projekt zespołowy
1.7	Dokumentacja techniczna
1.7.1	Środowisko:
1.7.1.1	Git 2.36.1
1.7.1.2	Apache-maven-3.8.6
1.7.1.3	PostgreSQL
1.7.1.3.1	Bazy danych:
1.7.1.3.1.1	Apteka
1.7.1.3.1.1.1	Schemat: apteka
1.7.1.3.1.2	keycloak
1.7.1.4	Docker version 20.10.14
1.7.1.5	Intellij Version: 2022.1.2 (preferowane IDE)
1.7.1.6	Java version "1.8.0_321"
1.7.1.7	SDK corretto – 17.0.3
1.7.2	Aby uruchomić projekt należy sklonować repozytorium:
https://github.com/FromJuniorToJunior/Apteka
1.7.3	Następnie trzeba utworzyć obrazy aplikacji dla front-end i back-end:
1.7.3.1	Back-end – Znajdując się w folderze głównym projektu używamy komendy w terminalu: 
docker build -t apteka:1 .
1.7.3.2	Front-end - Znajdując się w folderze apteka_pg używamy komendy w terminalu: 
docker build -t front:1 .
1.7.4	Przechodzimy do pliku docker-compose i budujemy kontenery aplikacji w kolejności:
1.7.4.1	Postgres
1.7.4.2	Adminer
1.7.4.3	Keycloak
1.7.4.4	Apteka
1.7.4.5	Front

