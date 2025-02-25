// Espera o DOM estar completamente carregado
document.addEventListener('DOMContentLoaded', function() {
    // Dados para o gráfico de produtos
    var produtosData = {
        labels: ['Produto A', 'Produto B', 'Produto C', 'Produto D'],
        datasets: [{
            label: 'Quantidade de Produtos',
            data: [10, 25, 5, 8], // Dados fictícios
            backgroundColor: ['#3498db', '#e74c3c', '#2ecc71', '#f39c12'],
            borderColor: ['#2980b9', '#c0392b', '#27ae60', '#f39c12'],
            borderWidth: 1
        }]
    };

    // Opções para o gráfico
    var chartOptions = {
        responsive: true,
        scales: {
            y: {
                beginAtZero: true
            }
        }
    };

    // Criando o gráfico para produtos
    var ctxProdutos = document.getElementById('produtosChart').getContext('2d');
    new Chart(ctxProdutos, {
        type: 'bar', // Tipo do gráfico
        data: produtosData,
        options: chartOptions
    });

    // Dados para o gráfico de clientes
    var clientesData = {
        labels: ['Cliente A', 'Cliente B', 'Cliente C', 'Cliente D'],
        datasets: [{
            label: 'Quantidade de Clientes',
            data: [15, 30, 8, 12], // Dados fictícios
            backgroundColor: ['#9b59b6', '#16a085', '#f1c40f', '#e74c3c'],
            borderColor: ['#8e44ad', '#1abc9c', '#f39c12', '#c0392b'],
            borderWidth: 1
        }]
    };

    // Criando o gráfico para clientes
    var ctxClientes = document.getElementById('clientesChart').getContext('2d');
    new Chart(ctxClientes, {
        type: 'bar',
        data: clientesData,
        options: chartOptions
    });

    // Dados para o gráfico de funcionários
    var funcionariosData = {
        labels: ['Funcionário A', 'Funcionário B', 'Funcionário C', 'Funcionário D'],
        datasets: [{
            label: 'Quantidade de Funcionários',
            data: [8, 15, 10, 5], // Dados fictícios
            backgroundColor: ['#2ecc71', '#f39c12', '#3498db', '#e74c3c'],
            borderColor: ['#27ae60', '#f39c12', '#2980b9', '#c0392b'],
            borderWidth: 1
        }]
    };

    // Criando o gráfico para funcionários
    var ctxFuncionarios = document.getElementById('funcionariosChart').getContext('2d');
    new Chart(ctxFuncionarios, {
        type: 'bar',
        data: funcionariosData,
        options: chartOptions
    });
});

document.addEventListener('DOMContentLoaded', function() {
    // Dados para o gráfico de pizza
    var pizzaData = {
        labels: ['Categoria 1', 'Categoria 2', 'Categoria 3', 'Categoria 4'],
        datasets: [{
            label: 'Distribuição de Categorias',
            data: [25, 35, 20, 20], // Dados fictícios
            backgroundColor: ['#3498db', '#e74c3c', '#2ecc71', '#f39c12'],
            borderColor: ['#2980b9', '#c0392b', '#27ae60', '#f39c12'],
            borderWidth: 1
        }]
    };

    // Opções para o gráfico (opcional)
    var pizzaOptions = {
        responsive: true,
        plugins: {
            legend: {
                position: 'top'
            }
        }
    };

    // Criando o gráfico de pizza
    var ctxPizza = document.getElementById('pizzaChart').getContext('2d');
    new Chart(ctxPizza, {
        type: 'pie', // Tipo de gráfico
        data: pizzaData,
        options: pizzaOptions
    });
});
