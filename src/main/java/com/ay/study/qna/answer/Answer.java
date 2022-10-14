package com.ay.study.qna.answer;

import com.ay.study.qna.question.Question;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @ManyToOne // question_id 를 FK 로 하는 ForeignKey 관계 생성
    // 순환 참조 해결 방법 (2) : 부모 Entity 와 자식 Entity 관계 명시
    @JsonBackReference
    private Question question;
}