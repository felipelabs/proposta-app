package com.felipelabs.proposta_app.service;

import com.felipelabs.proposta_app.dto.PropostaRequestDTO;
import com.felipelabs.proposta_app.dto.PropostaResponseDTO;
import com.felipelabs.proposta_app.entity.Proposta;
import com.felipelabs.proposta_app.mapper.PropostaMapper;
import com.felipelabs.proposta_app.repository.PropostaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PropostaService {

    PropostaRepository propostaRepository;

    NotificacaoService notificacaoService;

    public PropostaResponseDTO criar(PropostaRequestDTO propostaRequestDTO){
        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(propostaRequestDTO);
        propostaRepository.save(proposta);

        PropostaResponseDTO response = PropostaMapper.INSTANCE.convertEntitytoDto(proposta);
        notificacaoService.notificar(response, "proposta-pendente.ex");

        return response;
    }

    public List<PropostaResponseDTO> obterProposta() {
        return PropostaMapper.INSTANCE.convertListEntityToListDto(propostaRepository.findAll());
    }
}
