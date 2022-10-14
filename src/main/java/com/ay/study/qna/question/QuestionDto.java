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
        private Integer id;

        public static QuestionInfo fromEntity(Question question) {
            return QuestionInfo.builder()
                .id(question.getId())
                .subject(question.getSubject())
                .createDate(question.getCreateDate())
                .build();
        }
    }

    @Getter
    @Setter
    @Builder
    public static class QuestionDetail {
        private Integer id;
        private String subject;
        private String content;
        private LocalDateTime createDate;
        public static QuestionDetail fromEntity(Question question) {
            return QuestionDetail.builder()
                .id(question.getId())
                .subject(question.getSubject())
                .content(question.getContent())
                .createDate(question.getCreateDate())
                .build();
        }
    }
}