package ru.utair.cowsAndBulls;

import ru.utair.cowsAndBulls.entity.CowAndBallsNumber;
import ru.utair.cowsAndBulls.entity.CowAndBallsWord;
import ru.utair.cowsAndBulls.entity.Game;
import ru.utair.cowsAndBulls.entity.GameMove;
import ru.utair.cowsAndBulls.enums.GameStatus;
import ru.utair.cowsAndBulls.exception.GameException;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws GameException {
        System.out.println("1. Игра на цифрах");
        System.out.println("2. Игра на буквах(Очень сложный уровнень)");

        Scanner in = new Scanner(System.in);
        System.out.print("Выберите игру: ");
        int num = in.nextInt();

        Game game;
        switch (num) {
            case 2:
                game = new CowAndBallsWord();
                break;
            default:
                game = new CowAndBallsNumber();
                break;
        }
        game.start();

        while (!game.getGameInformation().getStatus().equals(GameStatus.finish)) {
            System.out.print("Ваш ход: ");
            String answer = in.next();
            GameMove gameMove = game.move(answer);
            System.out.println(String.format("Найдено %d коров и %d быков", gameMove.getCows(), gameMove.getBulls()));
        }

        if (game.getGameInformation().getStatus().equals(GameStatus.finish)) {
            System.out.println("Поздравляем с победой! Количество попыток: " + game.getGameInformation().getHistory().size());
        }
    }
}
