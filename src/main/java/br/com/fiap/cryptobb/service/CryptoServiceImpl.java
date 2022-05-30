package br.com.fiap.cryptobb.service;

import br.com.fiap.cryptobb.dto.CreateUpdateCryptoDTO;
import br.com.fiap.cryptobb.dto.CryptoDTO;
import br.com.fiap.cryptobb.dto.CryptoUsdValueDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CryptoServiceImpl implements CryptoService {

    @Override
    public List<CryptoDTO> listAll(String name) {
        return new ArrayList<>();
    }

    @Override
    public CryptoDTO findById(int id) {
        return null;
    }

    @Override
    public CryptoDTO create(CreateUpdateCryptoDTO createUpdateCryptoDTO) {
        return null;
    }

    @Override
    public CryptoDTO update(int id, CreateUpdateCryptoDTO createUpdateCryptoDTO) {
        return null;
    }

    @Override
    public CryptoDTO updatePrice(int id, CryptoUsdValueDTO cryptoUsdValueDTO) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

}
