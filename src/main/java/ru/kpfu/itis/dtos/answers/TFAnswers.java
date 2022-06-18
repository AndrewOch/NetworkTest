
package ru.kpfu.itis.dtos.answers;

import com.fasterxml.jackson.annotation.JsonProperty;

// TFAnswers.java

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class TFAnswers {
    @Getter(onMethod_ = {@JsonProperty("answers")})
    @Setter(onMethod_ = {@JsonProperty("answers")})
    private List<TFAnswer> answers;
}

