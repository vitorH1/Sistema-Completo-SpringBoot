package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.Admin;
import com.example.demo.models.Funcionario;
import com.example.demo.repositories.AdminRepository;
import com.example.demo.repositories.FuncionariosRepository;
import com.example.demo.utils.MD5Utils;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private FuncionariosRepository funcionariosRepository;

    // Método para autenticação de login
    @PostMapping("/auth")
    public String autenticar(@RequestParam String nome, @RequestParam String senha, HttpSession session, Model model) {
        // Criptografa a senha fornecida para comparação com o banco de dados
        String senhaCriptografada = MD5Utils.encrypt(senha);
    
        // Verificação para Admin
        Admin admin = adminRepository.findByNomeAndSenha(nome, senhaCriptografada).orElse(null);
        if (admin != null) {
            session.setAttribute("usuarioLogado", admin);
            session.setAttribute("tipoUsuario", "ADMIN");
            return "redirect:/dashboard"; // Redireciona para o dashboard
        }
    
        // Verificação para Funcionário
        Funcionario funcionario = funcionariosRepository.findByNomeAndSenha(nome, senhaCriptografada).orElse(null);
        if (funcionario != null) {
            session.setAttribute("usuarioLogado", funcionario);
            session.setAttribute("tipoUsuario", "FUNCIONARIO");
            return "redirect:/dashboard"; // Redireciona para o dashboard
        }
    
        // Caso de falha no login
        return "redirect:/login?erro=true";  // Redireciona com um parâmetro de erro
    }

    // Método para login como Visitante
    @GetMapping("/dashboard/visitante")
    public String entrarComoVisitante(HttpSession session) {
        session.setAttribute("usuarioLogado", "VISITANTE");
        session.setAttribute("tipoUsuario", "VISITANTE"); // Marca como Visitante
        return "redirect:/dashboard";
    }

    // Método de logout
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Invalida a sessão do usuário
        return "redirect:/login"; // Redireciona para a página de login
    }
}
