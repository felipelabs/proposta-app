package com.felipelabs.proposta_app.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_proposta")
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double valorSolicitado;
    private int prazoPagamento;
    private Boolean aprovada;
    private boolean integrada;
    private String observacao;

    @OneToOne
    @JoinColumn(name = "tb_usuario")
    private Usuario usuario;
}
