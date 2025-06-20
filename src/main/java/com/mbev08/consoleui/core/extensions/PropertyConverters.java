package com.mbev08.consoleui.core.extensions;

import java.util.HashMap;
import java.util.Map;

public class PropertyConverters {
    private static final Map<Class<?>, PropertyConverter<?>> converters = new HashMap<>();

    static {
        converters.put(Boolean.class, str -> Boolean.parseBoolean(str));
        converters.put(Integer.class, str -> Integer.parseInt(str));
        converters.put(String.class, str -> str);
    }

    public static <T> T convert(String value, Class<T> type) {
        PropertyConverter<T> converter = (PropertyConverter<T>) converters.get(type);

        if (converter == null) {
            throw new IllegalArgumentException("Converter for " + type + " was not found!");
        }

        return converter.convert(value);
    }
}
