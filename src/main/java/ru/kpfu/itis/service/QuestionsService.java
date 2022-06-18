package ru.kpfu.itis.service;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.models.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class QuestionsService {

    public List<TFQuestion> getTFQuestions() {
        String fileName = "/Users/mac/Desktop/Networks/src/main/resources/HuaweiTrueFalse.csv";
        List<TFQuestion> beans = null;
        List<TFQuestion> randomised = new ArrayList<>();
        try {
            beans = new CsvToBeanBuilder(new FileReader(fileName))
                    .withSeparator(';')
                    .withType(TFQuestion.class)
                    .build()
                    .parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return randomised;
        }
        Collections.shuffle(beans);
        for (int i = 0; i < 15; i++) {
            TFQuestion question = beans.get(i);
            question.setId((long) i);
            randomised.add(question);
        }
        return randomised;
    }

    public List<SingleQuestion> getSingleQuestions(){

        String fileName = "/Users/mac/Desktop/Networks/src/main/resources/HuaweiSingle.csv";
        List<SingleRaw> beans = null;
        List<SingleQuestion> randomised = new ArrayList<>();
        try {
            beans = new CsvToBeanBuilder(new FileReader(fileName))
                    .withSeparator(';')
                    .withType(SingleRaw.class)
                    .build()
                    .parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return randomised;
        }
        List<SingleQuestion> refactored = new ArrayList<>();
        for (SingleRaw raw: beans) {
            SingleQuestion singleQuestion = new SingleQuestion();
            List<SAnswer> strings = new ArrayList<>();
            strings.add(new SAnswer(0L, raw.getAnswer1()));
            strings.add(new SAnswer(1L, raw.getAnswer2()));
            strings.add(new SAnswer(2L, raw.getAnswer3()));
            strings.add(new SAnswer(3L, raw.getAnswer4()));

            if (!raw.getAnswer5().equals("")){
                strings.add(new SAnswer(4L, raw.getAnswer5()));
            }
            singleQuestion.setAnswers(strings);
            singleQuestion.setText(raw.getText());
            singleQuestion.setRightAnswerId(raw.getRightAnswerId());
            refactored.add(singleQuestion);
        }
        Collections.shuffle(refactored);
        for (int i = 0; i < 15; i++) {
            SingleQuestion question = refactored.get(i);
            question.setId((long) i);
            randomised.add(question);
        }
        return randomised;
    }

    public List<MultiQuestion> getMultiQuestions(){

        String fileName = "/Users/mac/Desktop/Networks/src/main/resources/HuaweiMultiple.csv";
        List<MultiRaw> beans = null;
        List<MultiQuestion> randomised = new ArrayList<>();
        try {
            beans = new CsvToBeanBuilder(new FileReader(fileName))
                    .withSeparator(';')
                    .withType(MultiRaw.class)
                    .build()
                    .parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return randomised;
        }
        List<MultiQuestion> refactored = new ArrayList<>();
        for (MultiRaw raw: beans) {
            MultiQuestion question = new MultiQuestion();
            List<MAnswer> mAnswers = new ArrayList<>();
            mAnswers.add(new MAnswer(0L, raw.getAnswer1()));
            mAnswers.add(new MAnswer(1L, raw.getAnswer2()));
            mAnswers.add(new MAnswer(2L, raw.getAnswer3()));
            mAnswers.add(new MAnswer(3L, raw.getAnswer4()));

            if (!raw.getAnswer5().equals("")){
                mAnswers.add(new MAnswer(4L, raw.getAnswer5()));
            }
            question.setAnswers(mAnswers);
            question.setText(raw.getText());

            List<Long> rights = new ArrayList<>();

            if (raw.getRightAnswer1() != null) rights.add(0L);
            if (raw.getRightAnswer2() != null) rights.add(1L);
            if (raw.getRightAnswer3() != null) rights.add(2L);
            if (raw.getRightAnswer4() != null) rights.add(3L);
            if (raw.getRightAnswer5() != null) rights.add(4L);

            question.setRightAnswerIds(rights);
            refactored.add(question);
        }
        Collections.shuffle(refactored);
        for (int i = 0; i < 22; i++) {
            MultiQuestion question = refactored.get(i);
            question.setId((long) i);
            randomised.add(question);
        }
        return randomised;
    }
}
