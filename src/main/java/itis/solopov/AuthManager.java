package itis.solopov;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthManager {
    public Boolean isAuthorized() {
        Authentication auth = extractAuthentication();
        return auth != null && (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("USER")) || auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN")));
    }

    public Authentication extractAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
