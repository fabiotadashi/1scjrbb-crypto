package br.com.fiap.cryptobb;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cryptos")
public class CryptoController {

    private List<CryptoDTO> cryptoDTOList;

    public CryptoController(){
        cryptoDTOList =  Arrays.asList(
                new CryptoDTO(1, "BitCoin", "BTC", BigDecimal.valueOf(150_000)),
                new CryptoDTO(2, "Etherium", "ETH", BigDecimal.valueOf(10_000)),
                new CryptoDTO(3, "Dodge Coin", "DODGE", BigDecimal.valueOf(3)),
                new CryptoDTO(4, "Cardano", "ADA", BigDecimal.valueOf(3.5))
        );
    }

    @GetMapping
    public List<CryptoDTO> listCryptos(
            @RequestParam(required = false) String name
    ) {
        return cryptoDTOList.stream()
                .filter(cryptoDTO -> name == null || cryptoDTO.getName().contains(name))
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public CryptoDTO getById(@PathVariable int id){
        return cryptoDTOList.stream()
            .filter(cryptoDTO -> cryptoDTO.getId() == id)
            .findFirst()
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
