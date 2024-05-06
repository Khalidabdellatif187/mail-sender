package com.cerebra.mailsender.repository;

import com.cerebra.mailsender.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserName(String userName);

}
