package com.kamauro.springSecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    @GetMapping({"/", "/home"})
    public String home() {
        return "home";
    }

    @GetMapping({"/login"})
    public String login() {
        return "/login";
    }

    @GetMapping({"/login-error"})
    public String loginError(ModelMap model) {
        model.addAttribute("alerta", "erro");
        model.addAttribute("titulo", "Credenciais inválidas!");
        model.addAttribute("texto", "Login ou senha incorretos, tente novamente.");
        model.addAttribute("subtexto", "Acesso permitido apenas para cadastros já ativados");
        return "login";
    }

    @GetMapping({"/acesso-negado"})
    public String acessoNegado(ModelMap model, HttpServletResponse response) {
        model.addAttribute("status", response.getStatus());
        model.addAttribute("error", "Acesso negado!");
        model.addAttribute("message", "Você não tem permissão para acesso a esta área.");
        return "error";
    }
    
}
