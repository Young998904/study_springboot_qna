package com.ay.study.qna.user;

import lombok.Getter;

@Getter
// static 이므로 Setter 은 필요 없음
public enum UserRole {
    // class 와 동일 but 객체를 싱글톤으로 쓰고 싶거나, 객체의 개수가 정해져 있을 때 사용
    // 오타로 인한 오류를 줄일 수 있다. ex) Usser, Uesr 등
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER"); // private static UserRole USER = new UserRole("ROLE_USER"); 과 동일


    UserRole (String value) {
        this.value = value;
    }

    private String value;
}
