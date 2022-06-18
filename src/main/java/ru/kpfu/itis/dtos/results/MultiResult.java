package ru.kpfu.itis.dtos.results;

import lombok.Data;

import java.util.List;

@Data
public class MultiResult {

    private long id;
    private List<Long> answerIds;
    private List<Long> rightAnswerIds;
    private List<String> rightAnswerTexts;
}
