package com.felipelabs.proposta_app.mapper;

import com.felipelabs.proposta_app.dto.PropostaRequestDTO;
import com.felipelabs.proposta_app.entity.Proposta;
import org.mapstruct.Mapper;

@Mapper
public interface PropostaMapper {

    Proposta convertDtoToProposta(PropostaRequestDTO propostaRequestDTO);
}
