<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reserva - Bar Cerveceria Casa Juan</title>
    <link rel="stylesheet" href="css/reserva.css">
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
        <div class="left-section">
            <div class="card">
                <h2>Condiciones de reserva</h2>
                <p>Para garantizar una experiencia óptima en <strong>Casa Juan</strong>, <strong>recomendamos</strong> realizar reservas con anticipación. <strong>Nuestro</strong> equipo estará encantado de recibirte y asegurar tu mesa para que disfrutes de nuestro ambiente acogedor y de nuestra deliciosa gastronomía. Por favor, ten en cuenta que las reservas están sujetas a disponibilidad y <strong>podrán</strong> ser confirmadas por nuestro personal. ¡Esperamos verte pronto en <strong>Casa Juan</strong>!</p>
            </div>
            <div class="opening-hours">
                <h2>Horario Casa Juan</h2>
                <p><strong>TODOS LOS DÍAS:</strong><br>13:30 - 15:30</p>
                <p><strong>LUNES A JUEVES:</strong><br>20:00 - 23:30</p>
                <p><strong>VIERNES A DOMINGO:</strong><br>20:00 - 00:00</p>
            </div>
            <div class="card">
                <h2>Horario Casa Juan Villanueva</h2>
                <p><strong>TODOS LOS DÍAS:</strong><br>13:30 - 15:30</p>
                <p><strong>LUNES A JUEVES:</strong><br>20:00 - 23:30</p>
                <p><strong>VIERNES A DOMINGO:</strong><br>20:00 - 00:00</p>
            </div>
        </div>
        <div class="right-section">
            <form action="guardar_reserva.php" method="POST" class="reservation-form">
                <h1>Reserva</h1>
                <div class="form-row">
                    <div class="form-group">
                        <label for="nombre">Nombre:</label>
                        <input type="text" id="nombre" name="nombre" required>
                    </div>
                    <div class="form-group">
                        <label for="apellido">Apellido:</label>
                        <input type="text" id="apellido" name="apellido" required>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group">
                        <label for="correo">Correo electrónico:</label>
                        <input type="email" id="correo" name="correo" required>
                    </div>
                    <div class="form-group">
                        <label for="telefono">Teléfono:</label>
                        <input type="text" id="telefono" name="telefono" required>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group">
                        <label for="numero_personas">Número de Personas:</label>
                        <input type="number" id="numero_personas" name="numero_personas" required>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group">
                        <label for="fecha_reserva">Fecha de Reserva:</label>
                        <input type="date" id="fecha_reserva" name="fecha_reserva" required>
                    </div>
                    <div class="form-group">
                        <label for="hora_reserva">Hora de Reserva:</label>
                        <select id="hora_reserva" name="hora_reserva" required>
                            <option value="10:00">10:00</option>
                            <option value="10:30">10:30</option>
                            <option value="11:00">11:00</option>
                            <option value="11:30">11:30</option>
                            <option value="12:00">12:00</option>
                            <option value="12:30">12:30</option>
                            <option value="13:00">13:00</option>
                            <option value="13:30">13:30</option>
                            <option value="14:00">14:00</option>
                            <option value="14:30">14:30</option>
                            <option value="15:00">15:00</option>
                            <option value="15:30">15:30</option>
                            <option value="16:00">16:00</option>
                            <option value="16:30">16:30</option>
                            <option value="17:00">17:00</option>
                            <option value="17:30">17:30</option>
                            <option value="18:00">18:00</option>
                            <option value="18:30">18:30</option>
                            <option value="19:00">19:00</option>
                            <option value="19:30">19:30</option>
                            <option value="20:00">20:00</option>
                            <option value="20:30">20:30</option>
                            <option value="21:00">21:00</option>
                            <option value="21:30">21:30</option>
                            <option value="22:00">22:00</option>
                            <option value="22:30">22:30</option>
                            <option value="23:00">23:00</option>
                            <option value="23:30">23:30</option>
                            <option value="00:00">00:00</option>
                        </select>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group">
                        <label for="preferencia_ubicacion">Preferencia de Ubicación:</label>
                        <select id="preferencia_ubicacion" name="preferencia_ubicacion" required>
                            <option value="terraza">Terraza</option>
                            <option value="salon">Salón</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="restaurante">Restaurante:</label>
                        <select id="restaurante" name="restaurante" required>
                            <option value="Casa Juan" <?php if(isset($_GET['restaurante']) && $_GET['restaurante'] == 'Casa%20Juan') echo 'selected'; ?>>Casa Juan</option>
                            <option value="Casa Juan Villanueva" <?php if(isset($_GET['restaurante']) && $_GET['restaurante'] == 'Casa%20Juan%20Villanueva') echo 'selected'; ?>>Casa Juan Villanueva</option>
                        </select><br><br>
                    </div>
                </div>
                <input type="submit" value="Reservar">
            </form>
        </div>
    </div>
    <!-- Footer -->
    <footer class="footer" style="text-align: center; padding: 10px; background-color: #333; color: #fff; margin-top: auto;">
    <div class="direccion">
        <p>Dirección Casa Juan: CM-310, 70, 45800 Quintanar de la Orden, Toledo</p>
        <p>Dirección Casa Juan Villanueva: Av. Deporte, 30, 45810 Villanueva de Alcardete, Toledo</p>
    </div>
    <p>Contacto: contacto@casajuan.com | Móvil: 584107259</p>
</footer>
</body>
</html>
