package social_media.social_media.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import social_media.social_media.model.UserModel;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Custom UserDetails implementation for the social media application.
 * This class implements UserDetails interface and provides user information
 * required by Spring Security.
 */
public class CustomerUserDetails implements UserDetails {

    private final String email;
    private final String password;
    private final String role;

    public CustomerUserDetails(UserModel userModel) {
        this.email = userModel.getEmail();
        this.password = userModel.getPassword();
        this.role = userModel.getRole().name();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("Role_" + role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
