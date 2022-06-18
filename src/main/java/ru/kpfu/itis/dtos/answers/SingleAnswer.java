package ru.kpfu.itis.dtos.answers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SingleAnswer {

    @Getter(onMethod_ = {@JsonProperty("id")})
    @Setter(onMethod_ = {@JsonProperty("id")})
    private Long id;
    @Getter(onMethod_ = {@JsonProperty("answer")})
    @Setter(onMethod_ = {@JsonProperty("answer")})
    private Long answer;
}
