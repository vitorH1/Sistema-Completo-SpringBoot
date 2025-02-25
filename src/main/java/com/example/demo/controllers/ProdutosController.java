package com.example.demo.controllers;

import org.springframework.ui.Model;

import com.example.demo.models.Admin;
import com.example.demo.models.Funcionario;
import com.example.demo.models.Produto;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.demo.repositories.ProdutosRepository;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ProdutosController {

    @Autowired
    private ProdutosRepository produtosRepository;

@GetMapping("/produtos")
public String index(Model model, HttpSession session) {
    List<Produto> produtos = produtosRepository.findAll();
    model.addAttribute("produtos", produtos);

    // Verificar se há um usuário na sessão
    String tipoUsuario = "VISITANTE"; // Valor padrão, caso não haja usuário logado
    
    Object usuario = session.getAttribute("usuarioLogado"); // "usuarioLogado" é o nome do atributo na sessão
    
    if (usuario != null) {
        tipoUsuario = getTipoUsuario(usuario); // Aqui você deve implementar a lógica para determinar o tipo de usuário
    }
    
    model.addAttribute("tipoUsuario", tipoUsuario); // Passa o tipo de usuário para o modelo
    
    return "produtos/index";
}

private String getTipoUsuario(Object usuario) {
    // Lógica para determinar o tipo de usuário
    if (usuario instanceof Funcionario) {
        return "FUNCIONARIO";
    } else if (usuario instanceof Admin) {
        return "ADMIN";
    } else if ("VISITANTE".equals(usuario)) {
        return "VISITANTE";  // Garantir que "VISITANTE" seja tratado corretamente
    } else {
        return "VISITANTE";  // Caso padrão
    }
}


    // Página para criar novo produto - disponível apenas para ADMIN e FUNCIONARIO
    @GetMapping("/produtos/novo")
    public String novoProduto(Model model) {
        // Verifica se o usuário tem permissão
        if (!temPermissaoParaAdicionar()) {
            return "redirect:/produtos"; // Redireciona para a página de listagem se não tiver permissão
        }
        model.addAttribute("produto", new Produto());
        return "produtos/novo";
    }

    // Salvar produto - disponível apenas para ADMIN e FUNCIONARIO
    @PostMapping("/produtos/salvar")
    public String salvarProduto(@ModelAttribute Produto produto) {
        if (!temPermissaoParaAdicionar()) {
            return "redirect:/produtos"; // Redireciona para a página de listagem se não tiver permissão
        }
        produto.setData_cadastro(LocalDate.now());
        produtosRepository.save(produto);
        return "redirect:/produtos";
    }

    // Página para editar produto - disponível apenas para ADMIN e FUNCIONARIO
    @GetMapping("/produtos/editar/{id}")
    public String editarProduto(@PathVariable("id") int id, Model model) {
        if (!temPermissaoParaEditar()) {
            return "redirect:/produtos"; // Redireciona se não tiver permissão
        }
        Produto produto = produtosRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Produto inválido: " + id));
        model.addAttribute("produto", produto);
        return "produtos/editar";
    }

    // Atualizar produto - disponível apenas para ADMIN e FUNCIONARIO
    @PostMapping("/produtos/atualizar/{id}")
    public String atualizarProduto(@PathVariable("id") int id, @ModelAttribute Produto produto) {
        if (!temPermissaoParaEditar()) {
            return "redirect:/produtos"; // Redireciona se não tiver permissão
        }
        produto.setId(id);
        produtosRepository.save(produto);
        return "redirect:/produtos";
    }

    // Excluir produto - disponível apenas para ADMIN
    @GetMapping("/produtos/excluir/{id}")
    public String excluirProduto(@PathVariable("id") int id) {
        if (!temPermissaoParaExcluir()) {
            return "redirect:/produtos"; // Redireciona se não tiver permissão
        }
        Produto produto = produtosRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Produto inválido: " + id));
        produtosRepository.delete(produto);
        return "redirect:/produtos";
    }

    // Método auxiliar para verificar se o usuário tem permissão para adicionar
    private boolean temPermissaoParaAdicionar() {
        // Aqui você deve verificar o tipo de usuário logado
        // Exemplo: Se for administrador ou funcionário
        String role = "FUNCIONARIO"; // Apenas um exemplo, substitua pelo seu mecanismo de verificação real
        return role.equals("ADMIN") || role.equals("FUNCIONARIO");
    }

    // Método auxiliar para verificar se o usuário tem permissão para editar
    private boolean temPermissaoParaEditar() {
        String role = "FUNCIONARIO"; // Substitua pelo mecanismo de verificação real
        return role.equals("ADMIN") || role.equals("FUNCIONARIO");
    }

    // Método auxiliar para verificar se o usuário tem permissão para excluir
    private boolean temPermissaoParaExcluir() {
        String role = "ADMIN"; // Substitua pelo mecanismo de verificação real
        return role.equals("ADMIN");
    }
}
