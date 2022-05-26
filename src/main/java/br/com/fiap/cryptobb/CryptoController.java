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

    private ArrayList<CryptoDTO> cryptoDTOList = new ArrayList<>();

    public CryptoController() {
        cryptoDTOList.add(new CryptoDTO(1, "BitCoin", "BTC", BigDecimal.valueOf(150_000)));
        cryptoDTOList.add(new CryptoDTO(2, "Etherium", "ETH", BigDecimal.valueOf(10_000)));
        cryptoDTOList.add(new CryptoDTO(3, "Dodge Coin", "DODGE", BigDecimal.valueOf(3)));
        cryptoDTOList.add(new CryptoDTO(4, "Cardano", "ADA", BigDecimal.valueOf(3.5)));
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
    public CryptoDTO getById(@PathVariable int id) {
        return getCryptoByIdDTO(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CryptoDTO insert(
            @RequestBody CreateUpdateCryptoDTO createUpdateCryptoDTO
    ) {
        CryptoDTO cryptoDTO = new CryptoDTO();

        if(createUpdateCryptoDTO.getName() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        cryptoDTO.setId(cryptoDTOList.size() + 1);
        cryptoDTO.setName(createUpdateCryptoDTO.getName());
        cryptoDTO.setAcronym(createUpdateCryptoDTO.getAcronym());
        cryptoDTO.setUsdValue(createUpdateCryptoDTO.getUsdValue());

        cryptoDTOList.add(cryptoDTO);
        return cryptoDTO;
    }

    @PutMapping("{id}")
    public CryptoDTO update(
            @RequestBody CreateUpdateCryptoDTO createUpdateCryptoDTO,
            @PathVariable int id){
        CryptoDTO cryptoDTO = getCryptoByIdDTO(id);
        cryptoDTO.setName(createUpdateCryptoDTO.getName());
        cryptoDTO.setAcronym(createUpdateCryptoDTO.getAcronym());
        cryptoDTO.setUsdValue(createUpdateCryptoDTO.getUsdValue());
        return cryptoDTO;
    }

    @PatchMapping("{id}")
    public CryptoDTO updateUsdValue(
            @PathVariable int id,
            @RequestBody CryptoUsdValueDTO cryptoUsdValueDTO
    ){
        CryptoDTO cryptoDTO = getCryptoByIdDTO(id);
        cryptoDTO.setUsdValue(cryptoUsdValueDTO.getUsdValue());
        return cryptoDTO;
    }

    @DeleteMapping("id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        CryptoDTO cryptoDTO = getCryptoByIdDTO(id);
        cryptoDTOList.remove(cryptoDTO);
    }

    private CryptoDTO getCryptoByIdDTO(int id) {
        return cryptoDTOList.stream()
                .filter(cryptoDTO -> cryptoDTO.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
