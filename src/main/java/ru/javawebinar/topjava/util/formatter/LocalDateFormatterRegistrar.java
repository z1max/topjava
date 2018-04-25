package ru.javawebinar.topjava.util.formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;

import java.util.List;

public class LocalDateFormatterRegistrar implements FormatterRegistrar {

    @Autowired(required = false)
    @LocalDateFormat
    @LocalTimeFormat
    private List<Formatter<?>> formatters;

    @Override
    public void registerFormatters(FormatterRegistry registry) {
        if (formatters != null){
            for (Formatter<?> formatter : formatters){
                registry.addFormatter(formatter);
            }
        }

    }
}
