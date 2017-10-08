package Utility;

/**
 * Created by a.cerioni on 05/05/2017.
 */

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author robgion
 *
 */
public class ResourceUtils {

    public static final String RESOURCE_FILE_BUNDLE_PATH = "i18n/bundle";

    /**
     * Recupera un messaggio dal resource bundle tramite la chiave.
     *
     * @param key
     * @param lang
     * @return
     */
    public static String getMessageBundleByKey(String key, String lang) {
        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_FILE_BUNDLE_PATH, new Locale(lang));
        return bundle.getString(key);
    }


}