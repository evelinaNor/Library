# Library

Library application with React and Spring Boot.

Projekta sudaro: CRUD operations, Spring Boot Security, MySQL, Spring Boot internationalization, Git commits.

Library puslapis turi du prisijungimus:
 - User
 - Admin
 
 Noredami prisijungti prie Admin roles, suveskite
 Username: admin
 Password: pass

 Noredami prisijungti prie User roles, suveskite
 Username: user
 Password: pass
 
User roles pagrindinis puslapis turi: 
 - Pagrindiniame puslapyje matyti visas knygu sarasas.
 - Search bar. Sioje sekcijoje galima issifiltruoti bet kokia knyga is saraso pagal knygos pavadinima
 - mygtukas Search - suras knyga pagal pavadinima
 - mygtukas Clear - istrins paieska ir nunaviguos atgal i bendra knygu sarasa
 - mygtukas Review - parodo detalesne informacija apie knyga
 - mygtukas LogOut - leidzia atsijungti nuo User roles
 - Language change drop down list - leidzia pasirinkti puslapio vertima i Anglu arba Lietuviu kalba.

Admin roles pagrindinis puslapis turi:
 - Pilnai veikiancias CRUD operacijas (ADD, UPDATE, DELETE, READ)
 - Pagrindis puslapis rodo visa pridetu knygu sarasa
 - mygtukas Add new Book - leidzia prideti nauja knyga (pavadinimas, autorius, aprasymas, kategorija) kuri taip pat prisideda i MySql database
 - mygtukas EDIT - leidzia koreguoti ivestos knygos informacija
 - mygtukas DELETE - leidzia pasalinti knyga
 - mygtukas LogOut - leidzia atsijungti nuo Admin roles
 - Language change drop down list - leidzia pasirinkti puslapio vertima i Anglu arba Lietuviu kalba.



 # Getting Started
 To run this application change application.properties file