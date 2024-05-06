package com.cerebra.mailsender.repository;

import com.cerebra.mailsender.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserNameOrEmail(String userName,String email);
    User findByUserName(String userName);
    User findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);


}
