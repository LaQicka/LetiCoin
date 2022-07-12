package com.leticoin.webapp.dao;

import com.leticoin.webapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
