import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import ru.utair.cowsAndBulls.entity.CowAndBallsNumber;
import ru.utair.cowsAndBulls.entity.Game;
import ru.utair.cowsAndBulls.entity.GameMove;
import ru.utair.cowsAndBulls.enums.GameStatus;
import ru.utair.cowsAndBulls.exception.GameException;

import static org.junit.jupiter.api.Assertions.*;

public class GameNumberTest {

    @Test
    public void testPlayStatus(){
        Game game = new CowAndBallsNumber();

        game.start();

        assertEquals(game.getGameInformation().getStatus(), GameStatus.play);
    }

    @Test
    @SneakyThrows
    public void testGameHistoryMove() {
        Game game = new CowAndBallsNumber();
        game.start();

        GameMove gameMove = game.move("0123456789");

        assertEquals(game.getGameInformation().getHistory().get(0), gameMove);
    }

    @Test
    @SneakyThrows
    public void testGameMoveException() {
        Game game = new CowAndBallsNumber();

        GameException exception = assertThrows(GameException.class, () -> {
            GameMove gameMove = game.move("0123456789");
        });

        String expectedMessage = "Игра не в активном режиме";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @SneakyThrows
    public void testGameFinish() {
        Game game = new CowAndBallsNumber();
        game.start();
        game.setHidden("1234");

        GameMove gameMove = game.move("1234");

        assertEquals(game.getGameInformation().getStatus(), GameStatus.finish);
    }
}
