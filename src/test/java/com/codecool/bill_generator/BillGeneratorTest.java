package com.codecool.bill_generator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BillGeneratorTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void should_printValidPriceForSampleBasket() {
        BillGenerator.main(new String[]{"src/test/java/com/codecool/bill_generator/product_prices.csv", "src/test/java/com/codecool/bill_generator/sample_basket.txt"});
        assertEquals("The total price is: 17.00 EUR\n", outContent.toString());
    }

    @Test
    void should_printValidPriceForEmptyBasket() {
        BillGenerator.main(new String[]{"src/test/java/com/codecool/bill_generator/product_prices.csv", "src/test/java/com/codecool/bill_generator/empty_basket.txt"});
        assertEquals("The total price is: 0.00 EUR\n", outContent.toString());
    }

    @Test
    void should_printValidPriceForOtherBasket() {
        BillGenerator.main(new String[]{"src/test/java/com/codecool/bill_generator/product_prices.csv", "src/test/java/com/codecool/bill_generator/other_basket.txt"});
        assertEquals("The total price is: 18.30 EUR\n", outContent.toString());
    }
}
