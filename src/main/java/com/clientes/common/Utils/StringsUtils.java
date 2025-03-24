package com.clientes.common.Utils;

import java.util.Objects;

public class StringsUtils {
    private StringsUtils() {
    }
    public static boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }
    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }
    public static boolean isEmpty(CharSequence string) {
        return Objects.isNull(string) || isEmpty(string.toString());
    }
    public static boolean isNotEmpty(CharSequence string) {
        return !isEmpty(string);
    }
    public static String capitalize(String string){
        if(isEmpty(string)) return string;
        if(string.length() == 1) return string.toUpperCase();
        String[] spa = string.split(" ");
        String ret = "";
        for (String s :
                spa) {
            if(isEmpty(string)) continue;
            if(s.length() == 1)
                ret += " " + s;
            else
                ret += " " + s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
        }
        return ret.substring(1);
    }
    public static String truncate(String str, int length) {
        if (isEmpty(str)) {
            return "";
        }
        if (length < 0) {
            return str;
        }
        return str.codePoints()
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
