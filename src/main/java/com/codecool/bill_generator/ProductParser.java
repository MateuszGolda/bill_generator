package com.codecool.bill_generator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class ProductParser {
    public Map<String, List<AmountAndPrice>> parseProducts(String fileName) throws IOException {
        Map<String, List<AmountAndPrice>> products = new HashMap<>();

        // add each line representation to the map; skip table header
        try (Stream<String> stream = Files.lines(Paths.get(fileName)).skip(1)) {
            stream.forEach(s -> addToMap(s, products));
        }

        return products;
    }

    /**
     * Adds product to the map. If specified key is already associated with
     * a value it adds second value to the list in descending order by amount
     *
     * @param line     line from csv file
     * @param products map representing products details
     */
    private void addToMap(String line, Map<String, List<AmountAndPrice>> products) {
        String[] arr = line.split(",");
        products.merge(arr[0], Collections.singletonList(
                new AmountAndPrice(Integer.parseInt(arr[2]), Integer.parseInt(arr[3].replace(".", "")))),
                (a, b) -> a.get(0).compareTo(b.get(0)) > 0
                        ? Arrays.asList(a.get(0), b.get(0))
                        : Arrays.asList(b.get(0), a.get(0)));
    }
}
