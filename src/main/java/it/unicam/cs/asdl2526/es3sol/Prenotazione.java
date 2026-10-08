package it.unicam.cs.asdl2526.es3sol;

/**
 * Rappresenta la prenotazione di una certa aula in un determinato
 * {@link TimeSlot}.
 * <p>
 * Questa classe viene usata per esercitarsi sulla distinzione tra lo
 * <em>stato complessivo</em> di un oggetto e i campi che ne determinano
 * l'identità logica. Una prenotazione contiene infatti anche il docente e il
 * motivo, ma due prenotazioni sono considerate uguali quando riguardano la
 * stessa aula e lo stesso time slot. Di conseguenza {@link #equals(Object)},
 * {@link #hashCode()} e {@link #compareTo(Prenotazione)} sono progettati in
 * modo coerente con questa scelta.
 * <p>
 * L'ordinamento naturale delle prenotazioni è prima per time slot e, a parità
 * di time slot, per nome dell'aula secondo l'ordinamento naturale delle
 * stringhe.
 *
 * @author Luca Tesei
 */
public class Prenotazione implements Comparable<Prenotazione> {

    private final String aula;

    private final TimeSlot timeSlot;

    private String docente;

    private String motivo;

    /**
     * Costruisce una prenotazione specificando tutti i valori del suo stato.
     * Nessun parametro può essere {@code null}.
     *
     * @param aula l'aula a cui la prenotazione si riferisce
     * @param timeSlot il time slot della prenotazione
     * @param docente il nome del docente che ha prenotato l'aula
     * @param motivo il motivo della prenotazione
     * @throws NullPointerException se almeno uno dei parametri è {@code null}
     */
    public Prenotazione(String aula, TimeSlot timeSlot, String docente,
                        String motivo) {
        if (aula == null)
            throw new NullPointerException(
                    "Tentativo di costruire una prenotazione senza aula");
        if (timeSlot == null)
            throw new NullPointerException(
                    "Tentativo di costruire una prenotazione senza time slot");
        if (docente == null)
            throw new NullPointerException(
                    "Tentativo di costruire una prenotazione senza docente");
        if (motivo == null)
            throw new NullPointerException(
                    "Tentativo di costruire una prenotazione senza motivo");
        this.aula = aula;
        this.timeSlot = timeSlot;
        this.docente = docente;
        this.motivo = motivo;
    }

    /**
     * Restituisce l'aula associata a questa prenotazione.
     *
     * @return l'aula di questa prenotazione
     */
    public String getAula() {
        return aula;
    }

    /**
     * Restituisce il time slot associato a questa prenotazione.
     *
     * @return il time slot di questa prenotazione
     */
    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    /**
     * Restituisce il docente associato a questa prenotazione.
     *
     * @return il docente di questa prenotazione
     */
    public String getDocente() {
        return docente;
    }

    /**
     * Restituisce il motivo associato a questa prenotazione.
     *
     * @return il motivo di questa prenotazione
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Modifica il docente associato a questa prenotazione. Questo campo non
     * partecipa al criterio di uguaglianza e quindi la modifica non cambia
     * l'identità logica della prenotazione.
     *
     * @param docente il nuovo docente
     * @throws NullPointerException se {@code docente} è {@code null}
     */
    public void setDocente(String docente) {
        if (docente == null)
            throw new NullPointerException("Il docente non può essere null");
        this.docente = docente;
    }

    /**
     * Modifica il motivo associato a questa prenotazione. Questo campo non
     * partecipa al criterio di uguaglianza e quindi la modifica non cambia
     * l'identità logica della prenotazione.
     *
     * @param motivo il nuovo motivo della prenotazione
     * @throws NullPointerException se {@code motivo} è {@code null}
     */
    public void setMotivo(String motivo) {
        if (motivo == null)
            throw new NullPointerException("Il motivo non può essere null");
        this.motivo = motivo;
    }

    /**
     * Verifica l'uguaglianza logica tra questa prenotazione e un altro oggetto.
     * Due prenotazioni sono uguali se e solo se hanno la stessa aula e lo
     * stesso time slot. Il docente e il motivo non partecipano al confronto e
     * possono quindi essere diversi in due prenotazioni considerate uguali.
     *
     * @param obj l'oggetto con cui confrontare questa prenotazione
     * @return {@code true} se {@code obj} è una prenotazione con la stessa aula
     *         e lo stesso time slot, {@code false} altrimenti
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Prenotazione))
            return false;
        Prenotazione other = (Prenotazione) obj;

        /*
         * Solo aula e timeSlot costituiscono l'identità logica. Docente e
         * motivo appartengono allo stato dell'oggetto, ma possono cambiare
         * senza trasformarlo in una prenotazione diversa.
         */
        return this.aula.equals(other.aula)
                && this.timeSlot.equals(other.timeSlot);
    }

    /**
     * Restituisce un codice hash coerente con {@link #equals(Object)}.
     * <p>
     * Poiché l'uguaglianza di due prenotazioni dipende soltanto da aula e time
     * slot, anche il codice hash è calcolato a partire da questi due campi. In
     * particolare, due prenotazioni uguali hanno sempre lo stesso codice hash,
     * anche se hanno docente o motivo diversi.
     *
     * @return il codice hash di questa prenotazione
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        /*
         * Usiamo esattamente gli stessi campi scelti da equals. Inserire qui
         * docente o motivo violerebbe il contratto equals/hashCode, perché due
         * prenotazioni considerate uguali potrebbero produrre hash diversi.
         */
        result = prime * result + aula.hashCode();
        result = prime * result + timeSlot.hashCode();
        return result;
    }

    /**
     * Confronta questa prenotazione con un'altra secondo l'ordinamento naturale
     * della classe. Si confrontano prima i time slot; se sono uguali si
     * confrontano i nomi delle aule secondo l'ordinamento naturale di
     * {@link String}.
     * <p>
     * L'ordinamento è compatibile con {@link #equals(Object)}: il metodo
     * restituisce zero se e solo se le due prenotazioni sono uguali. Docente e
     * motivo non partecipano quindi neppure all'ordinamento.
     *
     * @param o la prenotazione con cui effettuare il confronto
     * @return un valore negativo se questa prenotazione precede {@code o}, zero
     *         se le due prenotazioni sono uguali, un valore positivo se questa
     *         prenotazione segue {@code o}
     * @throws NullPointerException se {@code o} è {@code null}
     */
    @Override
    public int compareTo(Prenotazione o) {
        if (o == null)
            throw new NullPointerException(
                    "Tentativo di confrontare una prenotazione con null");

        /*
         * Ordinamento lessicografico sugli stessi campi dell'identità logica:
         * prima il time slot e, solo a parità di time slot, l'aula.
         */
        int cmp = this.timeSlot.compareTo(o.timeSlot);
        if (cmp != 0)
            return cmp;
        return this.aula.compareTo(o.aula);
    }

    /**
     * Restituisce una rappresentazione testuale dello stato della prenotazione.
     * Il metodo costruisce e restituisce una stringa: non effettua alcuna stampa
     * sullo standard output.
     *
     * @return una rappresentazione testuale di questa prenotazione
     */
    @Override
    public String toString() {
        return "Prenotazione [aula = " + aula + ", time slot =" + timeSlot
                + ", docente=" + docente + ", motivo=" + motivo + "]";
    }
}
