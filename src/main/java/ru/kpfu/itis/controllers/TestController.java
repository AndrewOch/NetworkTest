package ru.kpfu.itis.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.dtos.answers.*;
import ru.kpfu.itis.dtos.results.*;
import ru.kpfu.itis.models.MAnswer;
import ru.kpfu.itis.models.MultiQuestion;
import ru.kpfu.itis.models.SingleQuestion;
import ru.kpfu.itis.models.TFQuestion;
import ru.kpfu.itis.service.QuestionsService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    private QuestionsService questionsService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<TFQuestion> tfQuestions;
    private List<SingleQuestion> singleQuestions;
    private List<MultiQuestion> multiQuestions;


    @GetMapping("/test/tf")
    public ModelAndView tfPage() {
        ModelAndView modelAndView = new ModelAndView("tf_test");
        tfQuestions = questionsService.getTFQuestions();
        modelAndView.addObject("questions", tfQuestions);
        return modelAndView;
    }

    @PostMapping("/test/tf")
    public void tfResult(String answers,  HttpServletResponse response) throws IOException {
        TFAnswers tfAnswers = objectMapper.readValue(answers, TFAnswers.class);

        TFResults results = new TFResults();
        results.setResults(new ArrayList<>());
        for (TFAnswer answer: tfAnswers.getAnswers()) {
            TFResult tfResult = new TFResult();
            tfResult.setId(answer.getId());
            tfResult.setAnswer(answer.getAnswer());
            tfResult.setRightAnswer(tfQuestions.get((int) answer.getId()).getRightAnswer());
            results.getResults().add(tfResult);
        }

        String json = objectMapper.writeValueAsString(results);
        System.out.println(json);
        response.setContentType("application/json");
        response.getWriter().println(json);
    }

    @GetMapping("/test/single")
    public ModelAndView singlePage() {
        ModelAndView modelAndView = new ModelAndView("single_test");
        singleQuestions = questionsService.getSingleQuestions();
        modelAndView.addObject("questions", singleQuestions);
        return modelAndView;
    }

    @PostMapping("/test/single")
    public void singleResult(String answers, HttpServletResponse response) throws IOException {
        SingleAnswers singleAnswers = objectMapper.readValue(answers, SingleAnswers.class);

        SingleResults results = new SingleResults();
        results.setResults(new ArrayList<>());
        for (SingleAnswer answer: singleAnswers.getAnswers()) {
            SingleResult result = new SingleResult();
            result.setId(answer.getId());
            result.setAnswerId(answer.getAnswer());
            SingleQuestion sq = singleQuestions.get(Math.toIntExact(answer.getId()));
            result.setRightAnswerId(sq.getRightAnswerId());
            result.setRightAnswerText(sq.getAnswers().get(Math.toIntExact(sq.getRightAnswerId())).getText());
            results.getResults().add(result);
        }

        String json = objectMapper.writeValueAsString(results);
        System.out.println(json);
        response.setContentType("application/json");
        response.getWriter().println(json);
    }

    @GetMapping("/test/multi")
    public ModelAndView multiPage() {
        ModelAndView modelAndView = new ModelAndView("multi_test");
        multiQuestions = questionsService.getMultiQuestions();
        modelAndView.addObject("questions", multiQuestions);
        return modelAndView;
    }

    @PostMapping("/test/multi")
    public void multiResult(String answers, HttpServletResponse response) throws IOException {
        MultiAnswers singleAnswers = objectMapper.readValue(answers, MultiAnswers.class);

        MultiResults results = new MultiResults();
        results.setResults(new ArrayList<>());
        for (MultiAnswer answer: singleAnswers.getAnswers()) {
            MultiResult result = new MultiResult();
            result.setId(answer.getId());
            result.setAnswerIds(answer.getAnswers());
            MultiQuestion sq = multiQuestions.get(Math.toIntExact(answer.getId()));
            result.setRightAnswerIds(sq.getRightAnswerIds());
            List<String> texts = new ArrayList<>();
            for (MAnswer ans: sq.getAnswers()) {
                if (sq.getRightAnswerIds().contains(ans.getId())){
                    texts.add(ans.getText());
                }
            }
            result.setRightAnswerTexts(texts);
            results.getResults().add(result);
        }

        String json = objectMapper.writeValueAsString(results);
        System.out.println(json);
        response.setContentType("application/json");
        response.getWriter().println(json);
    }
}
