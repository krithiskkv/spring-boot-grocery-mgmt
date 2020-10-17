package org.krithika.grocery.repository;

import org.krithika.grocery.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by administrator on 1/10/20.
 */
@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    List<User> findAll();

    User findByUserNameEqualsIgnoreCase(String name);

}
