package kr.hs.dgsw.cns.global.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.hs.dgsw.cns.global.auth.AuthDetailService;
import kr.hs.dgsw.cns.global.exceptions.BusinessException;
import kr.hs.dgsw.cns.global.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final AuthDetailService detailService;

    @Override
    @SuppressWarnings("NullableProblems")
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        try {
            String bearerToken = jwtProvider.getTokenFromHeader(request.getHeader(HttpHeaders.AUTHORIZATION));
            if (bearerToken != null) {
                final String id = jwtProvider.extractLoginFromToken(bearerToken);
                // TODO: High-level modules depend on low-level modules
                final UserDetails userDetails = detailService.loadUserByUsername(id);
                final Authentication authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (BusinessException e) {
            filterException(e, response);
        }
        filterChain.doFilter(request, response);
    }

    private void filterException(BusinessException exception, HttpServletResponse response)
            throws IOException {
        response.setStatus(exception.getCode());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(exception.getMessage());
    }
}
