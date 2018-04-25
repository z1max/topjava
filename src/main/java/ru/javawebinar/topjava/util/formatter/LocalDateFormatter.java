package ru.javawebinar.topjava.util.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateFormatter implements Formatter<LocalDate> {
    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        if (text == null) {
            return null;
        }
        return LocalDate.parse(text, getFormatter(locale));
    }

    @Override
    public String print(LocalDate object, Locale locale) {
        if (object == null) {
            return null;
        }
        return getFormatter(locale).format(object);
    }

    private DateTimeFormatter getFormatter(Locale locale){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd", locale);
    }
}
