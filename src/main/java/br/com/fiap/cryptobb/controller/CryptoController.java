package br.com.fiap.cryptobb.controller;

import br.com.fiap.cryptobb.dto.CreateUpdateCryptoDTO;
import br.com.fiap.cryptobb.dto.CryptoDTO;
import br.com.fiap.cryptobb.dto.CryptoUsdValueDTO;
import br.com.fiap.cryptobb.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cryptos")
public class CryptoController {

    private CryptoService cryptoService;

    public CryptoController(CryptoService cryptoService){
        this.cryptoService = cryptoService;
    }

    @GetMapping
    public List<CryptoDTO> listCryptos(
            @RequestParam(required = false) String name
    ) {
        return cryptoService.listAll(name);
    }

    @GetMapping("{id}")
    public CryptoDTO getById(@PathVariable int id) {
        return cryptoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CryptoDTO insert(
            @RequestBody CreateUpdateCryptoDTO createUpdateCryptoDTO
    ) {
        return cryptoService.create(createUpdateCryptoDTO);
    }

    @PutMapping("{id}")
    public CryptoDTO update(
            @RequestBody CreateUpdateCryptoDTO createUpdateCryptoDTO,
            @PathVariable int id){
        return cryptoService.update(id, createUpdateCryptoDTO);
    }

    @PatchMapping("{id}")
    public CryptoDTO updateUsdValue(
            @PathVariable int id,
            @RequestBody CryptoUsdValueDTO cryptoUsdValueDTO
    ){
        return cryptoService.updatePrice(id, cryptoUsdValueDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        cryptoService.delete(id);
    }

}
