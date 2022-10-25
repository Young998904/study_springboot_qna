package com.ay.study.qna.user;

import com.ay.study.qna.base.DataNotFoundException;
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

    public SiteUser getUser (String username) {
        SiteUser siteUser = userRepository.findByUsername(username).orElse(null);
        if (siteUser == null) {
            throw new DataNotFoundException("siteuser not found");
        }
        return siteUser;
    }
}
