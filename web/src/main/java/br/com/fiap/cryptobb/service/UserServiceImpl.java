package br.com.fiap.cryptobb.service;

import br.com.fiap.cryptobb.dto.AuthDTO;
import br.com.fiap.cryptobb.dto.CreateUserDTO;
import br.com.fiap.cryptobb.dto.TokenDTO;
import br.com.fiap.cryptobb.dto.UserDTO;
import br.com.fiap.cryptobb.entity.UserEntity;
import br.com.fiap.cryptobb.repository.UserRepository;
import br.com.fiap.cryptobb.security.JwtTokenUtils;
import javassist.tools.web.BadHttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils jwtTokenUtils;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(AuthenticationManager authenticationManager,
                           JwtTokenUtils jwtTokenUtils,
                           PasswordEncoder passwordEncoder,
                           UserRepository userRepository){
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtils = jwtTokenUtils;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO create(CreateUserDTO createUserDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(createUserDTO.getUsername());
        userEntity.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));

        UserEntity savedUser = userRepository.save(userEntity);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(savedUser.getId());
        userDTO.setUsername(savedUser.getName());
        return userDTO;
    }

    @Override
    public TokenDTO login(AuthDTO authDTO) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authDTO.getUsername(),
                            authDTO.getPassword()
                    )
            );
        } catch (DisabledException disabledException){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user.disabled");
        } catch (BadCredentialsException badCredentialsException){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid.credentials");
        }

        String token = jwtTokenUtils.createToken(authDTO.getUsername());

        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setToken(token);

        return tokenDTO;
    }

}
