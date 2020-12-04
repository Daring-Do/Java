package ru.utair.cowsAndBulls.entity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CowAndBallsWord implements Game {

    @Override
    public List<String> initChars() {
        return IntStream
                .rangeClosed('a', 'z')
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.toList());
    }

}
