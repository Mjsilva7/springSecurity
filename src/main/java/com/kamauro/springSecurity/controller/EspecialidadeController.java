package com.kamauro.springSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kamauro.springSecurity.model.Especialidade;
import com.kamauro.springSecurity.service.EspecialidadeService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/especialidades")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeService serviceEspecialidade;

    @GetMapping({"", "/"})
    public String abrir(Especialidade especialidade) {
        return "especialidade/especialidade";
    }

    @PostMapping("/salvar")
    public String salvar(Especialidade especialidade, RedirectAttributes attr) {
        serviceEspecialidade.salvar(especialidade);

        attr.addFlashAttribute("sucesso", "Operação realizada com sucesso.");
        return "redirect:/especialidades";
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> getEspecialidades(HttpServletRequest request) {
        return ResponseEntity.ok(serviceEspecialidade.buscarEspecialidades(request));
    }

    @GetMapping("/editar/{id}")
    public String abrirTelaEdicao(@PathVariable("id")Long id, ModelMap model) {
        model.addAttribute("especialidade", serviceEspecialidade.buscarPorId(id));
        return "especialidade/especialidade";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id")Long id, RedirectAttributes attr) {
        serviceEspecialidade.excluir(id);
        attr.addFlashAttribute("sucesso", "Operação realizada com sucesso.");
        return "redirect:/especialidades";
    }
    
}
