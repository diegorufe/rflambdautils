package com.rf.collections.functions;


/**
 * Consumidor para realizar enumerate (Python) en java
 *
 * @param <AtomicInteger> es el indice de la posición del valor
 * @param <T>             es el valor en la posición de lista o array
 */
@FunctionalInterface
public interface EnumerateConsumer<AtomicInteger, T> {

    void accept(Integer index, T value);

}
