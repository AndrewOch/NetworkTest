package ru.kpfu.itis.models;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class MultiRaw {

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
    private Long rightAnswer1;

    @CsvBindByPosition(position = 8)
    private Long rightAnswer2;

    @CsvBindByPosition(position = 9)
    private Long rightAnswer3;

    @CsvBindByPosition(position = 10)
    private Long rightAnswer4;

    @CsvBindByPosition(position = 11)
    private Long rightAnswer5;
}
