package com.bank.manage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsDTO {

    public StatisticsDTO(int year, int score) {
        this.year = year;
        this.score = score;
    }

    private int year;
    private int quarter;
    private int score;
}
