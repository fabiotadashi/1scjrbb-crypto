package br.com.fiap.cryptobb.service;

import br.com.fiap.cryptobb.dto.CreateUpdateCryptoDTO;
import br.com.fiap.cryptobb.dto.CryptoDTO;
import br.com.fiap.cryptobb.dto.CryptoUsdValueDTO;

import java.util.List;

public interface CryptoService {

    List<CryptoDTO> listAll(String name);
    CryptoDTO findById(int id);
    CryptoDTO create(CreateUpdateCryptoDTO createUpdateCryptoDTO);
    CryptoDTO update(int id, CreateUpdateCryptoDTO createUpdateCryptoDTO);
    CryptoDTO updatePrice(int id, CryptoUsdValueDTO cryptoUsdValueDTO);
    void delete(int id);

}
