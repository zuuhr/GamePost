package es.codeurjc.gamepost.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.gamepost.objects.User;
import es.codeurjc.gamepost.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    
    public Optional<User> get(String username){
        return userRepository.findByName(username);
    }

    public void submit(String username, String password){
        userRepository.save(new User(
                username, 
                //new BCryptPasswordEncoder().encode(password));
                password
            ));
    }

}
