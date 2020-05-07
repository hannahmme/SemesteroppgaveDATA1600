# SemesteroppgaveDATA1600

Dette er vår semesteroppgave i Programutvikling 1600.

Hannah M.M. Eriksen s325340

Amalie Leiknes s340559

Caroline Jetteberg s313564

## Konfigureringssystem for datamaskiner
  Et konfigurasjonssystem laget med JavaFX. Systemet består av et GUI for sluttbruker som kan bestille en egentilpasset pc, og et GUI for superbruker som kan administrere produktene som er med i tilpasningen. 
  Startsiden
  Applikasjonen starter med en side med tre valg. Her kan superbruker logge inn på sin side helt øverst, sluttbruker kan klikke seg videre for å konfigurere sin egen datamaskin eller sluttbruker kan logge seg inn til venstre for å se sine gamle ordre.
  
  
  ####Brukere
  
  Admin kan logge inn ved å bruke brukernavn og passord “admin”. Begge felt må være fylt ut riktig for å komme videre til adminsiden.
  - Admin kan gå videre til administrering av produkter
  - Admin kan se alle tidligere ordre
  - Admin kan se alle kunder som opprettet
  
  Sluttbrukeren kan logge inn med epost og passord for å se tidligere ordre. Følgende brukere med epost og passord er allerede definert i systemet:
  - bruker@bruker.no; bruker
  - bruker2@bruker.no; bruker2
  - bruker3@bruker.no; bruker3
  - eksempel@eksempel.no; eksempel
  - test@test.no; test
  
  
  ###Administrering av produkter
  
  Etter å ha logget inn som admin og gått inn på siden for å administrere produkter har admin flere muligheter. 
  - Endre eller slette allerede eksisterende produkter
  - Lage helt nye produkter
  - Importere en liste med produkter som erstatter produktene som allerede eksisterer. Disse vil da kunne hentes fra en forhåndsdefinert filsti, men det kan også importeres binære filer fra andre mapper.
  - Lagre produkter til fil for senere bruk. Filene vil bli foreslått lagret i en forhåndsdefinert mappe , men kan lagres andre steder om ønskelig.
  
  Filene lagres og åpnes automatisk fra/ til ./datamaskin/binaryFilesPath for bedre oversikt og brukervennlighet. Her ligger det tre filer som kan importeres:
  - importAllProducts   	: Alle allerede definerte produkter
  - importHalfEmptyList 	: En halvfull liste med produkter (vil kaste feilmelding til brukeren om å opprette minst èn komponent i hver kategori for å kunne gå til hovedsiden)
  - importEmptyList     	: En importfil helt uten produkter
  
  
  ###Konfigurering av komponenter
  
  Brukeren vil gå gjennom tre ulike sider ved konfigurering av sin pc.
  - På første side må sluttbruker velge essensielle komponenter. Disse er listet opp i de ulike nedtrekkslistene på venstre side. På høyre side vil det være en handlekurv som oppdateres når bruker har valgt en verdi i alle boksene og trykket på “Oppdater handlekurv med valgte komponenter”. Dersom ikke alle boksene har en verdi vil det komme ut en feilmelding. Boksene kan også endres. Totalpris og forventet levetid for valgte komponenter vises under handlekurven.
  - På neste side kan bruker velge å legge til ekstra produkter om det er ønskelig. Ekstra produkter kan slettes fra listen, mens essensielle komponenter kun kan endres fra den første brukersiden. Handlekurv, totalpris og levetid vises fortsatt på høyre side.
  - På den siste siden kan bruker ferdigstille ordren. Her er det nødvendig å logge inn med en epost og et passord. Dersom man ikke har handlet her før kan man opprette en ny bruker. Når innloggingsdetaljer er skrevet inn korrekt og bruker har sendt bestillingen vil det kun være mulig å opprette nye brukere eller å gå tilbake til startsiden.
  
  
  ###Ordre
  
  Etter at bruker har sendt sin ordre vil den få tildelt et ordrenummer som vises på skjermen. Bruker kan så velge mellom å gå tilbake til startsiden for å lage en ny ordre eller for å se sine tidligere ordre. Ordrene blir lagret under ./datamaskin/sentOrdersPath og hentes også frem herfra hvis bruker eller admin vil se tidligere ordre, her vil ikke sluttbruker få muligheten til å velge egendefinert filsti.
  Her ligger det ti ordre inne som eksempelinfo, alle er uten feil.
