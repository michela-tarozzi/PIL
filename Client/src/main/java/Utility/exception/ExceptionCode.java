package Utility.exception;

/**
 * Created by acerioni on 07/10/2016.
 */

public class ExceptionCode implements ErrorCode {
private static int DAO = 300;
private static int CONTROLLER = 301;
private static int VIEW = 302;
private static int UTIL = 303;
private static int VALIDAZIONE=303;

private final int number;
@Override
public int getNumber() {
    return number;
}

private ExceptionCode(int number) {
    this.number = number;
}

public static ExceptionCode getDAOErrorCode() {
    return new ExceptionCode(DAO);
}

public static ExceptionCode getControllerCode() {
    return new ExceptionCode(CONTROLLER);
}

public static ExceptionCode getViewCode() {
    return new ExceptionCode(VIEW);
}

public static ExceptionCode getUtilCode(){return new ExceptionCode(UTIL);}

public static ExceptionCode getValidazioneCode(){return new ExceptionCode(VALIDAZIONE);}
}
