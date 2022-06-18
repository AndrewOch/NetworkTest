package ru.kpfu.itis.models;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

import java.util.List;

@Data
public class SingleQuestion {

    private Long id;

    private String text;

    private List<SAnswer> answers;

    private Long rightAnswerId;
}

