package com.ay.study.qna.answer;

import com.ay.study.qna.question.Question;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class AnswerDto {
    @Setter
    @Getter
    @ToString
    public static class addAnswer {
        private String content;
        private LocalDateTime createDate;
        private Question question;

        public addAnswer (String content, Question question) {
            this.content = content;
            this.question = question;
            this.createDate = LocalDateTime.now();
        }
    }
}
