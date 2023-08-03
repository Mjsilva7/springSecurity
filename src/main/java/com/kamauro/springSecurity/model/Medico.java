package com.kamauro.springSecurity.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "MEDICOS")
public class Medico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", unique = true, nullable = false)
	private String nome;
	
	@Column(name = "crm", unique = true, nullable = false)
	private Integer crm;
	
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_inscricao", nullable = false)
	private LocalDate dtInscricao;

	@JsonIgnore
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
			name = "medicos_tem_especialidades_pk",
			joinColumns = @JoinColumn(name = "id_medico_pk", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "id_especialidade_pk", referencedColumnName = "id")
    )
	private Set<Especialidade> especialidades;
	
	@JsonIgnore
	@OneToMany(mappedBy = "medico")
	private List<Agendamento> agendamentos;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "id_usuario_pk")
	private Usuario usuario;
}
