package com.ay.study.qna.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public SiteUser create (String username, String email, String password) {
        SiteUser siteUser = new SiteUser();
        siteUser.addUser(username, email, passwordEncoder.encode(password));
        userRepository.save(siteUser);
        return siteUser;
    }
}
