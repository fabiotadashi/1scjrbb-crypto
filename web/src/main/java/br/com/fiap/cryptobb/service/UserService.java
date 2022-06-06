package br.com.fiap.cryptobb.service;

import br.com.fiap.cryptobb.dto.AuthDTO;
import br.com.fiap.cryptobb.dto.CreateUserDTO;
import br.com.fiap.cryptobb.dto.TokenDTO;
import br.com.fiap.cryptobb.dto.UserDTO;

public interface UserService {

    UserDTO create(CreateUserDTO createUserDTO);

    TokenDTO login(AuthDTO authDTO);

}
