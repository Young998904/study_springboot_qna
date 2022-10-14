package com.ay.study.qna.question;

import com.ay.study.qna.answer.Answer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity // 아래 Question 클래스는 엔티티 클래스이다.
// 아래 클래스와 1:1로 매칭되는 테이블이 DB에 없다면, 자동으로 생성되어야 한다.
public class Question {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Integer id;

    @Column(length = 200) // varchar(200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    // cascade : 영속성 전이
    @OneToMany (mappedBy = "question", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    // 순환 참조 해결 방법 (2) : 부모 Entity 와 자식 Entity 관계 명시
    @JsonManagedReference
//    // 순환참조 해결 방법 (1) : 아예 없는 데이터 처리
//    @JsonIgnore
    private List<Answer> answerList = new ArrayList<>();


    public void addAnswer(Answer answer) {
        answer.setQuestion(this);
        getAnswerList().add(answer);
    }
}