package ru.utair.cowsAndBulls.entity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CowAndBallsNumber implements Game {

    @Override
    public List<String> initChars() {
        return IntStream
                .rangeClosed(0, 9)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());
    }
}
