package uz.pdp.digitallibrary.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.digitallibrary.entity.User;
import uz.pdp.digitallibrary.service.AuthService;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final AuthService authService;;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String authorization = request.getHeader("Authorization");
        if (Objects.nonNull(authorization) && authorization.startsWith("Bearer ")) {

            String token = authorization.substring(7);

                String username = jwtProvider.validateToken(token);

            User user = (User) authService.loadUserByUsername(username);


            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);


        }

        //yozaman keyin faqat


        filterChain.doFilter(request, response);

    }
}
