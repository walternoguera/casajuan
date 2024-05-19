<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <title>Detalle del Evento</title>
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
    
    <div class="container" style="padding: 20px; max-width: 1000px; margin: 0 auto;">
    <a href="eventos.php" class="btn-regresar" style="display: block; margin-bottom: 20px; text-align: left;">Regresar a todos los eventos</a>
    <?php
    if (isset($_GET['id'])) {
        $servidor = "localhost";
        $usuario = "root";
        $contrasena = "";
        $basedatos = "casajuan";

        $conn = new mysqli($servidor, $usuario, $contrasena, $basedatos);

        if ($conn->connect_error) {
            die("Conexión fallida: " . $conn->connect_error);
        }

        $id = $_GET['id'];

        $sql = "SELECT titulo, descripcion, fecha_evento FROM eventos WHERE id = ?";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param("i", $id);
        $stmt->execute();
        $stmt->bind_result($titulo, $descripcion, $fecha_evento);
        $stmt->fetch();

        if ($titulo) {
            echo '<div style="margin-left: 20px;">';
            echo '<h1 style="margin-top: 0; margin-bottom: 20px;">' . $titulo . '</h1>';
            echo '<p style="margin-bottom: 10px;">' . $descripcion . '</p>';
            echo '<p style="margin-bottom: 10px;"><strong>Fecha del evento: </strong>' . $fecha_evento . '</p>';
            echo '</div>';
        } else {
            echo "Evento no encontrado.";
        }

        $stmt->close();
        $conn->close();
    } else {
        echo "ID de evento no especificado.";
    }
    ?>
</div>
</body>
</html>
