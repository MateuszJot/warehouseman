package pl.krakow.uken.mateuszjachowicz.warehouseman.lib.config;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Context {
    private static final ThreadLocal<String> AUTH_TOKEN = new ThreadLocal<>();

    public static void setAuthToken(String authToken) {
        AUTH_TOKEN.set(authToken);
    }

    public static String getAuthToken() {
        return AUTH_TOKEN.get();
    }

    public static void clearAuthToken() {
        AUTH_TOKEN.remove();
    }
}
