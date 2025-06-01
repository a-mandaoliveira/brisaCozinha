<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<%
    HttpSession session1 = request.getSession(false);

    if (session1 != null && session1.getAttribute("usuarioEmail") != null) {
        String emailUsuario = (String) session1.getAttribute("usuarioEmail");
        String tipoUsuario = (String) session1.getAttribute("tipoUsuario");

        if ("admin".equals(tipoUsuario)) {
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
    <title>Página de Reservas</title>
    <script src="javaScript/jQuery.js"></script>
    <script src="javaScript/myReservations.js"></script>
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
            text-align: center;
        }

        .container {
            width: 80%;
            margin: 2rem auto;
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

        .btn-cancelar {
            background-color: #f44336;
            color: white;
        }

        .btn-cancelar:hover {
            background-color: #e53935;
        }

        .btn-editar {
            background-color: #007bff;
            color: white;
        }

        .btn-editar:hover {
            background-color: #0056b3;
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
    Reservas de Mesas
  </header>

    <div class="container">
        <table>
            <thead>
                <tr>
                    <th>Data da Reserva</th>
                    <th>Hora da Reserva</th>
                    <th>Número de Pessoas</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody id="tabelaDeReservas">
            </tbody>
        </table>
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
        function abrirExcluir(id) {
            // Preencher os campos com os dados da linha da tabela
            document.getElementById('idExcluir').value = id;

            // Exibir o modal
            document.getElementById('modal-excluir').style.display = 'block';
        }

        function fecharModal() {
            document.getElementById('modal-excluir').style.display = 'none';
        }

        window.onclick = function(event) {
            if (event.target === document.getElementById('modal-excluir')) {
                fecharModal();
            }
        };
    </script>

</body>
</html>
