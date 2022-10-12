package com.ay.study.qna;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SbbApplicationTests {
    @Autowired
    private QuestionRepository questionRepository;
    private static int lastSampleDataId = 0;
    @BeforeEach
    void 실행전_초기화() {
        clearData();
        createData();
    }
    private void clearData() {
        questionRepository.disableForeignKeyChecks();
        questionRepository.truncate();
        questionRepository.enableForeignKeyChecks();
    }
    private void createData() {
        Question question1 = new Question();
        question1.setSubject("제목1");
        question1.setContent("내용1");
        question1.setCreateDate(LocalDateTime.now());
        questionRepository.save(question1);

        Question question2 = new Question();
        question2.setSubject("제목2");
        question2.setContent("내용2");
        question2.setCreateDate(LocalDateTime.now());
        questionRepository.save(question2);
    }
    @Test
    void 질문_저장 () {
        Question question3 = new Question();
        question3.setSubject("제목3");
        question3.setContent("내용3");
        question3.setCreateDate(LocalDateTime.now());
        questionRepository.save(question3);

        // 방법 (1)
        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList.size()).isEqualTo(3);

        // 방법 (2)
        assertThat(question3.getId()).isEqualTo(lastSampleDataId + 3);
    }

    @Test
    void 질문_삭제() {
        Question q = questionRepository.findById(1).orElse(null);

        if (q == null) {
            throw new RuntimeException();
        }

        questionRepository.delete(q);

        List<Question> questionList = questionRepository.findAll();

        assertThat(questionList.size()).isEqualTo(1);
    }
}
