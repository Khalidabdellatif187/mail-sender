package com.cerebra.mailsender.serviceImpl;

import com.cerebra.mailsender.model.User;
import com.cerebra.mailsender.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CustomDetailsUserService implements UserDetailsService {


    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userNameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUserNameOrEmail(userNameOrEmail , userNameOrEmail);
        if (user == null){
            throw new UsernameNotFoundException("User not found with username or email:" + userNameOrEmail);
        } else {
            return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword() , Collections.emptyList()
            );
        }
    }

}
