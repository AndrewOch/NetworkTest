package ru.kpfu.itis.dtos.answers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class SingleAnswers {

    @Getter(onMethod_ = {@JsonProperty("answers")})
    @Setter(onMethod_ = {@JsonProperty("answers")})
    private List<SingleAnswer> answers;
}
