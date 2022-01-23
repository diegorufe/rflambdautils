package com.rf.test.collections;

import com.rf.collections.utils.UtilsCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 * Clase test para utilidades de collecciones
 *
 * @author diego.ruiz
 */
public class UtilsCollectionTest {

    private static final Logger LOGGER = Logger.getLogger(UtilsCollectionTest.class.getSimpleName());

    /**
     * Método para realizar test de la función enumerate de lambda
     */
    @Test
    public void testEnumerate() {

        List<Integer> listValues = new ArrayList<>();

        for (int i = 3; i > 0; i--) {
            listValues.add(i);
        }

        AtomicInteger valueIndex = new AtomicInteger();
        AtomicInteger valueList = new AtomicInteger(listValues.size());

        UtilsCollection.enumerate(listValues, (index, value) -> {
            LOGGER.info("Indice: " + index + ", valor: " + value);

            Assertions.assertEquals(valueIndex.getAndIncrement(), (int) index);
            Assertions.assertEquals(valueList.getAndDecrement(), (int) value);
        });
    }

    /**
     * étodo para realizar test de foreach chequeando que sea null antes de realizar el foreach
     */
    @Test
    public void testForeachCheckNull() {
        List<String> listValues = List.of("One", "Two", "Three");

        UtilsCollection.forEachCheckNull(listValues, (value) -> {
            LOGGER.info("Valor: " + value);
        });

        listValues = null;

        // Por aquí no deberia entrar nunan
        UtilsCollection.forEachCheckNull(listValues, (value) -> {
            LOGGER.info("Valor que no se debería ver nunca: " + value);
        });
    }
}
