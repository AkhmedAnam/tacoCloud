package ru.ahmed.anam.taco.data;

import org.springframework.data.repository.CrudRepository;
import ru.ahmed.anam.taco.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
