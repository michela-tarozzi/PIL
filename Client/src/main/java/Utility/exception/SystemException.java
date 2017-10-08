package Utility.exception;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class SystemException extends RuntimeException {


    private ErrorCode errorCode;
    /*Rispetto ad una mappa, la tree map garantisce che l'iterazione dei suoi elementi avviene in ordine in modo ascendente rispetto alla chiave in
    modo da poter recuperare i valori in modo più veloce*/
    private final Map<String,Object> properties = new TreeMap<String,Object>();

    private String DESCRIPTION_KEY = "Descrizione";



    /*
    Se si invoca questo metodo invece del costruttore, è il metodo stesso che decide se occore sollevare l'eccezione originale.
    In questo modo si evita di percorrere tutto lo skack trace delle eccezioni se l'eccezione è di tipo SystemExceptio e dunque
    la vogliamo gestire noi.
    */
    public static SystemException wrap(Throwable exception, ErrorCode errorCode) {
        if (exception instanceof SystemException) {
            SystemException se = (SystemException)exception;
            if (errorCode != null && errorCode != se.getErrorCode()) {
                return new SystemException(exception.getMessage(), exception, errorCode);
            }
            return se;
        } else {
            return new SystemException(exception.getMessage(), exception, errorCode);
        }
    }

    public static SystemException wrap(Throwable exception) {
        return wrap(exception, null);
    }


    public SystemException(){

    }



    public SystemException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public SystemException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public SystemException(Throwable cause, ErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public SystemException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public SystemException setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    @SuppressWarnings("unchecked")
    private <T> T get(String name) {

        return (T)properties.get(name);
    }

    private SystemException set(String name, Object value) {
        properties.put(name, value);
        return this;
    }

    public <T>T  getDescription() {
        return (T)properties.get(DESCRIPTION_KEY);

    }

    public SystemException setDescription(Object value){
        properties.put(DESCRIPTION_KEY, value);
        return this;
    }


    public static String getErrorText(String error) {
        if (error == null) {
            return null;
        }

        ResourceBundle bundle = ResourceBundle.getBundle("properties.StringError");
        return bundle.getString(error);
    }

}
