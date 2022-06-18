package ru.kpfu.itis.models;

import lombok.Data;

import java.util.List;

@Data
public class MultiQuestion {

    private Long id;
    private String text;
    private List<MAnswer> answers;
    private List<Long> rightAnswerIds;
}
