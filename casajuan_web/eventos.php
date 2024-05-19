<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <!--<link rel="stylesheet" href="css/eventos.css">-->
    
    <title>Eventos</title>
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
    
    <div class="container-eventos">
    <?php
    $servidor = "localhost";
    $usuario = "root";
    $contrasena = "";
    $basedatos = "casajuan";

    $conn = new mysqli($servidor, $usuario, $contrasena, $basedatos);

    if ($conn->connect_error) {
        die("Conexión fallida: " . $conn->connect_error);
    }

    
    $sql = "SELECT id, titulo, descripcion, fecha_evento FROM eventos";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        
        while($row = $result->fetch_assoc()) {
            echo '<div class="evento" style="background-color: white; padding: 20px; margin: 20px auto; max-width: 800px; border: 1px solid #ddd; border-radius: 8px;">';
            echo '<h2 style="margin-top: 0;">' . $row["titulo"] . '</h2>';
            echo '<p style="margin-bottom: 15px; margin-top: 15px;">' . substr($row["descripcion"], 0, 100) . '...</p>';
            echo '<p style="margin-bottom: 10px;"><strong>Fecha del evento: </strong>' . $row["fecha_evento"] . '</p>';
            echo '<a href="evento_detalle.php?id=' . $row["id"] . '" class="btn-leer-mas" style="display: inline-block; padding: 10px; background-color: #007BFF; color: white; text-decoration: none; border-radius: 5px; transition: background-color 0.3s ease;">Leer más</a>';
            echo '</div>';
        }
    } else {
        echo "No hay eventos disponibles :(";
    }
    $conn->close();
    ?>
</div>
</body>
</html>
