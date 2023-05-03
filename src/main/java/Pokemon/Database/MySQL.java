package Pokemon.Database;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;

import Pokemon.Entrenador.Entrenador;

public class MySQL {

    private static Connection conexion;
    public static boolean login;
    public static boolean registrado;



    public static void todoEntrenador() {

        try {

            // Crear conexión con la base de datos

            Connection db = DriverManager.getConnection("jdbc:mysql://localhost:3306/pokemon", "root", "");


        } catch (Exception e) {

            System.out.println("Error al conectar.");

            e.printStackTrace();

        }

    }

    public static void login(String usuarioIngresado, String passIngresada) {

        try {

            Connection db = DriverManager.getConnection("jdbc:mysql://localhost:3306/pokemon", "root", "");

            String sql = "SELECT nombre, pass FROM entrenador WHERE nombre = ?";

            PreparedStatement statement = db.prepareStatement(sql);
            statement.setString(1, usuarioIngresado);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String usuario = resultSet.getString("nombre");
                String pass = resultSet.getString("pass");

                if (pass.equals(passIngresada)) {
                    System.out.println("Acceso permitido");
                    Entrenador usuarioLogueado = cargarEntrenador(usuario);
                    login = true;
                } else {
                    System.out.println("Usuario o contraseña incorrectos.");
                    login = false;
                }
            } else {
                System.out.println("Usuario o contraseña incorrectos.");
                login = false;
            }

            resultSet.close();
            statement.close();
            db.close();

        } catch (Exception e) {
            System.out.println("Error al conectar.");

            e.printStackTrace();
        }

    }

    public static Entrenador cargarEntrenador(String nombre) {
        try {
            Connection db = DriverManager.getConnection("jdbc:mysql://localhost:3306/pokemon", "root", "");

            String sql = "SELECT * FROM entrenador WHERE nombre = ?";

            PreparedStatement statement = db.prepareStatement(sql);
            statement.setString(1, nombre);

            ResultSet resultSet = statement.executeQuery();

            Entrenador entrenador = null;

            if (resultSet.next()) {
                // Obtener todos los detalles del usuario de la base de datos
                String contrasena = resultSet.getString("pass");
                int dinero = resultSet.getInt("dinero");

                // Crear un objeto Entrenador con los detalles cargados
                entrenador = new Entrenador(nombre, dinero, new HashMap<>(), new HashMap<>(), contrasena, new LinkedList<>(), new LinkedList<>(), new LinkedList<>());
            }

            resultSet.close();
            statement.close();
            db.close();

            return entrenador;
        } catch (Exception e) {
            System.out.println("Error al cargar el entrenador.");
            e.printStackTrace();
            return null;
        }
    }

    public static void register(String nombre, String pass) {

        try {
            Connection db = DriverManager.getConnection("jdbc:mysql://localhost:3306/pokemon", "root", "");
            String sql = "SELECT MAX(id_entrenador) FROM entrenador";
            PreparedStatement statement = db.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            int ultimaId = 0;

            if(rs.next()) {
                ultimaId = rs.getInt(1);
            }

            int nuevaId = ultimaId + 1;

            sql = "SELECT COUNT(*) FROM entrenador WHERE id_entrenador = ?";
            PreparedStatement preparedStatement = db.prepareStatement(sql);
            preparedStatement.setInt(1, nuevaId);
            rs = preparedStatement.executeQuery();
            int numUsuarios = 0;

            if(rs.next()) {
                numUsuarios = rs.getInt(1);
            }
            if(numUsuarios > 0) {
                System.out.println("Ya existe un usuario registrado con ese ID.");
                return;
            }

            sql = "INSERT INTO entrenador (id_entrenador, nombre, dinero, pass) VALUES (?,?,?,?)";
            preparedStatement = db.prepareStatement(sql);
            preparedStatement.setInt(1, nuevaId);
            preparedStatement.setString(2, nombre);
            preparedStatement.setInt(3, 500);
            preparedStatement.setString(4, pass);
            int filasInsertadasEntrenador = preparedStatement.executeUpdate();

            sql = "INSERT INTO mochila (id_mochila, id_entrenador, objeto1, objeto2, objeto3, objeto4, objeto5, objeto6) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = db.prepareStatement(sql);
            preparedStatement.setInt(1, nuevaId);
            preparedStatement.setInt(2, nuevaId);
            preparedStatement.setNull(3, Types.INTEGER);
            preparedStatement.setNull(4, Types.INTEGER);
            preparedStatement.setNull(5, Types.INTEGER);
            preparedStatement.setNull(6, Types.INTEGER);
            preparedStatement.setNull(7, Types.INTEGER);
            preparedStatement.setNull(8, Types.INTEGER);
            int filasInsertadasMochila = preparedStatement.executeUpdate();

            if(filasInsertadasEntrenador > 0 && filasInsertadasMochila > 0) {
                System.out.println("Registrado correctamente.");
                registrado = true;
            } else {
                System.out.println("Error en el registro.");
                registrado = false;
            }

            db.close();

        } catch (Exception e) {
            System.out.println("Error al conectar.");
            e.printStackTrace();
        }


    }

}
