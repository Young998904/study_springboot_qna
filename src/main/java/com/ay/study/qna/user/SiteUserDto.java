package com.ay.study.qna.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

public class SiteUserDto {
    @Getter
    @Setter
    public static class UserCreateForm {
        @Size(min = 3, max = 25)
        @NotEmpty(message = "사용자ID는 필수항목입니다.")
        private String username;

        @NotEmpty(message = "비밀번호는 필수항목입니다.")
        private String password1;

        @NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
        private String password2;

        @NotEmpty(message = "이메일은 필수항목입니다.")
        @Email // 이메일 형식 확인
        private String email;
    }
}
