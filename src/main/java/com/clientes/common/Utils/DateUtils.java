package com.clientes.common.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Logger;

public class DateUtils {
    private static final Logger LOG = Logger.getLogger(DateUtils.class.getName());

    private DateUtils() {
    }
    public static final String STANDAR_RFC3339 = "yyyy-MM-dd'T'HH:mm:ssXXX";
    public static final String DDMMYYY_HHMMSS = "dd/MM/yyyy HH:mm:ss";
    public static final String DDMMYYYY = "dd/MM/yyyy";
    public static final String MMYYY = "MM/yyyy";
    public static final String YYYYMMDD = "YYYY-MM-dd";
    public static final String HHMMSS="HH:mm:ss";
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public static Date date(String f, String p) {
        Date date = null;
        if (Objects.isNull(f) || Objects.isNull(p)) {
            return date;
        }
        try {
            date = new SimpleDateFormat(p).parse(f);
        } catch (ParseException e) {
            LOG.severe(e.getMessage());
        }
        return date;
    }
    public static LocalDate LocalDate(String f) {
        LocalDate date = null;
        if (Objects.isNull(f)) {
            return date;
        }
        date = LocalDate.parse(f, formatter);
        return date;
    }

    public static String date(Date f, String p) {
        if (f == null || p == null) {
            return "";
        }
        return new SimpleDateFormat(p).format(f);
    }

    public static Date first(String f) {
        if (Objects.isNull(f)) {
            return null;
        }
        return DateUtils.date(String.format("%s 00:00:00", f), DateUtils.DDMMYYY_HHMMSS);
    }

    public static Date last(String f) {
        if (Objects.isNull(f)) {
            return null;
        }
        return DateUtils.date(String.format("%s 23:59:59", f), DateUtils.DDMMYYY_HHMMSS);
    }

    public static Date first(Date f) {
        if (Objects.isNull(f)) {
            return null;
        }
        return DateUtils.date(String.format("%s 00:00:00", DateUtils.date(f, DateUtils.DDMMYYYY)), DateUtils.DDMMYYY_HHMMSS);
    }

    public static Date last(Date f) {
        if (Objects.isNull(f)) {
            return null;
        }
        return DateUtils.date(String.format("%s 23:59:59", DateUtils.date(f, DateUtils.DDMMYYYY)), DateUtils.DDMMYYY_HHMMSS);
    }

    public static Date addHour(Date f, Integer h) {
        if (Objects.isNull(f)) {
            return null;
        }
        if (Objects.isNull(h)) {
            h = Integer.parseInt("0");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(f);
        calendar.add(Calendar.HOUR, h);
        return calendar.getTime();
    }

    public static Date convertFromLocalDateTime(LocalDateTime dateToConvert) {
        return Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }
}
