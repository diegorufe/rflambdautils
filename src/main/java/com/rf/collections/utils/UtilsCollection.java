package com.rf.collections.utils;

import com.rf.collections.functions.EnumerateConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Clase de utilidades para colecciones
 *
 * @author diego.ruiz
 */
public final class UtilsCollection {

    private static final Logger LOGGER = LoggerFactory.getLogger(UtilsCollection.class);

    private UtilsCollection() {

    }

    /**
     * Método para realizar enumeración de datos. Nos permite obener en el consumidor el indice del registro y el registro en cuestiñón
     * <p>
     * Ejemplo:
     *
     * <p>
     *
     * <pre>
     * {@code
     * List<String> listNames = new ArrayList<>();
     * ...
     * UtilsCollection.enumerate(listNames, (index, name) -> { ... });
     * }
     * </pre>
     *
     * @param data   para realizar la iteración de datos
     * @param action a ejecutar a la hora de consumir el indice y el valor. Requerira {@link EnumerateConsumer} donde el primer valor será el indice del registro y el segundo valor el registro
     * @param <T>    es el tipo de datos de la coleción a interar
     */
    public static <T> void enumerate(final Collection<T> data, final EnumerateConsumer<Integer, T> action) {

        if (data != null) {
            enumerate(data.stream(), action);
        } else if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Los datos a intentar enumerar son null");
        }

    }

    /**
     * Método para realizar enumeración de datos. Nos permite obener en el consumidor el indice del registro y el registro en cuestiñón
     *
     * @param stream para realizar la iteración de datos
     * @param action a ejecutar a la hora de consumir el indice y el valor
     * @param <T>    es el tipo de datos de la coleción a interar
     */
    public static <T> void enumerate(final Stream<T> stream, final EnumerateConsumer<Integer, T> action) {

        if (stream != null) {

            Objects.requireNonNull(action);

            AtomicInteger index = new AtomicInteger();

            stream.forEach(value -> {
                action.accept(index.getAndIncrement(), value);
            });

        } else if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Los datos a intentar enumerar son null");
        }
    }

    /**
     * Método para realizar la acción de foreach comprobando que los datos no son null
     *
     * @param data   para realizar foreach
     * @param action a ejecutar cuando se acepte el consumidor
     * @param <T>    tipo de dato de la colleción de datos
     */
    public static <T> void forEachCheckNull(final Collection<T> data, final Consumer<T> action) {
        if (data != null) {
            forEachCheckNull(data.stream(), action);
        } else if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Los datos a intentar realizar for each son null");
        }
    }

    /**
     * Método para realizar la acción de foreach comprobando que el stream no sea null para realizar la acción
     *
     * @param stream para realizar foreach
     * @param action a ejecutar cuando se acepte el consumidor
     * @param <T>    tipo de dato de la colección
     */
    public static <T> void forEachCheckNull(final Stream<T> stream, final Consumer<T> action) {
        if (stream != null) {
            Objects.requireNonNull(action);
            stream.forEach(action);
        } else if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Los datos a intentar realizar for each son null");
        }
    }
}
