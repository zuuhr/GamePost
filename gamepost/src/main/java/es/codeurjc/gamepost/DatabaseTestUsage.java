package es.codeurjc.gamepost;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import es.codeurjc.gamepost.objects.User;
import es.codeurjc.gamepost.repositories.UserRepository;

public class DatabaseTestUsage implements CommandLineRunner{
    @Autowired
    private UserRepository repository;

    //TODO: This seems not to be working.
    @Override
    public void run(String... args) throws Exception{
        repository.save(new User("Yo"));

        Optional<User> users = repository.findById(0);
        
        if (users.isPresent()) {
			System.out.println(users);
		}

        repository.deleteAll();
    }
}
