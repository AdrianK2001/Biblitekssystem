# Bibliotekssystem - Testning

Denna README beskriver teststrategin och testerna som har skrivits för att säkerställa funktionaliteten i applikationen. Tester har implementerats för att verifiera funktionaliteten i controller och servicelagret, samt interaktionen mellan dessa och databasen.

## Teststrategi

Teststrategin för detta projekt omfattar tre huvudsakliga typer av tester:

1.   Enhetstester:
   - Enhetstester används för att testa den isolerade logiken i tjänster (service-klasser), där externa beroenden som databasåtkomst eller externa API-anrop mockas.

2.   Komponenttester:
   - Komponenttester syftar till att testa interaktionen mellan controller och tjänstelager. Repository och externa beroenden mockas för att isolera testet från databas och externa API-anrop.

3.   Integrationstester:
   - Integrationstester används för att verifiera hela systemet från controller till databas. Här testas det faktiska flödet av data genom applikationen för att säkerställa att alla delar fungerar tillsammans.

De tre typerna av tester som är implementerade i denna applikation är:

- LoanServiceTest - Enhetstest
- LoanControllerTest - Komponenttest
- LoanIntegrationTest - Integrationstest

## Testade Metoder

    1.   LoanServiceTest (Enhetstest)
   -   Metod som testas: createLoan(Long bookId, String borrower) och getAllLoans().
   -   Motivering:
     -  createLoan() testar logiken för att skapa ett nytt lån och verifierar att det skickas korrekt till repositoryn.
     -  getAllLoans() testar att alla lån kan hämtas från repositoryn.
   -   Varför dessa metoder?: Dessa metoder är viktiga att testa för att säkerställa att tjänstelagret hanterar logiken korrekt, t.ex. att ett lån kan skapas och att alla lån kan hämtas.

    2.   LoanControllerTest (Komponenttest)
   -   Metod som testas: POST /loan (skapa ett lån)
   -   Motivering:
     - Här testas controller och tjänstelagret tillsammans. POST-anropet skapar ett lån och verifierar att controller returnerar korrekt status och värde.
   -   Varför denna metod?: Eftersom controllern är den del som hanterar HTTP-anrop, är det viktigt att verifiera att den korrekt hanterar inkommande förfrågningar och samverkar med tjänstelagret.

### 3.   LoanIntegrationTest (Integrationstest)
   -   Metod som testas: Skapa och hämta lån via controller och repository
   -   Motivering:
     - Testar hela kedjan från controller till databas. Skapar ett lån via controller och verifierar att det sparas korrekt i databasen och kan hämtas via en GET-förfrågan.
   -   Varför denna metod?: Det är viktigt att testa hela flödet i applikationen, inklusive controller, tjänstelager och databas, för att säkerställa att alla delar samverkar korrekt.

## Hur man kör testerna

För att köra testerna, följ dessa steg:

### Förutsättningar
1.   Maven: Testerna använder Maven för att hantera beroenden och bygga projektet. Se till att du har en korrekt installerad version av Maven.
2.   MySQL-databas: För integrationstester krävs en fungerande MySQL-databas.

### Steg för att köra testerna:

   Öppna terminalen och skriv följande:
   git clone https://github.com/ditt-username/Bibliotekssystem.git
   cd Bibliotekssystem

   Använd valfri texteditor för att öppna upp projektet exempel Visual Studio Code, IntelliJ.
   Lokalisera mappen som heter "test" och klicka dig fram till de olika "testklasserna".
