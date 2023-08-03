package com.kamauro.springSecurity.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ESPECIALIDADES", indexes = {@Index(name = "idx_especialidade_titulo", columnList = "titulo")})
public class Especialidade {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", unique = true, nullable = false)
	private String titulo;
	
	@Column(name = "descricao", columnDefinition = "TEXT")
	private String descricao;
	
	@ManyToMany
	@JoinTable(
			name = "medicos_tem_especialidades_pk",
			joinColumns = @JoinColumn(name = "id_especialidade_pk", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "id_medico_pk", referencedColumnName = "id")
    )
	private List<Medico> medicos;
}
