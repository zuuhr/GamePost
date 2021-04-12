package es.codeurjc.gamepost.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

import es.codeurjc.gamepost.objects.User;
import es.codeurjc.gamepost.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(username);
        UserBuilder userBuilder = null;

        if (!user.isPresent())
            throw new UsernameNotFoundException("User not found");
        
        userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
        userBuilder.disabled(false);
        userBuilder.password(user.get().getPassword());
        userBuilder.authorities(new SimpleGrantedAuthority("ROLE_USER"));
        
        return userBuilder.build();
    }
    
}
