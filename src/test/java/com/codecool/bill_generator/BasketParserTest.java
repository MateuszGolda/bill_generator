package com.codecool.bill_generator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BasketParserTest {
    BasketParser basketParser;

    @BeforeEach
    void init() {
        basketParser = new BasketParser();
    }

    @Test
    void should_populateMapForSampleBasket() throws IOException {
        Map<String, Integer> expected = new HashMap<>();
        expected.put("1001", 4);
        expected.put("3401", 4);
        expected.put("1243", 2);

        Map<String, Integer> actual = basketParser.parseBasket("src/test/java/com/codecool/bill_generator/sample_basket.txt");

        assertEquals(expected, actual);
    }

    @Test
    void should_returnEmptyMapForEmptyBasket() throws IOException {
        Map<String, Integer> actual = basketParser.parseBasket("src/test/java/com/codecool/bill_generator/empty_basket.txt");

        assertTrue(actual.isEmpty());
    }
}
