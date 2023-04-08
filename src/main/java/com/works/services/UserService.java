package com.works.services;

import com.works.utils.REnum;
import com.works.entities.Role;
import com.works.entities.User;
import com.works.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserService implements UserDetailsService {

    final UserRepository userRepository;
    final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, @Lazy PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = null;

        Optional<User> optionalUser = userRepository.findByEmailEquals(username);
        if (optionalUser.isPresent()){
            User dbUser = optionalUser.get();
            userDetails = new org.springframework.security.core.userdetails.User(
                    dbUser.getEmail(),
                    dbUser.getPassword(),
                    dbUser.isEnabled(),
                    dbUser.isTokenExpired(),
                    true,
                    true,
                    getAuthorities(dbUser.getRoles())
                    );
        }else {
            throw new UsernameNotFoundException("This user is not exist.");
        }
        return userDetails;
    }

    List<GrantedAuthority> getAuthorities(List<Role> roles){
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }


    public ResponseEntity register(User user){
        Map<REnum, Object> hm = new LinkedHashMap<>();
        Optional<User> optionalUser = userRepository.findByEmailEquals(user.getEmail());
        if (optionalUser.isPresent()){
            hm.put(REnum.status, false);
            hm.put(REnum.message, "The user is already registered.");
            hm.put(REnum.result, optionalUser.get().getEmail());
            return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
        }else{
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            hm.put(REnum.status, true);
            hm.put(REnum.message, "User registered.");
            hm.put(REnum.result, user);
            return new ResponseEntity(hm, HttpStatus.OK);
        }
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

}
