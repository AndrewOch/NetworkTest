package ru.kpfu.itis.dtos.results;

import lombok.Data;

import java.util.List;

@Data
public class MultiResults {

    List<MultiResult> results;
}
