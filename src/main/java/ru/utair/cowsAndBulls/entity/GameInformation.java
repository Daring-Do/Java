package ru.utair.cowsAndBulls.entity;

import lombok.*;
import ru.utair.cowsAndBulls.enums.GameStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameInformation {
    @Getter(value = AccessLevel.PROTECTED)
    @Setter(value = AccessLevel.PROTECTED)
    private String hidden;
    private List<GameMove> history;
    private List<String> chars;
    private GameStatus status;
}
