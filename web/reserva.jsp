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
  <title>Reservas - Brisa Cozinha</title>
  <script src="javaScript/jQuery.js"></script>
  <script src="javaScript/reservation.js"></script>
  <style>
    /* Reset básico */
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
    }

    body {
      font-family: 'Georgia', serif;
      background-color: #256B3D;
      color: #f5e9d4;
    }

    header {
      padding: 1rem 2rem;
      display: flex;
      justify-content: space-between;
      align-items: center;
    }

    header span {
      font-size: 1.5rem;
      font-weight: bold;
    }

    nav a {
      color: #f5e9d4;
      text-decoration: none;
      padding: 0.5rem 1rem;
      font-size: 1rem;
      letter-spacing: 0.05em;
    }

    main {
      padding: 3rem 2rem;
      display: flex;
      justify-content: center;
      align-items: center;
      flex-direction: column;
    }

    h1 {
      margin-bottom: 1rem;
      font-size: 2.5rem;
      text-align: center;
    }

    .reserva-form {
      background-color: #bbf8bb;
      padding: 2rem;
      border-radius: 10px;
      max-width: 600px;
      width: 100%;
      color: #256B3D;
    }

    .reserva-form label {
      display: block;
      margin-bottom: 0.5rem;
      font-weight: bold;
    }

    .reserva-form input,
    .reserva-form select,
    .reserva-form button {
      width: 100%;
      padding: 0.75rem;
      margin-bottom: 1rem;
      border-radius: 6px;
      border: 1px solid #ccc;
    }

    .reserva-form input[type="date"] {
      font-size: 1rem;
    }

    .reserva-form input[type="time"] {
      font-size: 1rem;
    }

    .reserva-form button {
      background-color:#256B3D ;
      color: #f5e9d4;
      font-size: 1.2rem;
      font-weight: bold;
      cursor: pointer;
      transition: background-color 0.3s ease;
      text-transform: uppercase;
    }

    .reserva-form button:hover {
      background-color: #16492D;
    }

    footer {
      background-color: #16492D;
      color: #f5e9d4;
      display: flex;
      justify-content: space-around;
      flex-wrap: wrap;
      gap: 2rem;
      padding: 2rem 2rem;
    }

    footer div {
      max-width: 300px;
    }

    footer h3 {
      font-weight: bold;
      margin-bottom: 1rem;
    }

    footer a {
      display: block;
      color: #f5e9d4;
      margin-top: 0.5rem;
    }

    /* Responsividade */
    @media (max-width: 768px) {
      header {
        padding: 1rem;
      }

      main {
        padding: 2rem;
      }

      .reserva-form {
        padding: 1.5rem;
      }
    }

    
      #logout-button {
        background-color: red;
        color: white;
        font-size: 16px;
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s ease;
      }

      #logout-button:hover {
        background-color: darkred;
      }

  </style>
</head>
<body>

  <!-- Header com links para outras páginas -->
  <header>
    <span>Brisa Cozinha</span>
    <nav>
      <a href="index.html">Início</a>
      <a href="cardapio.html">Cardápio</a>
      <a href="reserva.html">Reservas</a>
      <a href="tabelasMesa.jsp">Gerenciar Tabela</a>
      <button id="logout-button">Logout</button>
    </nav>
  </header>

  <!-- Seção de reserva -->
  <main>
    <h1>Faça sua Reserva</h1>

    <form class="reserva-form" id="reserva-form" action="#" method="POST">
        <label for="data">Data da Reserva:</label>
        <input type="date" id="data" name="data" required />

        <label for="hora">Hora da Reserva:</label>
        <input type="time" id="hora" name="hora" required />

        <label for="pessoas">Número de Pessoas:</label>
        <select id="pessoas" name="pessoas" required>
            <option value="2">2 Pessoas</option>
            <option value="3">3 Pessoas</option>
            <option value="4">4 Pessoas</option>
            <option value="5">5 Pessoas</option>
            <option value="6">6 Pessoas</option>
            <option value="7">7 Pessoas</option>
            <option value="8">8 Pessoas</option>
        </select>
        
        <label for="mesas">Número da mesa:</label>
        <select id="mesas" name="mesas" required>
        </select>

        <button type="submit">Reservar Agora</button>
    </form>
  </main>

  <footer>
    <div>
      <h3>Contato</h3>
      <address>
        Rua Principal, 123<br />
        (12) 2465-79600<br />
        contato@brisa-cozinha.com
      </address>
    </div>
    <div>
      <h3>Horário de Funcionamento</h3>
      <p>Seg-Sex: 12:00 – 23:00<br />Sab-Dom: 12:00 – 23:00</p>
    </div>
    <div>
      <h3>Redes Sociais</h3>
      <a href="#">Facebook</a>
      <a href="#">Instagram</a>
      <a href="#">Twitter</a>
    </div>
  </footer>
  <script src="javaScript/jQuery.js"></script>
  <script src="javaScript/logout.js"></script>
</body>
</html>

