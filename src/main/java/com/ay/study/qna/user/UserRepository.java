package com.ay.study.qna.user;

import com.ay.study.qna.base.RepositoryUtil;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, Long>, RepositoryUtil {

    Optional<SiteUser> findByUsername(String username);

    default void truncateTable() {
        // alter table 로 auto_increment 수정시 외래키 해제 필요 없음
//        disableForeignKeyChecks();
        truncate(); // 실행 시 AnswerRepository 와 QuestionRepository 에 있는 truncate() 가 실행됨
//        enableForeignKeyChecks();
    }
}
