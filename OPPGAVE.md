# PGR203 Innlevering 2: HTTP Server


I denne oppgaven skal dere lage en HTTP-server som 

1. Kan levere filer fra under `src/main/resources`
2. Kan svare på noen `/echo`-requester

Oppgaven skal utvikles med parprogrammering og test-drevet utvikling og dere må ha et omfattende sett med tester som skal kjøre automatisk med Travis CI. Oppgaven skal utvikles med GitHub Classroom og dere må benytte linken dere har fått i Canvas til repository. Repository må linke til Travis CI.

Maven prosjektet skal bygge en executable jar fil med `mvn package` og denne denne jar-filen skal når man starter `java -jar ...` printe ut hvilken URL den starter på og den skal kunne håndtere flere HTTP-requester.

## Sjekkliste for å få godkjent

* [ ] Koden er sjekket inn på github.com/Westerdals-repository
* [ ] `README.md` inneholder en korrekt link til Travis CI
* [ ] `mvn package` bygger en executable jar-fil
* [ ] `java -jar target/...jar` (etter `mvn package`) starter opp en webserver
* [ ] `README.md` beskriver prosjektet, hvordan man bygger det, hvordan man kjører serveren og hvilken URL den starter på  
* [ ] Webserveren kan svare på ECHO-requests
    * [ ] `/echo?status=<code>`
    * [ ] `/echo?status=302&location=<url>`
    * [ ] `/echo?body=Hello+world`
* [ ] Webserveren kan levere `index.html`
* [ ] Webserveren kan serve mer enn én http request før den må restartes
* [ ] Koden inneholder et godt sett med tester og testene kjører i Travis CI. Test av serveren skal bruke deres egen http client klasse
* [ ] Koden inneholder en HttpClient klasse med tester mot `http://urlecho.appspot.com`
* [ ] GitHub repository er private
* [ ] Veilederne er lagt til som Collaborators på GitHub repository (`alacho2`, `aridder`, `asmadsen`)
* [ ] Dere har lastet opp ZIP (Download fra GitHub-siden til repository) og lagt ved link til GitHub repository i Canvas

## Sjekkliste for god leveranse

* [ ] `.gitignore` hindrer `target/`, `.idea` og `*.iml` fra å sjekkes inn ved uhell
* [ ] Serveren hindrer klienten fra å lese filer utenfor `src/main/resources`
* [ ] Navn på pakker, klasser og metoder skal følge vanlig Java-konvensjon når det gjelder små og store bokstaver
* [ ] Indentering skal følge vanlig Java-konvensjon
* [ ] `README.md` inneholder link til en diagram som viser strukturen på koden
* [ ] Server skriver nyttige loggmeldinger, inkludert informasjon om hvilken URL den kjører på ved oppstart

