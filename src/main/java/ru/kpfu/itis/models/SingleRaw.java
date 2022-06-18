package ru.kpfu.itis.models;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class SingleRaw {

    @CsvBindByPosition(position = 1)
    private String text;

    @CsvBindByPosition(position = 2)
    private String answer1;

    @CsvBindByPosition(position = 3)
    private String answer2;

    @CsvBindByPosition(position = 4)
    private String answer3;

    @CsvBindByPosition(position = 5)
    private String answer4;

    @CsvBindByPosition(position = 6)
    private String answer5;

    @CsvBindByPosition(position = 7)
    private Long rightAnswerId;
}
