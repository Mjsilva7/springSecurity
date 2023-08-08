package com.kamauro.springSecurity.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.kamauro.springSecurity.datatables.Datatables;
import com.kamauro.springSecurity.datatables.DatatablesColunas;
import com.kamauro.springSecurity.model.Especialidade;
import com.kamauro.springSecurity.repository.EspecialidadeRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class EspecialidadeService {

    @Autowired
    private EspecialidadeRepository repository;

    @Autowired
    private Datatables datatables;

    public void salvar(Especialidade especialidade) {
        repository.save(especialidade);
    }

    public Map<String, Object> buscarEspecialidades(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.ESPECIALIDADES);
        Page<?> page = datatables.getSearch().isEmpty()
                ? repository.findAll(datatables.getPageable())
                : repository.findAllByTitulo(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

    public Especialidade buscarPorId(Long id) {
        return repository.findById(id).get();
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
    
}
