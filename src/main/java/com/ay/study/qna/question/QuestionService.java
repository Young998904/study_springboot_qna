package com.ay.study.qna.question;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    public List<Question> getQuestionList() {
        return questionRepository.findAll();
    }
}
