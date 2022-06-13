package br.com.fiap.cryptonative.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("strings")
public class StringController {

    @GetMapping("reverse")
    public String reverse(
            @RequestParam String value
    ){
        return new StringBuilder(value).reverse().toString();
    }

}
