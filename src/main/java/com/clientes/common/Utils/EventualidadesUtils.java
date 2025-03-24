package com.clientes.common.Utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class EventualidadesUtils {

    /**
     * Metodo que devuelve el calculo de dias vividos. Este calculo lo hace tomando de referencia la fecha de nacimiento registrada del cliente
     * @param fechaNacimiento parametro enviado para el calulo
     * @return retorna un dato de tipo Long con la cantidad de dias calculados
     */
    public long diasVividos(LocalDate fechaNacimiento) {
        return ChronoUnit.DAYS.between(fechaNacimiento, LocalDate.now());
    }

    /**
     * Este metodo calcula su proxima fecha de cumpleaños del cliente, tomando de referencia la fecha de nacimiento
     * @param fechaNacimiento parametro enviado para calular
     * @return retorna una fecha de su proximo cumpleaño
     */
    public LocalDate proximoCumple(LocalDate fechaNacimiento) {
        LocalDate proximoCumpleanos = fechaNacimiento.withYear(LocalDate.now().getYear());
        if (proximoCumpleanos.isBefore(LocalDate.now())) {
            proximoCumpleanos = proximoCumpleanos.plusYears(1);
        }
        return proximoCumpleanos;
    }

    /**
     * Metdodo que calcula su fecha aproximada de fallecimiento tomando en concideracion que este calculo se ha hecho con una esperanza de vida de 80 años
     * @param fechaNacimiento parametro enviado para calular
     * @return retorna una fecha la cual es posiblemente su fallecimiento en un ccaso hipotetico
     */
    public LocalDate calcularFechaFallecimiento(LocalDate fechaNacimiento) {
        return fechaNacimiento.plusYears(80);
    }

    /**
     * Este metodo calcula su fecha de jubilaion tomando en concideracion hipotetico que los años para la jubilacion para hombre y mujer es de 65 años.
     * @param fechaNacimiento parametro enviado para calular
     * @return retorna la posible feha de jubilacion
     */
    public LocalDate calcularFechaJubilacion(LocalDate fechaNacimiento) {
        return fechaNacimiento.plusYears(65);
    }

    /**
     * Metodo que calula la edad exacta segun su fecha de nacimiento registrada del cliente
     * @param fechaNacimiento parametro enviado para calcular
     * @return
     */
    public int calcularEdadExacta(LocalDate fechaNacimiento) {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

}
