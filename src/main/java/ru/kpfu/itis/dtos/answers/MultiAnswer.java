package ru.kpfu.itis.dtos.answers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class MultiAnswer {

    @Getter(onMethod_ = {@JsonProperty("id")})
    @Setter(onMethod_ = {@JsonProperty("id")})
    private Long id;
    @Getter(onMethod_ = {@JsonProperty("answers")})
    @Setter(onMethod_ = {@JsonProperty("answers")})
    private List<Long> answers;
}
