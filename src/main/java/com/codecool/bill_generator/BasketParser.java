package com.codecool.bill_generator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class BasketParser {
    public Map<String, Integer> parseBasket(String fileName) throws IOException {

        Map<String, Integer> basket = new HashMap<>();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(s -> basket.merge(s, 1, Integer::sum));
        }
        return basket;
    }
}
