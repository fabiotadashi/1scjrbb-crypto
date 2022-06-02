package br.com.fiap.cryptobb.security;

import br.com.fiap.cryptobb.entity.UserEntity;
import br.com.fiap.cryptobb.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public JwtUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findFirstByNameEquals(username);

        if(userEntity == null){
            throw new UsernameNotFoundException("Username "+username+" not found");
        }

        return new User(
                userEntity.getName(),
                userEntity.getPassword(),
                new ArrayList<>() // Roles
        );
    }
}
