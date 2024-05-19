<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reserva - Bar Cerveceria Casa Juan</title>
    <link rel="stylesheet" href="css/contacto.css">
</head>
<body>
    <!-- Menú de Navegación -->
<nav class="menu-navegacion">
    <ul>
        <li class="logo"><a href="index.html"><img src="img/logo.png" alt="Bar Cerveceria Casa Juan"></a></li>
        <li><a href="index.html#nuestros-restaurantes">Nuestros Restaurantes</a></li>
        <li><a href="reserva.php">Reservas</a></li>
        <li><a href="eventos.php">Eventos</a></li>
        <li><a href="contacto.php">Contáctanos</a></li>
    </ul>
</nav>

    <div class="container">
        <div class="contact-form">
            <h1>Contáctenos</h1>
            <form action="guardar_contacto.php" method="POST">
                <div class="form-group">
                    <label for="asunto">Asunto:</label>
                    <select id="asunto" name="asunto">
                        <option value="Servicio al cliente">Servicio al cliente</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="correo">Correo electrónico:</label>
                    <input type="email" id="correo" name="correo" required>
                </div>
                <div class="form-group">
                    <label for="mensaje">Mensaje:</label>
                    <textarea id="mensaje" name="mensaje" rows="5" required></textarea>
                </div>
                <div class="form-group">
                    <input type="submit" value="Enviar">
                </div>
            </form>
        </div>
    </div>
    <footer class="footer" style="text-align: center; padding: 10px; background-color: #333; color: #fff; margin-top: auto;">
    <div class="direccion">
        <p>Dirección Casa Juan: CM-310, 70, 45800 Quintanar de la Orden, Toledo</p>
        <p>Dirección Casa Juan Villanueva: Av. Deporte, 30, 45810 Villanueva de Alcardete, Toledo</p>
    </div>
    <p>Contacto: contacto@casajuan.com | Móvil: 584107259</p>
</footer>

</body>
</html>
