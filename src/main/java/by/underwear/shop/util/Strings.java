package by.underwear.shop.util;

public final class Strings {
    private Strings() {
    }

    public static boolean isNotEmpty(String s) {
        return s != null && !s.isEmpty();
    }
}