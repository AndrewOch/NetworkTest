package ru.kpfu.itis.dtos;

import lombok.Data;

import java.util.List;

@Data
public class MultiAnswerDto {

    private Long id;
    private List<Long> answers;
}
