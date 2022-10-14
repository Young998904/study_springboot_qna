package com.ay.study.qna.question;

import com.ay.study.qna.question.QuestionDto.QuestionDetail;
import com.ay.study.qna.question.QuestionDto.QuestionInfo;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
//    public List<Question> getQuestionList() {
//        return questionRepository.findAll();
//    }

    public List<QuestionInfo> getQuestionDtoList() {
        return questionRepository.findAll().stream()
            .map(QuestionInfo::fromEntity).collect(Collectors.toList());
    }

    public QuestionDetail getQuestionDetail(int id) {
        return questionRepository.findById(id)
            .map(QuestionDetail::fromEntity).orElse(null);
    }
}
