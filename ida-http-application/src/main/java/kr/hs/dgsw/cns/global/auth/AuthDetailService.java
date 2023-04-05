package kr.hs.dgsw.cns.global.auth;

import kr.hs.dgsw.cns.aggregate.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthDetailService implements UserDetailsService {

    private final AuthService<Long> authService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final long id = Long.parseLong(username);
        if (!(authService.loadAuthByIdentify(id) instanceof Member member)) {
            throw new UsernameNotFoundException(String.format("%d is not exists", id));
        }

        return toUserDetails(member);
    }

    private UserDetails toUserDetails(Member member) {
        return new AuthUser(member.getId(), member.getEmail(),member.getPassword().getValue(),
                toGrantedAuthorities(member.getRole()));
    }

    public Collection<? extends GrantedAuthority> toGrantedAuthorities(Enum<?> role) {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
}
