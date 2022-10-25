package com.ay.study.qna.answer;

import static com.ay.study.qna.answer.AnswerDto.*;

import com.ay.study.qna.base.DataNotFoundException;
import com.ay.study.qna.question.Question;
import com.ay.study.qna.question.QuestionRepository;
import com.ay.study.qna.user.SiteUser;
import com.ay.study.qna.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    private final ModelMapper modelMapper;
    public void createAnswer(int id, AddAnswer addAnswer, SiteUser siteUser) {
        Question question = questionRepository.findById(id).orElse(null);
        if (question == null) {
            throw new DataNotFoundException("question not found");
        }

        addAnswer.setAuthor(siteUser);
        Answer answer = modelMapper.map(addAnswer, Answer.class);

        question.addAnswer(answer);

        answerRepository.save(answer);
    }
}
