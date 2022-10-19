package com.ay.study.qna.question;

import com.ay.study.qna.answer.Answer;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotEmpty;
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
        private List<Answer> answerList;
        public static QuestionDetail fromEntity(Question question) {
            return QuestionDetail.builder()
                .id(question.getId())
                .subject(question.getSubject())
                .content(question.getContent())
                .createDate(question.getCreateDate())
                .answerList(question.getAnswerList())
                .build();
        }
    }

    @Getter
    @Setter
    public static class RequestQuestionForm {
        @NotEmpty(message = "제목은 필수 사항 입니다.") // 제약 조건 (1)
        private String subject;

        @NotEmpty(message = "내용은 필수 사항 입니다.") // 제약 조건 (2)
        private String content;
    }
}