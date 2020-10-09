package com.codecool.bill_generator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductParserTest {
    ProductParser productParser;

    @BeforeEach
    void init() {
        productParser = new ProductParser();
    }

    @Test
    void should_populateMap_forSampleCsvFile() throws IOException {
        Map<String, Collection<AmountAndPrice>> expected = new HashMap<>();

        expected.put("3401", new TreeSet<>(Collections.singletonList(
                new AmountAndPrice(1, 315))));

        expected.put("1243", new TreeSet<>(Arrays.asList(
                new AmountAndPrice(10, 190),
                new AmountAndPrice(1, 20))));

        expected.put("1001", new TreeSet<>(Arrays.asList(
                new AmountAndPrice(2, 200),
                new AmountAndPrice(1, 120))));

        Map<String, Collection<AmountAndPrice>> actual = productParser.parseProducts("src/test/resources/product_prices.csv");

        assertEquals(expected, actual);
    }
}
