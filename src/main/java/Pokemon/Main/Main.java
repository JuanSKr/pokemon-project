package Pokemon.Main;

import java.sql.*;

import Pokemon.Entrenador.Entrenador;
import Pokemon.Entrenador.Entrenador.*;
import Pokemon.Menus.Login.*;

public class Main {

    private static Connection conexion;
    public static boolean login;

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

    public static void comprobarUsuario(String usuarioIngresado, String passIngresada) {

        try {

            Connection db = DriverManager.getConnection("jdbc:mysql://localhost:3306/pokemon", "root", "");

            String sqlUsuario = "SELECT nombre FROM entrenador";

            Statement statementUsuario = db.createStatement();
            ResultSet resultSetUsuario = statementUsuario.executeQuery(sqlUsuario);
            String usuario = "";
            if (resultSetUsuario.next()) {
                usuario = resultSetUsuario.getString("nombre");
            }

            String sqlContrasena = "SELECT pass FROM entrenador";

            Statement statementContrasena = db.createStatement();
            ResultSet resultSetContrasena = statementContrasena.executeQuery(sqlContrasena);
            String pass = "";
            if (resultSetContrasena.next()) {
                pass = resultSetContrasena.getString("pass");
            }

            if (usuario.equals(usuarioIngresado) && pass.equals(passIngresada)) {
                System.out.println("Acceso permitido");
                login = true;
            } else {
                System.out.println("Usuario o contraseña incorrectos.");
                login = false;
            }

            resultSetUsuario.close();
            statementUsuario.close();
            resultSetContrasena.close();
            statementContrasena.close();
            db.close();

        } catch (Exception e) {
            System.out.println("Error al conectar.");

            e.printStackTrace();
        }

    }

    public static Entrenador obtenerEntrenador(String usuario, String contrasena) throws SQLException {
        Connection db = DriverManager.getConnection("jdbc:mysql://localhost:3306/pokemon", "root", "");
        String sql = "SELECT * FROM entrenador WHERE nombre = ? AND pass = ?";
        PreparedStatement statement = db.prepareStatement(sql);
        statement.setString(1, usuario);
        statement.setString(2, contrasena);
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

    public static void main(String[] args) {
        comprobarUsuario("Juan", "test");
    }

}
