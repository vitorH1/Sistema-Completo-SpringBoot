package com.example.demo.controllers;
import com.example.demo.models.Admin;
import com.example.demo.models.Cliente;
import com.example.demo.models.Funcionario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import com.example.demo.repositories.ClientesRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class ClientesController {
    @Autowired
    private ClientesRepository clientesRepository;

    // Especificando uma rota para evitar ambiguidade
    @GetMapping("/api/clientes")
    public List<Cliente> getAllClientes() {
        return clientesRepository.findAll();
    }

    // Endpoint para exibir a página de clientes
    @GetMapping("/clientes")
    public String index(Model model, HttpSession session) {
        List<Cliente> clientes = clientesRepository.findAll();
        model.addAttribute("clientes", clientes);

        // Verificar se há um usuário na sessão
        String tipoUsuario = "VISITANTE"; // Valor padrão, caso não haja usuário logado
        
        Object usuario = session.getAttribute("usuarioLogado"); // "usuarioLogado" é o nome do atributo na sessão
        
        if (usuario != null) {
            tipoUsuario = getTipoUsuario(usuario); // Aqui você deve implementar a lógica para determinar o tipo de usuário
        }
        
        model.addAttribute("tipoUsuario", tipoUsuario); // Passa o tipo de usuário para o modelo
        
        return "clientes/index"; // Nome da view
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
    @GetMapping("/clientes/novo")
    public String novoCliente(Model model) {
        model.addAttribute("cliente", new Cliente()); // Adiciona um objeto vazio para ser preenchido no                                                             // formulário
        return "clientes/novo"; // Retorna o template correto
    }

    @PostMapping("/clientes/salvar")
    public String salvarCliente(@ModelAttribute Cliente cliente) { // Define a data de cadastro como a data                                                                            // atual
        clientesRepository.save(cliente); // Salva o produto no banco de dados
        return "redirect:/clientes"; // Redireciona para a página de listagem de produtos
    }

    @GetMapping("/clientes/editar/{id}")
    public String editarClientes(@PathVariable("id") int id, Model model) {
        Cliente cliente = clientesRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Cliente inválido: " + id));
        model.addAttribute("cliente", cliente);  // Certifique-se de usar "funcionario" aqui
        return "clientes/editar"; // O template está correto
}
    
    @PostMapping("/clientes/atualizar/{id}")
    public String atualizarCliente(@PathVariable("id") int id, @ModelAttribute Cliente cliente) {
        cliente.setCliente_id(id); // Garante que estamos atualizando o produto correto
        clientesRepository.save(cliente); // Atualiza o produto
        return "redirect:/clientes"; // Redireciona para a lista de produtos
    }

    @GetMapping("/clientes/excluir/{id}")
    public String excluirClientes(@PathVariable("id") int id) {
        Cliente cliente = clientesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("cliente inválido: " + id));
                clientesRepository.delete(cliente);
        return "redirect:/clientes"; // Redireciona para a lista de produtos
    }


}
