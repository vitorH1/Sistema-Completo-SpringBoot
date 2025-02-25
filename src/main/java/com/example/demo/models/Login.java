package com.example.demo.models;

import com.example.demo.utils.MD5Utils;
import com.example.demo.repositories.AdminRepository;
import com.example.demo.repositories.FuncionariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

@Controller
public class Login {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private FuncionariosRepository funcionariosRepository;

    @GetMapping("/login")
    public String mostrarLogin() {
         return "login"; // Nome do template ou página HTML para login
     }

    @PostMapping("/login")
    public String login(@RequestParam String nome, @RequestParam String senha, HttpSession session) {
        
        // Autenticação como Admin (sem criptografar a senha)
        Admin admin = adminRepository.findByNomeAndSenha(nome, senha).orElse(null);
        if (admin != null) {
            session.setAttribute("usuarioLogado", admin);
            session.setAttribute("tipoUsuario", "ADMIN");
            return "redirect:/dashboard";
        }

        // Autenticação como Funcionário (criptografando a senha)
        String senhaCriptografada = MD5Utils.encrypt(senha); // Criptografa a senha
        Funcionario funcionario = funcionariosRepository.findByNomeAndSenha(nome, senhaCriptografada).orElse(null);
        if (funcionario != null) {
            session.setAttribute("usuarioLogado", funcionario);
            session.setAttribute("tipoUsuario", "FUNCIONARIO");
            return "redirect:/dashboard";
        }

        // Login inválido
        return "redirect:/login?erro=true";
    }

    @GetMapping("/dashboard/visitante")
    public String entrarComoVisitante(HttpSession session) {
        session.setAttribute("usuarioLogado", "VISITANTE");
        session.setAttribute("tipoUsuario", "VISITANTE"); // Marca como Visitante
        return "redirect:/dashboard";
    }
    
}
