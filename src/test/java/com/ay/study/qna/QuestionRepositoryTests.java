package com.ay.study.qna;

import com.ay.study.qna.answer.Answer;
import com.ay.study.qna.answer.AnswerRepository;
import com.ay.study.qna.question.Question;
import com.ay.study.qna.question.QuestionRepository;
import com.ay.study.qna.user.SiteUser;
import com.ay.study.qna.user.UserRepository;
import com.ay.study.qna.user.UserService;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class QuestionRepositoryTests {
// // 기존코드
//    @Autowired
//    private QuestionRepository questionRepository;
//    private static int lastSampleDataId;
//
//    @BeforeEach
//    void beforeEach() {
//        clearData();
//        createSampleData();
//    }
//
//    public static int createSampleData(QuestionRepository questionRepository) {
//        Question q1 = new Question();
//        q1.setSubject("sbb가 무엇인가요?");
//        q1.setContent("sbb에 대해서 알고 싶습니다.");
//        q1.setCreateDate(LocalDateTime.now());
//        questionRepository.save(q1);
//
//        Question q2 = new Question();
//        q2.setSubject("스프링부트 모델 질문입니다.");
//        q2.setContent("id는 자동으로 생성되나요?");
//        q2.setCreateDate(LocalDateTime.now());
//        questionRepository.save(q2);
//
//        return q2.getId();
//    }
//
//    private void createSampleData() {
//        lastSampleDataId = createSampleData(questionRepository);
//    }
//
//    public static void clearData(QuestionRepository questionRepository) {
////        // 외래키 해제 중복 문제 해결 (1) RepositoryUtil 생성
////        questionRepository.disableForeignKeyChecks();
////        questionRepository.truncate();
////        questionRepository.enableForeignKeyChecks();
//        questionRepository.deleteAll();
//        questionRepository.truncateTable();
//    }
//
//    private void clearData() {
//        clearData(questionRepository);
//    }
//
//    @Test
//    void 저장() {
//        Question q1 = new Question();
//        q1.setSubject("sbb가 무엇인가요?");
//        q1.setContent("sbb에 대해서 알고 싶습니다.");
//        q1.setCreateDate(LocalDateTime.now());
//        questionRepository.save(q1);
//
//        Question q2 = new Question();
//        q2.setSubject("스프링부트 모델 질문입니다.");
//        q2.setContent("id는 자동으로 생성되나요?");
//        q2.setCreateDate(LocalDateTime.now());
//        questionRepository.save(q2);
//
//        assertThat(q1.getId()).isEqualTo(lastSampleDataId + 1);
//        assertThat(q2.getId()).isEqualTo(lastSampleDataId + 2);
//    }
//
//    @Test
//    void 삭제() {
//        assertThat(questionRepository.count()).isEqualTo(lastSampleDataId);
//
//        Question q = this.questionRepository.findById(1).get();
//        questionRepository.delete(q);
//
//        assertThat(questionRepository.count()).isEqualTo(lastSampleDataId - 1);
//    }
//
//    @Test
//    void 수정() {
//        Question q = this.questionRepository.findById(1).get();
//        q.setSubject("수정된 제목");
//        questionRepository.save(q);
//
//        q = this.questionRepository.findById(1).get();
//
//        assertThat(q.getSubject()).isEqualTo("수정된 제목");
//    }
//
//    @Test
//    void findAll() {
//        List<Question> all = questionRepository.findAll();
//        assertThat(all.size()).isEqualTo(lastSampleDataId);
//
//        Question q = all.get(0);
//        assertThat(q.getSubject()).isEqualTo("sbb가 무엇인가요?");
//    }
//
//    @Test
//    void findBySubject() {
//        Question q = questionRepository.findBySubject("sbb가 무엇인가요?");
//        assertThat(q.getId()).isEqualTo(1);
//    }
//
//    @Test
//    void findBySubjectAndContent() {
//        Question q = questionRepository.findBySubjectAndContent(
//            "sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
//        assertThat(q.getId()).isEqualTo(1);
//    }
//
//    @Test
//    void findBySubjectLike() {
//        List<Question> qList = questionRepository.findBySubjectLike("sbb%");
//        Question q = qList.get(0);
//
//        assertThat(q.getSubject()).isEqualTo("sbb가 무엇인가요?");
//    }
//
//    @Test
//    @Rollback(false)
//    void 대량의_데이터_생성() {
//        for (int i=3; i<=100; i++) {
//            Question q = new Question();
//            q.setSubject("제목 %d".formatted(i));
//            q.setContent("내용 %d".formatted(i));
//            q.setCreateDate(LocalDateTime.now());
//            questionRepository.save(q);
//        }
//    }

//    // author 추가 후
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private QuestionRepository questionRepository;
//
//    @Autowired
//    private AnswerRepository answerRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    private static long lastSampleDataId;
//
//    @BeforeEach
//    void beforeEach() {
//        clearData();
//        createSampleData();
//    }
//
//    public static long createSampleData(UserService userService, QuestionRepository questionRepository) {
//        UserServiceTests.createSampleData(userService);
//
//        Question q1 = new Question();
//        q1.setSubject("sbb가 무엇인가요?");
//        q1.setContent("sbb에 대해서 알고 싶습니다.");
//        q1.setAuthor(new SiteUser(2L));
//        q1.setCreateDate(LocalDateTime.now());
//        questionRepository.save(q1);
//
//        Question q2 = new Question();
//        q2.setSubject("스프링부트 모델 질문입니다.");
//        q2.setContent("id는 자동으로 생성되나요?");
//        q2.setAuthor(new SiteUser(2L));
//        q2.setCreateDate(LocalDateTime.now());
//        questionRepository.save(q2);
//
//        return q2.getId();
//    }
//
//    private void createSampleData() {
//        lastSampleDataId = createSampleData(userService, questionRepository);
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
//    @Test
//    void 저장() {
//        Question q1 = new Question();
//        q1.setSubject("sbb가 무엇인가요?");
//        q1.setContent("sbb에 대해서 알고 싶습니다.");
//        q1.setAuthor(new SiteUser(2L));
//        q1.setCreateDate(LocalDateTime.now());
//        questionRepository.save(q1);
//
//        Question q2 = new Question();
//        q2.setSubject("스프링부트 모델 질문입니다.");
//        q2.setContent("id는 자동으로 생성되나요?");
//        q2.setAuthor(new SiteUser(2L));
//        q2.setCreateDate(LocalDateTime.now());
//        questionRepository.save(q2);
//
//        assertThat(q1.getId()).isEqualTo(lastSampleDataId + 1);
//        assertThat(q2.getId()).isEqualTo(lastSampleDataId + 2);
//    }
//
//    @Test
//    void 삭제() {
//        assertThat(questionRepository.count()).isEqualTo(lastSampleDataId);
//
//        Question q = this.questionRepository.findById(1).get();
//        questionRepository.delete(q);
//
//        assertThat(questionRepository.count()).isEqualTo(lastSampleDataId - 1);
//    }
//
//    @Test
//    void 수정() {
//        Question q = this.questionRepository.findById(1).get();
//        q.setSubject("수정된 제목");
//        questionRepository.save(q);
//
//        q = this.questionRepository.findById(1).get();
//
//        assertThat(q.getSubject()).isEqualTo("수정된 제목");
//    }
//
//    @Test
//    void findAll() {
//        List<Question> all = questionRepository.findAll();
//        assertThat(all.size()).isEqualTo(lastSampleDataId);
//
//        Question q = all.get(0);
//        assertThat(q.getSubject()).isEqualTo("sbb가 무엇인가요?");
//    }
//
////    @Test
////    void findAllPageable() {
////        // Pageble : 한 페이지에 몇개의 아이템이 나와야 하는지 + 현재 몇 페이지인지)
////        Pageable pageable = PageRequest.of(0, (int) lastSampleDataId);
////        Page<Question> page = questionRepository.findAll(pageable);
////
////        assertThat(page.getTotalPages()).isEqualTo(1);
////    }
//
//    @Test
//    void findBySubject() {
//        Question q = questionRepository.findBySubject("sbb가 무엇인가요?");
//        assertThat(q.getId()).isEqualTo(1);
//    }
//
//    @Test
//    void findBySubjectAndContent() {
//        Question q = questionRepository.findBySubjectAndContent(
//            "sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
//        assertThat(q.getId()).isEqualTo(1);
//    }
//
//    @Test
//    void findBySubjectLike() {
//        List<Question> qList = questionRepository.findBySubjectLike("sbb%");
//        Question q = qList.get(0);
//
//        assertThat(q.getSubject()).isEqualTo("sbb가 무엇인가요?");
//    }
//
//    @Test
//    void createManySampleData() {
//        boolean run = false;
//
//        if (run == false) return;
//
//        IntStream.rangeClosed(3, 300).forEach(id -> {
//            Question q = new Question();
//            q.setSubject("%d번 질문".formatted(id));
//            q.setContent("%d번 질문의 내용".formatted(id));
//            q.setAuthor(new SiteUser(2L));
//            q.setCreateDate(LocalDateTime.now());
//            questionRepository.save(q);
//        });
//    }
}
