package es.codeurjc.gamepost.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import es.codeurjc.gamepost.objects.User;
import es.codeurjc.gamepost.repositories.UserRepository;

@Component
public class CustomUserAuthenticationProvider implements AuthenticationProvider{

    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Optional<User> user = userRepository.findByName(authentication.getName());

        if(!user.isPresent())
            throw new BadCredentialsException("User not found");

        String password = (String) authentication.getCredentials();
        if(!new BCryptPasswordEncoder().matches(password, user.get().getPassword()))        //TODO: Get password Hashed
            throw new BadCredentialsException("Wrong password");

        List<GrantedAuthority> role = new ArrayList<>();
        role.add(new SimpleGrantedAuthority(user.get().getRole()));
        
        return new UsernamePasswordAuthenticationToken(user.get().getName(), password, role);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // TODO Auto-generated method stub
        return false;
    }
}