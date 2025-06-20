package com.mbev08.consoleui.core.extensions;

@FunctionalInterface
interface PropertyConverter<T> {
    T convert(String str) throws IllegalArgumentException;
}
