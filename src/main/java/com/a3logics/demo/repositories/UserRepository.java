package com.a3logics.demo.repositories;

import com.a3logics.demo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findById(long id);

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);
}
