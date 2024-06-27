package com.felipelabs.proposta_app.service;

import com.felipelabs.proposta_app.dto.PropostaResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificacaoService {

    public RabbitTemplate rabbitTemplate;

    public void notificar(PropostaResponseDTO propostaResponseDTO, String exchange){
        rabbitTemplate.convertAndSend(exchange, "", propostaResponseDTO);
    }
}
