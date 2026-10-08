# Esercitazione 3 — Uguaglianza, hash code e ordinamento naturale

## Obiettivo dell'esercitazione

Questa esercitazione riprende alcuni concetti fondamentali della programmazione orientata agli oggetti in Java e li applica a due classi che modellano un semplice dominio di prenotazione delle aule.

L'obiettivo principale è imparare a distinguere:

- lo **stato complessivo** di un oggetto;
- i campi che ne determinano l'**identità logica**;
- il criterio usato per stabilire l'**uguaglianza** tra oggetti;
- i campi che devono essere utilizzati per calcolare un **codice hash coerente con `equals`**;
- il criterio con cui definire un **ordinamento naturale compatibile con `equals`**.

L'esercitazione permette inoltre di lavorare sull'immutabilità degli oggetti e sulla gestione di oggetti mutabili usati come componenti dello stato.

## Le classi

### `TimeSlot`

Un `TimeSlot` rappresenta un intervallo di tempo delimitato da un istante di inizio e da un istante di fine.

Un time slot valido deve avere un istante iniziale strettamente precedente a quello finale.

Gli oggetti `TimeSlot` devono essere **immutabili**. Gli istanti sono rappresentati mediante `GregorianCalendar`, che è invece una classe mutabile: occorre quindi prestare attenzione a non permettere che lo stato interno del time slot possa essere modificato dall'esterno.

Due time slot sono logicamente uguali quando rappresentano esattamente lo stesso intervallo temporale. L'ordinamento naturale confronta prima l'istante di inizio e, a parità di inizio, l'istante di fine.

La classe offre inoltre operazioni per calcolare i minuti di sovrapposizione tra due intervalli e per stabilire se la sovrapposizione supera una soglia di tolleranza.

### `Prenotazione`

Una `Prenotazione` associa un'aula a un `TimeSlot` e contiene inoltre il nome del docente e il motivo della prenotazione.

La scelta importante ai fini dell'esercizio è che **l'identità logica di una prenotazione è determinata soltanto dall'aula e dal time slot**. Il docente e il motivo possono cambiare senza che la prenotazione diventi, dal punto di vista logico, una prenotazione diversa.

Di conseguenza:

- `equals` deve usare aula e time slot;
- `hashCode` deve essere costruito usando gli stessi campi utilizzati da `equals`;
- `compareTo` deve confrontare prima il time slot e, a parità di time slot, l'aula;
- `compareTo` deve restituire `0` se e solo se le due prenotazioni sono uguali secondo `equals`.

## Metodi da implementare

Completare tutti i punti indicati con `TODO` nelle classi `TimeSlot` e `Prenotazione`, rispettando esattamente il contratto descritto dalla documentazione Javadoc.

In particolare, l'esercitazione richiede di ragionare sui seguenti aspetti:

- controllo dei parametri e rispetto degli invarianti del costruttore;
- copie difensive necessarie per garantire l'immutabilità di `TimeSlot`;
- ridefinizione di `equals`;
- ridefinizione di `hashCode` coerente con `equals`;
- implementazione dell'interfaccia `Comparable` e di `compareTo`;
- compatibilità tra `compareTo` ed `equals`;
- calcolo della sovrapposizione di intervalli temporali;
- soglia di tolleranza per la sovrapposizione;
- ridefinizione di `toString`.

## Test JUnit

Nel progetto sono presenti test JUnit che costituiscono parte del materiale dell'esercitazione.

È importante leggerli oltre che eseguirli: ciascun test rappresenta uno scenario concreto previsto dal contratto delle classi. I test verificano sia i casi ordinari sia alcuni casi limite, per esempio:

- parametri `null` e intervalli temporali non validi;
- uguaglianza tra oggetti distinti nello heap;
- coerenza tra `equals` e `hashCode`;
- compatibilità tra `compareTo` ed `equals`;
- immutabilità di `TimeSlot` mediante copie difensive;
- intervalli disgiunti, adiacenti, parzialmente sovrapposti e contenuti;
- sovrapposizioni inferiori, uguali o superiori alla soglia di tolleranza;
- arrotondamento per difetto dei minuti di sovrapposizione.

L'obiettivo non è modificare i test per farli passare, ma implementare le API in modo che il comportamento delle classi rispetti le specifiche.

## Consegna

La consegna deve essere effettuata nel **compito corrispondente su Moodle del corso**.

Devono essere consegnati esclusivamente i due file Java contenenti le implementazioni richieste:

- `TimeSlot.java`
- `Prenotazione.java`

Prima della consegna è consigliato eseguire tutti i test JUnit del progetto e verificare che terminino con successo.

## Collegamento con la prova di laboratorio

Le esercitazioni settimanali riproducono la struttura dei progetti Maven utilizzati nella prova di codifica del laboratorio. L'obiettivo è abituarsi a leggere una specifica espressa tramite API e Javadoc, individuare gli invarianti dello stato degli oggetti e realizzare un'implementazione che soddisfi tutti i casi previsti dai test.
