package com.knu.it;

@FunctionalInterface
public interface Function2<T, U, R> {
    public R apply(T t, U u);
}
