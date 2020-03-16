package com.sbrf.fullSpring.repos;

import com.sbrf.fullSpring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends JpaRepository<User, Long > {
    User findByUserName(String userName);
}
