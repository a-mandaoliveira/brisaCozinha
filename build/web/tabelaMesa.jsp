<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<%
    HttpSession session1 = request.getSession(false);

    if (session1 != null && session1.getAttribute("usuarioEmail") != null) {
        String emailUsuario = (String) session1.getAttribute("usuarioEmail");
        String tipoUsuario = (String) session1.getAttribute("tipoUsuario");

        if ("cliente".equals(tipoUsuario)) {
            response.sendRedirect(request.getContextPath() + "/index.html");
            return;
        }

    } else {
        response.sendRedirect(request.getContextPath() + "/login.html");
        return;
    }
%>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Tabela com Editar e Apagar</title>
    <script src="javaScript/jQuery.js"></script>
    <script src="javaScript/tableManager.js"></script>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Arial', sans-serif;
            background-color: #f5f5f5;
            color: #333;
        }

        header {
            padding: 1rem 2rem;
            background-color: #256B3D;
            color: #f5e9d4;
            font-size: 1.5rem;
            font-weight: bold;
            display: flex;
            justify-content: center; 
            align-items: center;
            position: relative;
        }

        header .btn-voltar {
            position: absolute;
            left: 2rem;
            background-color: #f44336;
            color: white;
            padding: 0.5rem 1.5rem;
            font-size: 1rem;
            font-weight: bold;
            border-radius: 6px;
            border: none;
            cursor: pointer;
            box-shadow: 0 5px 0 #e53935;
            transition: background-color 0.3s ease;
        }

        header .btn-voltar:hover {
            background-color: #e53935;
        }

        .container {
            width: 80%;
            margin: 2rem auto;
        }

        .btn-adicionar {
            background-color: #007bff;
            color: white;
            padding: 0.75rem 2rem;
            font-size: 1.1rem;
            font-weight: bold;
            border-radius: 6px;
            border: none;
            cursor: pointer;
            box-shadow: 0 5px 0 #0056b3;
            transition: background-color 0.3s ease;
            margin-bottom: 1rem;
        }

        .btn-adicionar:hover {
            background-color: #0056b3;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 2rem;
        }

        table, th, td {
            border: 1px solid #ccc;
        }

        th, td {
            padding: 12px;
            text-align: center;
        }

        th {
            background-color: #16492D;
            color: #f5e9d4;
            font-size: 1.1rem;
        }

        td {
            background-color: #fff;
            font-size: 1rem;
        }

        .btn {
            padding: 6px 12px;
            border-radius: 6px;
            cursor: pointer;
            font-size: 1rem;
            transition: background-color 0.3s ease;
            border: none;
        }

        .btn-editar {
            background-color: #4CAF50;
            color: white;
        }

        .btn-editar:hover {
            background-color: #45a049;
        }

        .btn-apagar {
            background-color: #f44336;
            color: white;
        }

        .btn-apagar:hover {
            background-color: #e53935;
        }

        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            overflow: auto;
            padding-top: 60px;
        }

        .modal-content {
            background-color: #fefefe;
            margin: 5% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
            max-width: 500px;
            border-radius: 8px;
        }

        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }

        .modal input {
            width: 100%;
            padding: 8px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 6px;
        }

        .modal button {
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            font-size: 1rem;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .modal button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

  <header>
    <button class="btn-voltar" onclick="window.history.back();">Voltar</button>
    Tabela: Mesas
  </header>

  <div class="container">
    <button class="btn-adicionar" onclick="abrirModalAdicionar()">Adicionar Mesa</button>

    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Numero da Mesa</th>
          <th>Lugares</th>
          <th>Status</th>
          <th>Preço</th>
          <th>Ações</th>
        </tr>
      </thead>
      <tbody id="tabelaDeMesas">
      </tbody>
    </table>
  </div>

    <!-- Modal de Adicionar Mesa -->
    <div id="modal-adicionar" class="modal">
        <div class="modal-content">
          <span class="close" onclick="fecharModal()">&times;</span>
          <h2>Adicionar Mesa</h2>
          <form id="form-adicionar">
            <label for="numeroADD">Número da Mesa:</label>
            <input type="text" id="numeroADD" name="numeroADD" required />
            
            <label for="lugaresADD">Número de Lugares:</label>
            <input type="number" id="lugaresADD" name="lugaresADD" min="1" required />
    
            <label for="statusADD">Status:</label>
            <input type="text" id="statusADD" name="statusADD" required />
    
            <label for="precoADD">Preço:</label>
            <input type="text" id="precoADD" name="precoADD" required />
    
            <button type="submit">Adicionar Mesa</button>
          </form>
        </div>
      </div>
    
  <!-- Modal de Edição -->
  <div id="modal-editar" class="modal">
    <div class="modal-content">
      <span class="close" onclick="fecharModal()">&times;</span>
      <h2>Editar Mesa</h2>
      <form id="form-editar">
          <input type="hidden" id="idMesa" name="idMesa">
        
        <label for="numero">Número da Mesa:</label>
        <input type="text" id="numero" name="numero" required />
        
        <label for="lugares">Número de Lugares:</label>
        <input type="number" id="lugares" name="lugares" min="1" required />

        <label for="status">Status:</label>
        <input type="text" id="status" name="status" required />

        <label for="preco">Preço:</label>
        <input type="text" id="preco" name="preco" required />

        <button type="submit">Salvar Alterações</button>
      </form>
    </div>
  </div>
  
  <div id="modal-excluir" class="modal">
    <div class="modal-content">
        <span class="close" onclick="fecharModal()">&times;</span>
        <h2>Excluir Mesa</h2>
        <p>Tem certeza que deseja excluir essa mesa?</p>
        <form id="form-excluir">
            <input type="hidden" id="idExcluir" name="idExcluir">
            <button type="submit" id="confirmar">Confirmar</button>
        </form>
        <button type="button" onclick="fecharModal()">Cancelar</button>
    </div>
  </div>

  <script>
    // Função para abrir o modal e preencher com os dados da reserva
    function abrirModal(id) {
        // Preencher os campos com os dados da linha da tabela
        document.getElementById('idMesa').value = id;
        document.getElementById('numero').value = document.getElementById('numero-'+id).innerText;
        document.getElementById('lugares').value = document.getElementById('lugares-'+id).innerText;
        document.getElementById('status').value = document.getElementById('status-'+id).innerText;
        document.getElementById('preco').value = document.getElementById('preco-'+id).innerText;

        // Exibir o modal
        document.getElementById('modal-editar').style.display = 'block';

        // Ao submeter o formulário, atualizar a tabela
        document.getElementById('form-editar').onsubmit = function (e) {
            e.preventDefault();
            document.getElementById('numero-'+id).innerText = document.getElementById('numero').value;
            document.getElementById('lugares-'+id).innerText = document.getElementById('lugares').value;
            document.getElementById('status-'+id).innerText = document.getElementById('status').value;
            document.getElementById('preco-'+id).innerText = document.getElementById('preco').value;
            fecharModal(); // Fecha o modal após salvar
        };
    }
    
    function abrirExcluir(id) {
      // Preencher os campos com os dados da linha da tabela
      document.getElementById('idExcluir').value = id;

      // Exibir o modal
      document.getElementById('modal-excluir').style.display = 'block';
    }
    
    // Função para fechar o modal
    function fecharModal() {
        document.getElementById('modal-editar').style.display = 'none';
        document.getElementById('modal-excluir').style.display = 'none';
        document.getElementById('modal-adicionar').style.display = 'none';
    }

    // Fecha o modal ao clicar fora do conteúdo
    window.onclick = function(event) {
      if (event.target === document.getElementById('modal-editar') || event.target === document.getElementById('modal-excluir') || event.target === document.getElementById('modal-adicionar')) {
        fecharModal();
      }
    };
    
    function abrirModalAdicionar() {
          document.getElementById('modal-adicionar').style.display = 'block';
        }   
    
        document.getElementById('form-adicionar').onsubmit = function (e) {
          e.preventDefault();
    
          const numero = document.getElementById('numero').value;
          const lugares = document.getElementById('lugares').value;
          const status = document.getElementById('status').value;
          const preco = document.getElementById('preco').value;
    
          const tabela = document.getElementById('tabelaDeMesas');
          const novaLinha = tabela.insertRow();
    
          novaLinha.innerHTML = `
            <td>${numero}</td>
            <td>${lugares}</td>
            <td>${status}</td>
            <td>${preco}</td>
          `;
    
          fecharModal();
        };
  </script>

</body>
</html>
