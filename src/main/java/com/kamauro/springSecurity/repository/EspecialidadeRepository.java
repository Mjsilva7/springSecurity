package com.kamauro.springSecurity.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kamauro.springSecurity.model.Especialidade;

public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {

    @Query("select e from Especialidade e where e.titulo like :search%")
    Page<Especialidade> findAllByTitulo(String search, Pageable pageable);
    
}
