package com.clientes.common.Utils;

import java.util.Collection;
import java.util.List;

public class ListUtils {
    private ListUtils() {
    }
    public static  boolean isEmpty(Collection<?> lst) {
        return lst == null || lst.isEmpty();
    }
    public  static  boolean isNotEmpty(Collection<?> lst) {
        return !isEmpty(lst);
    }

    public static  boolean isEmpty(Object... args) {
        return args == null || args.length == 0;
    }

    public static  boolean isNotEmpty(Object... args) {
        return !isEmpty(args);
    }

    public static String listAsString(String separador, List lista){
        String ret = "";
        if(isEmpty(lista)) return  ret;
        for(Object o: lista){
            ret += separador + o;
        }
        return ret.substring(separador.length());
    }
}
