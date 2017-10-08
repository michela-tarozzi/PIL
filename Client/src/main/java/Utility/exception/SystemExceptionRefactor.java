package Utility.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class SystemExceptionRefactor extends RuntimeException {
/*Rispetto ad una mappa, la tree map garantisce che l'iterazione dei suoi elementi avviene in ordine in modo ascendente rispetto alla chiave in
modo da poter recuperare i valori in modo più veloce*/
private final Map<String, Object> properties = new TreeMap<String, Object>();
private String DESCRIZIONE = "Descrizione";         //Questo è il messaggio umanamente leggibile (si spera)
private String COMPONENTE = "Componente";           //Il codice del componente dove nasce l'eccezione
private String STACKTRACE = "StackTrace";           //lo stacktrace di trowabili cosi' possiamo salvare su Log4j i dettagli
private String ECCEZIONE = "Eccezione";              //il throwable che scatena tutto

/*
Se si invoca questo metodo invece del costruttore, è il metodo stesso che decide se occore sollevare l'eccezione originale.
In questo modo si evita di percorrere tutto lo skack trace delle eccezioni se l'eccezione è di tipo SystemExceptio e dunque
la vogliamo gestire noi.
*/
public static SystemExceptionRefactor wrap(Throwable exception, ExceptionCode errorCode) {
    /*Qui è il posto giusto per inserire una qualcosa per la gestione specifica delle eccezzioni
    * tipo capire che roba sono controllando exception.....e magari chiamare handler esterni specifici
    * tipo una classe a parte per hibernate e DAO che torna alla fine una SystemExceptionRefactor */
    if (exception instanceof SystemExceptionRefactor) {
        SystemExceptionRefactor se = (SystemExceptionRefactor) exception;
        if (errorCode != null && errorCode != se.getErrorCode()) {
            return new SystemExceptionRefactor(exception.getMessage(), exception, errorCode);
        }
        return se;
    } else {
        return new SystemExceptionRefactor(exception.getMessage(), exception, errorCode);
    }
}

//Se non mi passano un codice d'errore sono pigri e non vogliono stare a definirli ma pace..
public static SystemExceptionRefactor wrap(Throwable exception) {
    return wrap(exception, null);
}


/**
 * -------------------------INIZIO COSTRUTTORI -------------------------------------------
 * Vari Costruttori ... pensare a queli servono e lasciarli fuori come pubblici e privatizzare il resto
 */

//Senza Questo costruttore non posso WRAPPARE le Eccezzioni altrui
public SystemExceptionRefactor() {
}

//Con questo costruttore posso creare mie istanze custom per gestire cose
public SystemExceptionRefactor(String message, ExceptionCode errorCode) {
    this.properties.put(DESCRIZIONE, getErroreUtente(message));
    this.properties.put(COMPONENTE, errorCode);
    PrintException();
}

//Questo e' privato così fuori da qua non mi prudono le manine e mi chiedo sempre se sto wrappando o meno.... e
// nel caso chiamo wrap che se la vede solo
private SystemExceptionRefactor(String message, Throwable cause, ExceptionCode errorCode) {
    this.properties.put(DESCRIZIONE, getErroreUtente(message));
    this.properties.put(COMPONENTE, errorCode);
    this.properties.put(STACKTRACE, cause.getStackTrace());  //lo stacktrace alla fine sta nell'eccezione...
    this.properties.put(ECCEZIONE, cause);
    PrintException();
}

/**
 * ----------------------FINE COSTRUTTORI -------------------------------------------
 **/

public ExceptionCode getErrorCode() {
    return (ExceptionCode) this.properties.get(COMPONENTE);
}


//Torna la mappa che contiene l'errore..... a che serve pubblica?
private Map<String, Object> getProperties() {
    return properties;
}


//Questo viene usato dove si vuole una descrizione dell'errore per tutto il resto si usa lo stacktrace
//che  poi sto coso torna un T a capocchia quindi si potrebbe pure strutturare la cosa...
public <T> T getDescription() {
    return (T) properties.get(DESCRIZIONE);

}

//Lo StackTrace
public StackTraceElement[] getStackTrace() {
    return (StackTraceElement[]) properties.get(STACKTRACE);
}

//Questa Funzione fa un bel print dell'eccezione appena creata così la possiamo vedere a schermo, Appendere a Log4j ecc..
private void PrintException() {

    try {
        StringWriter StackTraceWriter = new StringWriter();
        if (properties.get(STACKTRACE) != null) {
            PrintWriter pw = new PrintWriter(StackTraceWriter);
            Throwable stackTraceElements = (Throwable) properties.get(ECCEZIONE);
            stackTraceElements.printStackTrace(pw);
        }
        ExceptionCode exceptionCode = (ExceptionCode) properties.get(COMPONENTE);
        System.out.println("[Exception Manager] \nDescrizione Errore: " + properties.get(DESCRIZIONE)
                + "\nComponente che lo ha generato: " + exceptionCode.getNumber()
                + (properties.get(STACKTRACE) != null ? "\nStackTrace: " + StackTraceWriter.toString():""));
    } catch (Exception e) {
        System.out.print("Impossibile stampare l'eccezione " + e.getMessage());

    }
}


private String getErroreUtente(String error) {
    if (error == null) {
        return null;
    }
    try {
        ResourceBundle bundle = ResourceBundle.getBundle("properties.StringError");
        //Potrebbe essere brutto ma e' pratico quando mi settano un messaggio potrebbe essere
        // un qualcosa di localizzato nel caso lo cerco....
        return bundle.getString(error);
    }
    //Se prorio nn lo trovo do fuori l'eccezione cosi' come sta e peace
    catch (Exception e) {
        return error;
    }
}
}
