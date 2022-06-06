package br.com.fiap.cryptobb.controller;

import br.com.fiap.cryptobb.dto.AuthDTO;
import br.com.fiap.cryptobb.dto.CreateUserDTO;
import br.com.fiap.cryptobb.dto.TokenDTO;
import br.com.fiap.cryptobb.dto.UserDTO;
import br.com.fiap.cryptobb.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public UserDTO createUser(@RequestBody CreateUserDTO createUserDTO){
        return userService.create(createUserDTO);
    }

    @PostMapping("login")
    public TokenDTO login(@RequestBody AuthDTO authDTO){
        return userService.login(authDTO);
    }


}
