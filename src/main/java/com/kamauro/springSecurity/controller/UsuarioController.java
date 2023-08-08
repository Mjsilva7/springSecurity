package com.kamauro.springSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kamauro.springSecurity.model.Usuario;
import com.kamauro.springSecurity.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("u")
public class UsuarioController {

    @Autowired
    private UsuarioService serviceUsuario;

    @GetMapping("/novo/cadastro/usuario")
    public String cadastroPorAdminParaAdminMedicoPaciente(Usuario usuario) {
        return "usuario/cadastro";
    }

    @GetMapping("/lista")
    public String listaTodos() {
        return "usuario/lista";
    }

    @GetMapping("/datatables/server/usuarios")
    public ResponseEntity<?> listaDatatables(HttpServletRequest request) {
        return ResponseEntity.ok(serviceUsuario.buscarTodos(request));
    }

    
}
