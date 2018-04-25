package ru.javawebinar.topjava.util.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalTimeFormatter implements Formatter<LocalTime> {
    @Override
    public LocalTime parse(String text, Locale locale) throws ParseException {
        if (text == null) {
            return null;
        }
        return LocalTime.parse(text, getFormatter(locale));
    }

    @Override
    public String print(LocalTime object, Locale locale) {
        if (object == null) {
            return null;
        }
        return getFormatter(locale).format(object);
    }

    private DateTimeFormatter getFormatter(Locale locale){
        return DateTimeFormatter.ofPattern("HH:mm", locale);
    }
}
