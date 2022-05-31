package br.com.fiap.cryptobb.service;

import br.com.fiap.cryptobb.dto.CreateUpdateCryptoDTO;
import br.com.fiap.cryptobb.dto.CryptoDTO;
import br.com.fiap.cryptobb.dto.CryptoUsdValueDTO;
import br.com.fiap.cryptobb.entity.CryptoEntity;
import br.com.fiap.cryptobb.repository.CryptoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CryptoServiceImpl implements CryptoService {

    private CryptoRepository cryptoRepository;

    public CryptoServiceImpl(CryptoRepository cryptoRepository){
        this.cryptoRepository = cryptoRepository;
    }

    @Override
    public List<CryptoDTO> listAll(String name) {
        return cryptoRepository.findAll()
                .stream()
                .map(entity -> new CryptoDTO(entity))
                .collect(Collectors.toList());
    }

    @Override
    public CryptoDTO findById(int id) {
        CryptoEntity cryptoEntity = cryptoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new CryptoDTO(cryptoEntity);
    }

    @Override
    public CryptoDTO create(CreateUpdateCryptoDTO createUpdateCryptoDTO) {
        CryptoEntity entity = new CryptoEntity();

        entity.setName(createUpdateCryptoDTO.getName());
        entity.setAcronym(createUpdateCryptoDTO.getAcronym());
        entity.setUsdValue(createUpdateCryptoDTO.getUsdValue());

        CryptoEntity savedEntity = cryptoRepository.save(entity);
        return new CryptoDTO(savedEntity);
    }

    @Override
    public CryptoDTO update(int id, CreateUpdateCryptoDTO createUpdateCryptoDTO) {
        CryptoEntity entity = cryptoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        entity.setName(createUpdateCryptoDTO.getName());
        entity.setAcronym(createUpdateCryptoDTO.getAcronym());
        entity.setUsdValue(createUpdateCryptoDTO.getUsdValue());

        CryptoEntity savedEntity = cryptoRepository.save(entity);
        return new CryptoDTO(savedEntity);
    }

    @Override
    public CryptoDTO updatePrice(int id, CryptoUsdValueDTO cryptoUsdValueDTO) {
        CryptoEntity entity = cryptoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        entity.setUsdValue(cryptoUsdValueDTO.getUsdValue());

        CryptoEntity savedEntity = cryptoRepository.save(entity);
        return new CryptoDTO(savedEntity);
    }

    @Override
    public void delete(int id) {
        CryptoEntity entity = cryptoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        cryptoRepository.delete(entity);
    }

}
