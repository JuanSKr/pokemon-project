package Pokemon.Main;

import java.sql.*;

import Pokemon.Entrenador.Entrenador;
import Pokemon.Entrenador.Entrenador.*;
import Pokemon.Menus.Login.*;

public class Main {

    private static Connection conexion;
    public static boolean login;
    public static boolean registrado;

    public static void todoEntrenador() {

        try {

            // Crear conexión con la base de datos

            Connection db = DriverManager.getConnection("jdbc:mysql://localhost:3306/pokemon", "root", "");

            // Crear objeto statement

            Statement statement = db.createStatement();

            // Ejecutar SQL

            ResultSet rs = statement.executeQuery("SELECT * FROM entrenador");

            // Recorrer el ResultSet


            while (rs.next()) {
                System.out.println("ID: " + rs.getString("id_entrenador") + " Nombre: " + rs.getString("nombre") + " Dinero: " + rs.getDouble("dinero")
                        + " pass " + rs.getString("pass"));
            }

            rs.close(); //Cerrar siempre


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

    public static Entrenador obtenerEntrenador(String usuario, String pass) throws SQLException {
        Connection db = DriverManager.getConnection("jdbc:mysql://localhost:3306/pokemon", "root", "");
        String sql = "SELECT * FROM entrenador WHERE nombre = ? AND pass = ?";
        PreparedStatement statement = db.prepareStatement(sql);
        statement.setString(1, usuario);
        statement.setString(2, pass);
        ResultSet resultSet = statement.executeQuery();
        Entrenador entrenador = null;
        if (resultSet.next()) {
            String nombre = resultSet.getString("nombre");
            int dinero = resultSet.getInt("dinero");
            entrenador = new Entrenador();
            Entrenador.setNombre(nombre);
            Entrenador.setDinero(dinero);

        }
        resultSet.close();
        statement.close();
        db.close();
        return entrenador;
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

            sql = "INSERT INTO entrenador (id_entrenador, nombre, dinero, pass, id_mochila) VALUES (?,?,?,?,?)";
            preparedStatement = db.prepareStatement(sql);
            preparedStatement.setInt(1, nuevaId);
            preparedStatement.setString(2, nombre);
            preparedStatement.setInt(3, 500);
            preparedStatement.setString(4, pass);
            preparedStatement.setNull(5, Types.INTEGER);
            int filasInsertadas = preparedStatement.executeUpdate();

            if(filasInsertadas > 0) {
                System.out.println("Registrado correctamente.");
                registrado = true;
            } else {
                System.out.println("error en el registro.");
                registrado = false;
            }

            db.close();

        } catch (Exception e) {
            System.out.println("Error al conectar.");
            e.printStackTrace();
        }

    }

}
