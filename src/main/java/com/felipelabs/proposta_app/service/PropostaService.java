package com.felipelabs.proposta_app.service;

import com.felipelabs.proposta_app.dto.PropostaRequestDTO;
import com.felipelabs.proposta_app.dto.PropostaResponseDTO;
import com.felipelabs.proposta_app.entity.Proposta;
import com.felipelabs.proposta_app.mapper.PropostaMapper;
import com.felipelabs.proposta_app.repository.PropostaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PropostaService {

    public String exchange;

    PropostaRepository propostaRepository;

    NotificacaoService notificacaoService;

    public PropostaService(@Value("${rabbitmq.propostapendente.exchange}") String exchange,
                           PropostaRepository propostaRepository,
                           NotificacaoService notificacaoService) {

        this.exchange = exchange;
        this.propostaRepository = propostaRepository;
        this.notificacaoService = notificacaoService;
    }

    public PropostaResponseDTO criar(PropostaRequestDTO propostaRequestDTO){
        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(propostaRequestDTO);
        propostaRepository.save(proposta);
        notificarRabbitMQ(proposta);
        return PropostaMapper.INSTANCE.convertEntitytoDto(proposta);
    }

    private void notificarRabbitMQ(Proposta proposta){
        try {
            notificacaoService.notificar(proposta, exchange);
        }catch (RuntimeException ex){
            proposta.setIntegrada(false);
            propostaRepository.save(proposta);
        }
    }

    public List<PropostaResponseDTO> obterProposta() {
        return PropostaMapper.INSTANCE.convertListEntityToListDto(propostaRepository.findAll());
    }
}
