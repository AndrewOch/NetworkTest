package ru.kpfu.itis.dtos.results;

import lombok.Data;

@Data
public class SingleResult {

    private long id;
    private Long answerId;
    private Long rightAnswerId;
    private String rightAnswerText;
}
