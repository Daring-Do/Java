package ru.utair.cowsAndBulls.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameMove {
    private String answer;
    private Integer cows;
    private Integer bulls;
}
