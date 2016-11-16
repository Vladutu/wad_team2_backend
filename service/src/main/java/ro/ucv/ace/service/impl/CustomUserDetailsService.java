package ro.ucv.ace.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.ucv.ace.exception.EntityNotFoundException;
import ro.ucv.ace.model.IAuthenticatable;
import ro.ucv.ace.service.ILoginService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by ctotolin on 24-Apr-16.
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ILoginService loginService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            IAuthenticatable authenticatable = loginService.getByUsername(username);

            return new org.springframework.security.core.userdetails.User(authenticatable.getUsername(), authenticatable.getPassword(),
                    true, true, true, true, getGrantedAuthorities(authenticatable));
        } catch (EntityNotFoundException e) {
            throw new UsernameNotFoundException("Username not found");
        }
    }

    private Collection<? extends GrantedAuthority> getGrantedAuthorities(IAuthenticatable authenticatable) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + authenticatable.getRole()));

        return authorities;
    }
}