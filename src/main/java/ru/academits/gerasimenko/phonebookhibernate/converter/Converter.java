package ru.academits.gerasimenko.phonebookhibernate.converter;

import java.util.List;

public interface Converter<S, D> {
    D convert(S source);

    default List<D> convert(List<S> sources) {
        return sources.stream()
                .map(this::convert)
                .toList();
    }
}