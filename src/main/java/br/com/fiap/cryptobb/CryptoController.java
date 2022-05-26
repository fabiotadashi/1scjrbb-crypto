package br.com.fiap.cryptobb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CryptoController {

    @GetMapping
    public String hello(){
        return "Hello World";
    }

}
