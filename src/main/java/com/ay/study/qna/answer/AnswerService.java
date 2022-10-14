package com.ay.study.qna.answer;

import com.ay.study.qna.DataNotFoundException;
import com.ay.study.qna.answer.AnswerDto.addAnswer;
import com.ay.study.qna.question.Question;
import com.ay.study.qna.question.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;
    public void createAnswer(int id, String content) {
        Question question = questionRepository.findById(id).orElse(null);
        if (question == null) {
            throw new DataNotFoundException("question not found");
        }
        addAnswer addAnswer = new addAnswer(content, question);

        System.out.println(addAnswer);
        Answer answer = modelMapper.map(addAnswer, Answer.class);
        System.out.println(answer);

        answerRepository.save(answer);
    }
}
