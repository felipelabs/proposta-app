package com.felipelabs.proposta_app.service;

import com.felipelabs.proposta_app.dto.PropostaRequestDTO;
import com.felipelabs.proposta_app.dto.PropostaResponseDTO;
import com.felipelabs.proposta_app.entity.Proposta;
import com.felipelabs.proposta_app.mapper.PropostaMapper;
import com.felipelabs.proposta_app.repository.PropostaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PropostaService {

    PropostaRepository propostaRepository;

    public PropostaResponseDTO criar(PropostaRequestDTO propostaRequestDTO){
        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(propostaRequestDTO);
        propostaRepository.save(proposta);
        return PropostaMapper.INSTANCE.convertEntitytoDto(proposta);
    }
}
