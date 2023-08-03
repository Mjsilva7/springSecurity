package com.kamauro.springSecurity.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "USUARIOS", indexes = {@Index(name = "idx_usuario_email", columnList = "email")})
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
	private String email;
	
	@JsonIgnore
	@Column(name = "senha", nullable = false)
	private String senha;
	
	@ManyToMany
	@JoinTable(
		name = "usuarios_tem_perfis_pk", 
        joinColumns = { @JoinColumn(name = "id_usuario_pk", referencedColumnName = "id") }, 
        inverseJoinColumns = { @JoinColumn(name = "id_perfil_pk", referencedColumnName = "id") }
	)
	private List<Perfil> perfis;
	
	@Column(name = "ativo", nullable = false, columnDefinition = "TINYINT(1)")
	private boolean ativo;
	
	@Column(name = "codigo_verificador", length = 6)
	private String codigoVerificador;
}
