package ru.kpfu.itis.models;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class TFQuestion {

    private Long id;

    @CsvBindByPosition(position = 1)
    private String text;

    @CsvBindByPosition(position = 2)
    private Boolean rightAnswer;
}
