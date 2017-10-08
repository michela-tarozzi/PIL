package Utility;

import java.util.HashMap;

/**
 * Created by b.pesty on 04/11/2016.
 */
public class ControllersDispatcher {

    private static HashMap<Object, Object> controllerMap = init();

    private static HashMap init() {
        if (controllerMap == null) {
            return new HashMap();
        }
        return controllerMap;
    }

    public static Object getController(Object key) {

        return controllerMap.get(key);
    }

    public static Object getControllers() {

        return controllerMap;
    }

    public static void setController(Object key, Object value) {
        controllerMap.put(key, value);
    }

    public static void removeController(Object key) {
        controllerMap.remove(key);
    }

}
