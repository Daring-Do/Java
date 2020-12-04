package ru.utair.cowsAndBulls.entity;

import ru.utair.cowsAndBulls.enums.GameStatus;
import ru.utair.cowsAndBulls.exception.GameException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface Game {
    GameInformation gameInformation = GameInformation
            .builder()
            .status(GameStatus.start)
            .build();

    default GameInformation getGameInformation() {
        return gameInformation;
    }

    default void setHidden(String hidden) {
        getGameInformation().setHidden(hidden);
    }

    default void start() {
        getGameInformation().setChars(initChars());
        getGameInformation().setHidden(getRandomHidden(4));
        getGameInformation().setHistory(new ArrayList<>());
        getGameInformation().setStatus(GameStatus.play);
    };

    List<String> initChars();

    default GameMove move(String answer) throws GameException {
        if (getGameInformation().getStatus().equals(GameStatus.play)) {
            GameMove gameMove = new GameMove(answer, 0, 0);

            List<String> charsAnswer = answer
                    .chars()
                    .mapToObj(c -> String.valueOf((char) c))
                    .collect(Collectors.toList());

            for (int i = 0; i < charsAnswer.size(); i++) {
                if (getGameInformation().getHidden().indexOf(charsAnswer.get(i)) == i) {
                    gameMove.setBulls(gameMove.getBulls() + 1);
                } else {
                    if (getGameInformation().getHidden().contains(charsAnswer.get(i))) {
                        gameMove.setCows(gameMove.getCows() + 1);
                    }
                }
            }

            getGameInformation().getHistory().add(gameMove);

            if (gameMove.getBulls() == getGameInformation().getHidden().length()) {
                getGameInformation().setStatus(GameStatus.finish);
            }
            return gameMove;
        } else {
            throw new GameException("Игра не в активном режиме");
        }
    };

    default String getRandomHidden(int size) {
        List<String> chars = new ArrayList<>(getGameInformation().getChars());

        StringBuilder result = new StringBuilder();
        for (int i = 0; i<size; i++) {
            int random = (int) (Math.random() * chars.size());
            result.append(chars.get(random));
            chars.remove(random);
        }

        return result.toString();
    };
}
