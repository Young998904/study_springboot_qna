package com.ay.study.qna;

import static org.assertj.core.api.Assertions.assertThat;

import com.ay.study.qna.answer.Answer;
import com.ay.study.qna.answer.AnswerRepository;
import com.ay.study.qna.question.Question;
import com.ay.study.qna.question.QuestionRepository;
import com.ay.study.qna.user.SiteUser;
import com.ay.study.qna.user.UserRepository;
import com.ay.study.qna.user.UserService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class AnswerRepositoryTests {
// // 기존 코드
//    @Autowired
//    private QuestionRepository questionRepository;
//    @Autowired
//    private AnswerRepository answerRepository;
////    @Autowired
////    private EntityManager em;
//    private int lastSampleDataId;
//
//    @BeforeEach
//    void beforeEach() {
//        clearData();
//        createSampleData();
//    }
//
//    private void clearData() {
//        QuestionRepositoryTests.clearData(questionRepository);
//        // truncate 대신 auto_increment 1로 초기화시 delete 메소드를 통해서도 삭제 가능
//        answerRepository.deleteAll();
//        // 외래키 해제 중복 해결 (2) RepositoryUtil 속 interface 의 default 메소드 활용
//        answerRepository.truncateTable();
//    }
//
//    private void createSampleData() {
//        QuestionRepositoryTests.createSampleData(questionRepository);
//
//        Question q = questionRepository.findById(1).get();
//
//        Answer a1 = new Answer();
//        a1.setContent("sbb 는 질문답변 게시판 입니다.");
//        a1.setQuestion(q);
//        a1.setCreateDate(LocalDateTime.now());
//        answerRepository.save(a1);
//
//        // ORM 관점에서 보기
//        q.getAnswerList().add(a1);
//
//        Answer a2 = new Answer();
//        a2.setContent("sbb에서는 주로 스프링 부트 관련 내용을 다룹니다.");
//        a2.setQuestion(q);
//        a2.setCreateDate(LocalDateTime.now());
//        // 코드 개선
//        answerRepository.save(a2);
//
//        q.getAnswerList().add(a2);
////        q.addAnswer(a2);
//
//        questionRepository.save(q);
//    }
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    void 저장_1() {
//        // SELECT * FORM question WHERE id=1
//        Question q = questionRepository.findById(2).get();
//        // 테스트 코드에서는 이 과정에서 DB 통신이 끊김
//
//        System.out.println("첫번째 호출 question 객체 : " + q);
//
//        Answer a = new Answer();
//        a.setContent("네 자동으로 생성됩니다.");
//        a.setQuestion(q);
//        a.setCreateDate(LocalDateTime.now());
//        answerRepository.save(a);
//
//        // 객체 수준의 저장
//        q.getAnswerList().add(a);
//        // 실제 DB 저장
//        questionRepository.save(q);
//
////        em.clear();
//
//        // q 다시 불러옴
//        q  = questionRepository.findById(2).get();
//
//        System.out.println("두번째 호출 question 객체 : " + q);
//
//        List<Answer> answerList = q.getAnswerList();
//        assertThat(answerList.size()).isEqualTo(1);
//    }
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    // CascadeType.ALL 적용
//    void 저장_2() {
//        Question q = questionRepository.findById(2).get();
//
//        Answer a1 = new Answer();
//        a1.setContent("답변1");
//        a1.setCreateDate(LocalDateTime.now());
//        q.addAnswer(a1);
//
//        Answer a2 = new Answer();
//        a2.setContent("답변2");
//        a2.setCreateDate(LocalDateTime.now());
//        q.addAnswer(a2);
//
//        // 해당 메소드 하나로 모든 변 경 내용 저장 되도록
//
////        questionRepository.save(q);
//        answerRepository.save(a1);
//        answerRepository.save(a2);
//
//        assertThat(q.getAnswerList().size()).isEqualTo(2);
//    }
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    void 조회() {
//        Answer a = answerRepository.findById(1).get();
//        assertThat(a.getContent()).isEqualTo("sbb 는 질문답변 게시판 입니다.");
//    }
//    // author 추가 후
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private QuestionRepository questionRepository;
//    @Autowired
//    private AnswerRepository answerRepository;
//    @Autowired
//    private UserRepository userRepository;
//
//    @BeforeEach
//    void beforeEach() {
//        clearData();
//        createSampleData();
//    }
//
//    public static void clearData(UserRepository userRepository, AnswerRepository answerRepository, QuestionRepository questionRepository) {
//        UserServiceTests.clearData(userRepository, answerRepository, questionRepository);
//    }
//
//    private void clearData() {
//        clearData(userRepository, answerRepository, questionRepository);
//    }
//
//    private void createSampleData() {
//        QuestionRepositoryTests.createSampleData(userService, questionRepository);
//
//        // 관련 답변이 하나없는 상태에서 쿼리 발생
//        Question q = questionRepository.findById(1).get();
//
//        Answer a1 = new Answer();
//        a1.setContent("sbb는 질문답변 게시판 입니다.");
//        a1.setAuthor(new SiteUser(1L));
//        a1.setCreateDate(LocalDateTime.now());
//        q.addAnswer(a1);
//
//        Answer a2 = new Answer();
//        a2.setContent("sbb에서는 주로 스프링부트관련 내용을 다룹니다.");
//        a2.setAuthor(new SiteUser(2L));
//        a2.setCreateDate(LocalDateTime.now());
//        q.addAnswer(a2);
//
//        questionRepository.save(q);
//    }
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    void 저장() {
//        Question q = questionRepository.findById(2).get();
//
//        Answer a1 = new Answer();
//        a1.setContent("네 자동으로 생성됩니다.");
//        a1.setAuthor(new SiteUser(2L));
//        a1.setCreateDate(LocalDateTime.now());
//        q.addAnswer(a1);
//
//        Answer a2 = new Answer();
//        a2.setContent("네네~ 맞아요!");
//        a2.setAuthor(new SiteUser(2L));
//        a2.setCreateDate(LocalDateTime.now());
//        q.addAnswer(a2);
//
//        questionRepository.save(q);
//    }
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    void 조회() {
//        Answer a = this.answerRepository.findById(1).get();
//        assertThat(a.getContent()).isEqualTo("sbb는 질문답변 게시판 입니다.");
//    }
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    void 관련된_question_조회() {
//        Answer a = this.answerRepository.findById(1).get();
//        Question q = a.getQuestion();
//
//        assertThat(q.getId()).isEqualTo(1);
//    }
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    void question으로부터_관련된_질문들_조회() {
//        Question q = questionRepository.findById(1).get();
//
//        List<Answer> answerList = q.getAnswerList();
//
//        assertThat(answerList.size()).isEqualTo(2);
//        assertThat(answerList.get(0).getContent()).isEqualTo("sbb는 질문답변 게시판 입니다.");
//    }
}
