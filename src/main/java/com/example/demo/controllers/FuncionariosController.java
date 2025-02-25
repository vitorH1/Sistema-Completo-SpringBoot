package com.example.demo.controllers;

import com.example.demo.models.Admin;
import com.example.demo.models.Funcionario;
import com.example.demo.utils.MD5Utils;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import com.example.demo.repositories.FuncionariosRepository;

@Controller
public class FuncionariosController {

    @Autowired
    private FuncionariosRepository funcionariosRepository;

    @GetMapping("/funcionarios")
    public String index(Model model, HttpSession session) {
        List<Funcionario> funcionarios = funcionariosRepository.findAll();
        model.addAttribute("funcionarios", funcionarios);

        funcionarios.forEach(func -> 
        func.setCpf(func.getCpf().replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4"))
    );
        // Verificar se há um usuário na sessão
        String tipoUsuario = "VISITANTE"; // Valor padrão, caso não haja usuário logado
        
        Object usuario = session.getAttribute("usuarioLogado"); // "usuarioLogado" é o nome do atributo na sessão
        
        if (usuario != null) {
            tipoUsuario = getTipoUsuario(usuario); // Aqui você deve implementar a lógica para determinar o tipo de usuário
        }
        
        model.addAttribute("tipoUsuario", tipoUsuario); // Passa o tipo de usuário para o modelo
        
        return "funcionarios/index"; // Nome da view
    }
    
    private String getTipoUsuario(Object usuario) {
        if (usuario instanceof Funcionario) {
            return "FUNCIONARIO";
        } else if (usuario instanceof Admin) {
            return "ADMIN";
        } else {
            return "VISITANTE";  // Caso padrão
        }
    }

    @GetMapping("/funcionarios/novo")
    public String novoFuncionario(Model model, HttpSession session) {
        if (!temPermissaoParaAdicionar(session)) {
            return "redirect:/funcionarios"; // Redireciona para a página de listagem se não tiver permissão
        }
        model.addAttribute("funcionario", new Funcionario());
        return "funcionarios/novo"; // Retorna o template correto
    }

    @PostMapping("/funcionarios/salvar")
    public String salvarFuncionario(@ModelAttribute Funcionario funcionario, HttpSession session) {
        if (!temPermissaoParaAdicionar(session)) {
            return "redirect:/funcionarios"; // Redireciona para a página de listagem se não tiver permissão
        }

        // Criptografar a senha antes de salvar no banco de dados
        String senhaCriptografada = MD5Utils.encrypt(funcionario.getSenha());
        funcionario.setSenha(senhaCriptografada);

        funcionariosRepository.save(funcionario); // Salva o funcionário no banco de dados
        return "redirect:/funcionarios"; // Redireciona para a página de listagem de funcionários
    }

    @GetMapping("/funcionarios/editar/{id}")
    public String editarFuncionario(@PathVariable("id") int id, Model model, HttpSession session) {
        if (!temPermissaoParaEditar(session)) {
            return "redirect:/funcionarios"; // Redireciona se não tiver permissão
        }

        Funcionario funcionario = funcionariosRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Funcionário inválido: " + id));
        model.addAttribute("funcionario", funcionario);
        return "funcionarios/editar"; // O template está correto
    }

    @PostMapping("/funcionarios/atualizar/{id}")
    public String atualizarFuncionario(@PathVariable("id") int id, @ModelAttribute Funcionario funcionario) {
        funcionario.setFuncionario_id(id);
        funcionariosRepository.save(funcionario);
        return "redirect:/funcionarios";
     }

    @GetMapping("/funcionarios/atualizar/{id}")
public String atualizarFuncionario(@PathVariable("id") int id, Model model) {
    Funcionario funcionario = funcionariosRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Funcionário inválido: " + id));
    model.addAttribute("funcionario", funcionario);
    return "funcionarios/editar";  // A página ou template que você deseja renderizar
}

    @GetMapping("/funcionarios/excluir/{id}")
    public String excluirFuncionarios(@PathVariable("id") int id) {
        Funcionario funcionario = funcionariosRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("cliente inválido: " + id));            funcionariosRepository.delete(funcionario);
        return "redirect:/funcionarios";
    }

    // Método auxiliar para verificar se o usuário tem permissão para adicionar
    private boolean temPermissaoParaAdicionar(HttpSession session) {
        String tipoUsuario = (String) session.getAttribute("tipoUsuario");
        return tipoUsuario != null && (tipoUsuario.equals("ADMIN") || tipoUsuario.equals("FUNCIONARIO"));
    }

    // Método auxiliar para verificar se o usuário tem permissão para editar
    private boolean temPermissaoParaEditar(HttpSession session) {
        String tipoUsuario = (String) session.getAttribute("tipoUsuario");
        return tipoUsuario != null && (tipoUsuario.equals("ADMIN") || tipoUsuario.equals("FUNCIONARIO"));
    }

    // Método auxiliar para verificar se o usuário tem permissão para excluir
    private boolean temPermissaoParaExcluir(HttpSession session) {
        String tipoUsuario = (String) session.getAttribute("tipoUsuario");
        return tipoUsuario != null && tipoUsuario.equals("ADMIN");
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Invalida a sessão do usuário
        return "redirect:/login"; // Redireciona para a página de login
    }
}
