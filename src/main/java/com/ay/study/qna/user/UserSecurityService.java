package com.ay.study.qna.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // (1) DB 확인
        Optional<SiteUser> _siteUser = userRepository.findByUsername(username);

        if (_siteUser.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        SiteUser siteUSer =  _siteUser.get();

        // (2) 권한 확인 추가
        List<GrantedAuthority> authorities = new ArrayList<>();

        if ("admin".equals(username)) {
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        }
        else {
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
        }

        // (3) 스프링 시큐리티 User 규격에 맞게 return
        return new User(siteUSer.getUsername(), siteUSer.getPassword(), authorities);
    }
}
