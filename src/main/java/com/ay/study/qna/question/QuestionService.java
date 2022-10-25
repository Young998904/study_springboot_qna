package com.ay.study.qna.question;

import com.ay.study.qna.base.DataNotFoundException;
import com.ay.study.qna.question.QuestionDto.QuestionDetail;
import com.ay.study.qna.question.QuestionDto.QuestionInfo;
import com.ay.study.qna.user.SiteUser;
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

    public void createQuestion(String subject, String content, SiteUser siteUser) {
        Question question = new Question(subject, content, siteUser);

        questionRepository.save(question);
    }

    public void vote(Integer id, SiteUser siteUser) {
        Question q = questionRepository.findById(id).orElse(null);

        if (q == null) {
            throw new DataNotFoundException("question not found");
        }

        q.getVoter().add(siteUser);
        questionRepository.save(q);
    }
}
