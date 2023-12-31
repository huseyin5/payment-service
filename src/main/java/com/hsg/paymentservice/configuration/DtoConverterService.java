package com.hsg.paymentservice.configuration;

import java.util.List;

public interface DtoConverterService {
    <S, T> List<T> dtoConverter(List<S> s, Class<T> targetClass);

    public <T> T dtoClassConverter(Object source, Class<T> baseClass);
}
