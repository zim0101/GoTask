package com.go_task.database.context;

import java.util.Objects;
import java.util.function.Function;

public interface CriteriaBuilderContextRunner <A, B, R> {

    R apply(A a, B b);

    default <V> CriteriaBuilderContextRunner<A, B, V> andThen(
            Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (A a, B b) -> after.apply(apply(a, b));
    }
}
