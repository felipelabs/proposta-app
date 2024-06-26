package com.felipelabs.proposta_app.mapper;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import com.felipelabs.proposta_app.dto.PropostaRequestDTO;
import com.felipelabs.proposta_app.dto.PropostaResponseDTO;
import com.felipelabs.proposta_app.entity.Proposta;
import org.hibernate.boot.model.internal.XMLContext;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.text.NumberFormat;
import java.util.List;

@Mapper
public interface PropostaMapper {

    PropostaMapper INSTANCE = Mappers.getMapper(PropostaMapper.class);

    @Mapping(target ="usuario.nome", source = "nome")
    @Mapping(target ="usuario.sobrenome", source = "sobrenome")
    @Mapping(target ="usuario.cpf", source = "cpf")
    @Mapping(target ="usuario.telefone", source = "telefone")
    @Mapping(target ="usuario.renda", source = "renda")
    @Mapping(target ="id", ignore = true)
    @Mapping(target ="aprovada", ignore = true)
    @Mapping(target ="integrada", ignore = true)
    @Mapping(target ="observacao", ignore = true)
    Proposta convertDtoToProposta(PropostaRequestDTO propostaRequestDTO);


    @Mapping(target = "nome", source = "usuario.nome")
    @Mapping(target = "sobrenome", source = "usuario.sobrenome")
    @Mapping(target = "cpf", source = "usuario.cpf")
    @Mapping(target = "telefone", source = "usuario.telefone")
    @Mapping(target = "renda", source = "usuario.renda")
    @Mapping(target = "valorSolicitadoFmt", expression = "java(setValorSolicitadoFmt(propostas))")
    PropostaResponseDTO convertEntitytoDto(Proposta propostas);

    List<PropostaResponseDTO> convertListEntityToListDto(Iterable<Proposta> propostas);

    default String setValorSolicitadoFmt(Proposta propostas){
        return NumberFormat.getCurrencyInstance().format(propostas.getValorSolicitado());
    }

}
