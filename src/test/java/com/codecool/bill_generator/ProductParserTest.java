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
        Map<String, List<AmountAndPrice>> expected = new HashMap<>();

        expected.put("3401", Collections.singletonList(
                new AmountAndPrice(1, 315)));

        expected.put("1243", Arrays.asList(
                new AmountAndPrice(10, 190),
                new AmountAndPrice(1, 20)));

        expected.put("1001", Arrays.asList(
                new AmountAndPrice(2, 200),
                new AmountAndPrice(1, 120)));

        Map<String, List<AmountAndPrice>> actual = productParser.parseProducts("src/test/resources/product_prices.csv");

        assertEquals(expected, actual);
    }
}
