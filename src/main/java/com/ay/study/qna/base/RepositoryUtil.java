package com.ay.study.qna.base;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface RepositoryUtil {
    @Transactional
    @Modifying
    @Query(value = "SET FOREIGN_KEY_CHECKS = 0", nativeQuery = true)
    void disableForeignKeyChecks();

    @Transactional
    @Modifying
    @Query(value = "SET FOREIGN_KEY_CHECKS = 1", nativeQuery = true)
    void enableForeignKeyChecks();

    default void truncateTable() {
        // alter table 로 auto_increment 수정시 외래키 해제 필요 없음
//        disableForeignKeyChecks();
        truncate(); // 실행 시 AnswerRepository 와 QuestionRepository 에 있는 truncate() 가 실행됨
//        enableForeignKeyChecks();
    }

    void truncate();
}
