package com.ay.study.qna.base;

import com.ay.study.qna.answer.Answer;
import com.ay.study.qna.answer.AnswerRepository;
import com.ay.study.qna.question.Question;
import com.ay.study.qna.question.QuestionRepository;
import java.time.LocalDateTime;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DevInitData {

    @Bean
    ApplicationRunner init(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        return args -> {
            for (int i=1; i<=100; i++) {
                questionRepository.save(Question.builder()
                    .subject("제목 %d".formatted(i))
                    .content("내용 %d".formatted(i))
                    .createDate(LocalDateTime.now())
                    .build());
            }

            Question q1 = questionRepository.findById(1).get();
            Answer a1 = new Answer();
            a1.setContent("답변 내용입니다.");
            a1.setQuestion(q1);
            a1.setCreateDate(LocalDateTime.now());

            answerRepository.save(a1);
        };
    }
}
