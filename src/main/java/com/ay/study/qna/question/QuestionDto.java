package com.ay.study.qna.question;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class QuestionDto {
    @Getter
    @Setter
    @Builder
    public static class QuestionInfo {
        private String subject;
        private LocalDateTime createDate;

        public static QuestionInfo fromEntity(Question question) {
            return QuestionInfo.builder()
                .subject(question.getSubject())
                .createDate(question.getCreateDate())
                .build();
        }
    }
}