package Utility.exception;

import org.hibernate.HibernateException;

/**
 * Created by felline on 17/10/2016.
 */
public class HibernateSpecificExceptionHandler extends SystemExceptionRefactor {

public static String gestisciHibernate(HibernateException ex) {
    return "Bello errore hibernate";
}
}
