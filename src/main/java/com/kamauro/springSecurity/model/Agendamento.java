package com.kamauro.springSecurity.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "AGENDAMENTOS")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
	@JoinColumn(name="id_especialidade")
	private Especialidade especialidade;
	
	@ManyToOne
	@JoinColumn(name="id_medico_pk")
	private Medico medico;
	
	@ManyToOne
	@JoinColumn(name="id_paciente_pk")
	private Paciente paciente;
	
	@ManyToOne
	@JoinColumn(name="id_horario_pk")
	private Horario horario; 

	@Column(name="data_consulta")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataConsulta;
    
}
