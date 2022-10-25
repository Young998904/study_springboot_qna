package com.ay.study.qna.base;

import com.ay.study.qna.user.SiteUser;
import com.ay.study.qna.user.UserRepository;
import com.ay.study.qna.answer.Answer;
import com.ay.study.qna.answer.AnswerRepository;
import com.ay.study.qna.question.Question;
import com.ay.study.qna.question.QuestionRepository;
import java.time.LocalDateTime;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DevInitData {

    @Bean
    ApplicationRunner init(QuestionRepository questionRepository, AnswerRepository answerRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        return args -> {
            SiteUser siteUser1 = new SiteUser("user1", "user1@test.com", "1234");
            siteUser1.addUser("user1", "user1@test.com", passwordEncoder.encode("1234"));

            SiteUser siteUser2 = new SiteUser("user2", "user2@test.com", "1234");
            siteUser2.addUser("user2", "user2@test.com", passwordEncoder.encode("1234"));

            userRepository.save(siteUser1);
            userRepository.save(siteUser2);

            for (int i=1; i<=100; i++) {
                questionRepository.save(Question.builder()
                    .subject("제목 %d".formatted(i))
                    .content("내용 %d".formatted(i))
                    .createDate(LocalDateTime.now())
                    .author(siteUser1)
                    .build());
            }

            Question q1 = questionRepository.findById(1).get();
            Answer a1 = new Answer();
            a1.setContent("답변 내용입니다.");
            a1.setQuestion(q1);
            a1.setAuthor(siteUser2);
            a1.setCreateDate(LocalDateTime.now());

            answerRepository.save(a1);
        };
    }
}
