package com.ay.study.qna;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class AnswerRepositoryTests {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
//    @Autowired
//    private EntityManager em;
    private int lastSampleDataId;

    @BeforeEach
    void beforeEach() {
        clearData();
        createSampleData();
    }

    private void clearData() {
        QuestionRepositoryTests.clearData(questionRepository);
        // truncate 대신 auto_increment 1로 초기화시 delete 메소드를 통해서도 삭제 가능
        answerRepository.deleteAll();
        // 외래키 해제 중복 해결 (2) RepositoryUtil 속 interface 의 default 메소드 활용
        answerRepository.truncateTable();
    }

    private void createSampleData() {
        QuestionRepositoryTests.createSampleData(questionRepository);

        Question q = questionRepository.findById(1).get();

        Answer a1 = new Answer();
        a1.setContent("sbb 는 질문답변 게시판 입니다.");
        a1.setQuestion(q);
        a1.setCreateDate(LocalDateTime.now());
        answerRepository.save(a1);

        // ORM 관점에서 보기
        q.getAnswerList().add(a1);

        Answer a2 = new Answer();
        a2.setContent("sbb에서는 주로 스프링 부트 관련 내용을 다룹니다.");
        a2.setQuestion(q);
        a2.setCreateDate(LocalDateTime.now());
        // 코드 개선
//        answerRepository.save(a2);
//
//        q.getAnswerList().add(a2);
        q.addAnswer(a2);

        questionRepository.save(q);
    }

    @Test
    @Transactional
    @Rollback(false)
    void 저장() {
        // SELECT * FORM question WHERE id=1
        Question q = questionRepository.findById(2).get();
        // 테스트 코드에서는 이 과정에서 DB 통신이 끊김

        System.out.println("첫번째 호출 question 객체 : " + q);

        Answer a = new Answer();
        a.setContent("네 자동으로 생성됩니다.");
        a.setQuestion(q);
        a.setCreateDate(LocalDateTime.now());
        answerRepository.save(a);

        q.getAnswerList().add(a);
        questionRepository.save(q);

//        em.clear();

        // q 다시 불러옴
        q  = questionRepository.findById(2).get();

        System.out.println("두번째 호출 question 객체 : " + q);

        List<Answer> answerList = q.getAnswerList();
        assertThat(answerList.size()).isEqualTo(1);
    }

    @Test
    @Transactional
    @Rollback(false)
    void 조회() {
        Answer a = answerRepository.findById(1).get();
        assertThat(a.getContent()).isEqualTo("sbb 는 질문답변 게시판 입니다.");
    }
}
