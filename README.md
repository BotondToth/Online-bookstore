<h1>Online könyvesbolt</h1>

Ez az alkalmazás a <b>Rendszerfejlesztés I.</b> kurzushoz készült.
Készítők:
<ul>
    <li>Dodony Róbert</li>
    <li>Lőrik Viktor</li>
    <li>Péter Roland</li>
    <li>Sors Ádám</li>
    <li>Tóth Botond</li>
</ul>


Az alkalmazás futtatásához szükséges:
<ul>
    <li>Java 8+</li>
    <li>Angular 5</li>
    <li>PostgreSQL</li>
</ul>
    
Az adatbázis az alapértelmezett porton (5432), az alapértelmezett url-en keresztül érhető el.

<b>FONTOS</b>: a superuser jelszavát át kell írni a megfelelőre a properties fájlban!

Inicializálásához a script mappában található init.sql használható

A frontend modul a frontend/bookstoreFrontend mappában az npm install, majd ng serve paranccsal indítható. Ezután a 4200 porton keresztül lehet elérni az alkalmazást.

A backend modul az mvn clean install, majd mvn spring-boot:run parancsok kiadása után indul el a 8080-as porton.
