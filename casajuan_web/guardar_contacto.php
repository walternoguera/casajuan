<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/guardar_contacto.css">
    <title>Contacto</title>
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
        <div class="message">
            <?php
                if ($_SERVER["REQUEST_METHOD"] == "POST") {
                    $servidor = "localhost";
                    $usuario = "root";
                    $contrasena = "";
                    $basedatos = "casajuan";

                    $conn = new mysqli($servidor, $usuario, $contrasena, $basedatos);

                    if ($conn->connect_error) {
                        die("Conexión fallida: " . $conn->connect_error);
                    }

                    $asunto = $_POST['asunto'];
                    $correo = $_POST['correo'];
                    $mensaje = $_POST['mensaje'];

                    $sql = "INSERT INTO contacto (asunto, correo, comentario) VALUES (?, ?, ?)";

                    $stmt = $conn->prepare($sql);
                    $stmt->bind_param("sss", $asunto, $correo, $mensaje);

                    // Ejecutar la consulta y verificar si se insertaron los datos
                    if ($stmt->execute()) {
                        echo "Tu sugerencia se envio correctamente.";
                        echo "En breve nos pondremos en contacto contigo";
                    } else {
                        echo "Error: " . $sql . "<br>" . $conn->error;
                    }

                    // Cerrar la conexión
                    $stmt->close();
                    $conn->close();
                } else {
                    echo "No se recibieron datos del formulario.";
                }
            ?>
            <a href="index.html" class="return-link">Regresar a la página principal</a>
        </div>
    </div>
</body>
</html>
