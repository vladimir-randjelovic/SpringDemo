#SpringDemo aplikacija
SpringDemo aplikacija napravljena je sa ciljem da, na jednostavan način, prikaže osnove Spring framework-a, izrade REST servisa i premenu JPA specifikacije za pristup MySQL bazi podataka.  Za razvoj aplikacije korišćene su SpringBoot biblioteke koje dozvoljavaju brz i jednostavan razvoj, kao i primenu ugrađenog TomCat servera. Imajući u vidu da je aplikacija urađena uz pomoć Maven build tool-a, pregled aplikacije, kao i njenu izmenu moguće je vršiti uz pomoć NetBeans razvojnog okruženja. Za testiranje je neophodno koristiti neki od REST klijenata (npr. Postman).
Aplikacije sa može klonirati sa GitHub lokacije: https://github.com/vladimir-randjelovic/SpringDemo.git
Razvoj aplikacije
Kako bi se na jednostavan način upoznali sa aplikacijom, potrebno je prvo klonirati repozitorijum sa prethodno navedene Git adrese, posle čega je pogodno koristiti neki od Git klijenata kako bi, na jednostavan način, mogli da izvršimo check out sa jednog commit-a na drugi.
##Commit 1
Prvi commit na repozitorijumu koristi samo dve klase koje su dovoljne za pokretanje Spring projekta sa jednim REST handler-om :
1.	Application.java
Klasa koja se koristi za pokretanje Spring aplikacije
2.	HelloController.java 
Najjednostavniji REST controller koji vraća string "Hello world" kada iz REST klijenta stigne GET request na adresu localhost:8093/
##Commit 2
Drugi commit dopunjen je sa još dve metode. Prva metoda getMethod() prikazuje obradu GET zahteva sa adresom /rest/test
```
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<String> getMethod(@RequestParam String name) {
        return new ResponseEntity<>("/test GET method response. Name: "+name, HttpStatus.OK);
    }
```
Druga methoda obrađuje POST zahtev sa adresom /rest/test
```
    @RequestMapping(value = "/test/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postMethod(@PathVariable(value = "id") int id, @RequestBody String body) {
        return new ResponseEntity<>("/test POST method response. ID: "+id+"      Body: "+body, HttpStatus.OK);
    }
```	
##Commit3
Treći commit odnosi se na primenu JPA specifikacije za pristup MySQL bazi. Iz ovog razloga napravljene su dve entity klase UserAccount.java i Car.java, dva repository interface-a UserRepository.java i CarRepository.java, kao i REST controller DbController.java koji se koristi za funkcije upisa i čitanje baze podataka.
Za pristup bazi koristi se sledeća konfiguracija:
```
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/db_example
spring.datasource.username=root
spring.datasource.password=root
```
##Commit 4
Četvrti commit vrši reorganizaciju klasa po paketima kako bi odvojio entity klase, kontrolere i repozitorijum interfejse.
##Commit 5
Peti commit dodaje query metode repository interfejsima.

