package com.ay.study.qna;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findBySubject(String subject);

    @Transactional
    @Modifying // @Query 에 의해 작성된 데이터 변경이 일어나는 삽입, 수정, 삭제 쿼리 메소드를 사용할 때 필요
    @Query(value = "truncate question", nativeQuery = true)
    void truncate();
}
