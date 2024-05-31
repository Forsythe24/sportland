package itis.solopov.filter;

import io.jsonwebtoken.Claims;
import itis.solopov.model.Role;
import itis.solopov.repository.RoleRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Component
public class JwtFilter extends GenericFilterBean {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER = "Bearer";

    private final JwtProvider jwtProvider;

    private final RoleRepository roleRepository;

    public JwtFilter(JwtProvider jwtProvider, RoleRepository roleRepository) {
        this.jwtProvider = jwtProvider;
        this.roleRepository = roleRepository;
    }

    public JwtAuthentication generate(Claims claims) {
        JwtAuthentication jwtAuthentication = new JwtAuthentication();
        jwtAuthentication.setRoles(getRoles(claims));
        jwtAuthentication.setEmail(claims.getSubject());
        jwtAuthentication.setId(claims.get("id", String.class));

        return jwtAuthentication;
    }

    private Set<Role> getRoles(Claims claims) {
        List<HashMap<String, Object>> roleMaps = claims.get("roles", List.class);

        Set<Role> roles = new HashSet<>();

        for (HashMap<String, Object> roleMap : roleMaps) {
            Role role = new Role();
            role.setId((Integer) roleMap.get("id"));
            role.setName((String) roleMap.get("name"));
            roles.add(role);
        }
        return roles;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = getTokenFromRequest((HttpServletRequest) servletRequest);
        if (token != null) {
            if (jwtProvider.validateAccessToken(token)) {
                Claims claims = jwtProvider.getAcessClaims(token);
                JwtAuthentication jwtAuthentication = generate(claims);
                jwtAuthentication.setAuthenticated(true);
                SecurityContextHolder.getContext().setAuthentication(jwtAuthentication);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(AUTHORIZATION_HEADER);
        if (bearer != null && bearer.startsWith(BEARER)) {
            return bearer.substring(BEARER.length() + 1);
        }
        return null;
    }
}
