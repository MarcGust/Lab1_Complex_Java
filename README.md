Movie Management Application
Detta är en enkel applikation för hantering av filmer med CRUD-operationer (Skapa, Läs, Uppdatera, Ta bort). Applikationen använder Java EE, WildFly, PostgreSQL och Docker.

Funktioner
Skapa, visa, uppdatera och ta bort filmer.
Filtrera filmer efter titel, regissör och genre.
Teknologier
Java EE (Jakarta EE)
WildFly (JEE-container)
PostgreSQL (databas)
Docker (för containerisering)

API
GET /movies – Hämta alla filmer.
POST /movies – Skapa en ny film.
PUT /movies/{id} – Uppdatera en film.
DELETE /movies/{id} – Ta bort en film.
GET /movies/filter – Filtrera filmer.
