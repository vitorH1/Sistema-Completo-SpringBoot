package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        String tipoUsuario = (String) session.getAttribute("tipoUsuario");

        if (tipoUsuario == null) {
            return "redirect:/login"; // Redireciona para a página de login se não estiver logado
        }

        model.addAttribute("tipoUsuario", tipoUsuario); // Adiciona o tipo de usuário ao modelo
        return "dashboard"; // Retorna o template do dashboard
    }   

}    