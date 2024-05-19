<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/guardar_reserva.css">
    <title>Resultado de la reserva</title>
</head>
<body>
    <div class="container">
        <div class="message">
            <?php
            if ($_SERVER["REQUEST_METHOD"] == "POST") {

                $servidor = "localhost";
                $usuario = "root";
                $contrasena = "";
                $basedatos = "casajuan";

                $conexion = new mysqli($servidor, $usuario, $contrasena, $basedatos);

                if ($conexion->connect_error) {
                    die("Error de conexión: " . $conexion->connect_error);
                }

                $nombre = $_POST["nombre"];
                $apellido = $_POST["apellido"];
                $correo = $_POST["correo"];
                $telefono = $_POST["telefono"];
                $numero_personas = $_POST["numero_personas"];
                $fecha_reserva = $_POST["fecha_reserva"];
                $hora_reserva = $_POST["hora_reserva"];
                $preferencia_ubicacion = $_POST["preferencia_ubicacion"];
                $restaurante = $_POST["restaurante"];

                $sql = "INSERT INTO reservas (nombre, apellido, correo, telefono, numero_personas, fecha_reserva, hora_reserva, preferencia_ubicacion, restaurante) 
                        VALUES ('$nombre', '$apellido', '$correo', '$telefono', '$numero_personas', '$fecha_reserva', '$hora_reserva', '$preferencia_ubicacion', '$restaurante')";

                if ($conexion->query($sql) === TRUE) {
                    echo "<p>Reserva realizada correctamente</p>";
                } else {
                    echo "<p>Error al realizar la reserva: " . $conexion->error . "</p>";
                }
                $conexion->close();
            } else {
                echo "<p>No se han enviado datos del formulario</p>";
            }
            ?>
            <a href="index.html" class="return-link">Regresar a la página principal</a>
        </div>
    </div>
</body>
</html>
