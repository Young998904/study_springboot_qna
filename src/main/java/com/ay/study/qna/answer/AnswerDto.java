package com.ay.study.qna.answer;

import com.ay.study.qna.question.Question;
import com.ay.study.qna.user.SiteUser;
import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class AnswerDto {
    @Setter
    @Getter
    @ToString
    public static class AddAnswer {
        @NotEmpty (message = "내용은 필수 사항입니다.")
        private String content;
        private LocalDateTime createDate;
        private Question question;
        private SiteUser author;
        public AddAnswer (String content) {
            this.content = content;
            this.createDate = LocalDateTime.now();
        }
    }
}
