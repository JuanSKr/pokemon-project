package Pokemon.Database;

import Pokemon.Combate.Ataque;
import Pokemon.Combate.Estado;
import Pokemon.Combate.Mejora;
import Pokemon.Combate.Movimiento;
import Pokemon.Entrenador.Entrenador;
import Pokemon.Funcionalidad.Funcion;
import Pokemon.Pokemon.Pokemon;
import Pokemon.Pokemon.Tipo;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;

public class PokemonCRUD {

    public static boolean login;
    public static boolean registrado;

    public static void login(String usuarioIngresado, String passIngresada) {
        try {
            Connection db = MySQL.getConexion();
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
                    login = false;
                }
            } else {
                login = false;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * POR DOCUMENTAR
     *
     * @param nombre Nombre de usuario
     * @return
     */

    public static Entrenador cargarEntrenador(String nombre) {
        try {
            Connection db = MySQL.getConexion();
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

            return entrenador;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Primero consulta la ID máxima que puede tener un entrenador en la base de datos, para no repetir IDs.
     * Después se crea un nuevo entrenador con el nombre y la contraseña elegida y una nueva mochila asociada el entrenador.
     *
     * @param nombre Nombre de usuario
     * @param pass   Contraseña
     */

    public static void register(String nombre, String pass) {

        try {
            Connection db = MySQL.getConexion();
            String sql = "SELECT MAX(id_entrenador) FROM entrenador";
            PreparedStatement statement = db.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            int ultimaId = 0;

            if (rs.next()) {
                ultimaId = rs.getInt(1);
            }

            int nuevaId = ultimaId + 1;

            sql = "SELECT COUNT(*) FROM entrenador WHERE id_entrenador = ?";
            PreparedStatement preparedStatement = db.prepareStatement(sql);
            preparedStatement.setInt(1, nuevaId);
            rs = preparedStatement.executeQuery();
            int numUsuarios = 0;

            if (rs.next()) {
                numUsuarios = rs.getInt(1);
            }
            if (numUsuarios > 0) {
                return;
            }

            sql = "INSERT INTO entrenador (id_entrenador, nombre, dinero, pass) VALUES (?,?,?,?)";
            preparedStatement = db.prepareStatement(sql);
            preparedStatement.setInt(1, nuevaId);
            preparedStatement.setString(2, nombre);
            preparedStatement.setInt(3, 500);
            preparedStatement.setString(4, pass);
            int filasInsertadasEntrenador = preparedStatement.executeUpdate();

            sql = "INSERT INTO mochila (id_mochila, id_entrenador, id_objeto, cantidad) " +
                    "VALUES (?, ?, ?, ?)";
            preparedStatement = db.prepareStatement(sql);
            preparedStatement.setInt(1, nuevaId);
            preparedStatement.setInt(2, nuevaId);
            preparedStatement.setNull(3, Types.INTEGER);
            preparedStatement.setNull(4, Types.INTEGER);

            int filasInsertadasMochila = preparedStatement.executeUpdate();

            if (filasInsertadasEntrenador > 0 && filasInsertadasMochila > 0) {
                registrado = true;
            } else {
                registrado = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Mediante un random del 1 al 149 consulta un Pokemon en la base de datos con la ID que salga en el random.
     *
     * @return Pokemon random
     */

    public static Pokemon generarPokemon() {
        try {
            Connection db = MySQL.getConexion();
            String sql = "SELECT * FROM pokedex WHERE id_pokedex = ?";
            PreparedStatement preparedStatement = db.prepareStatement(sql);

            int idRandom = Funcion.random(1, 149);

            preparedStatement.setInt(1, idRandom);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Pokemon pokemon = new Pokemon();
                pokemon.setFoto(resultSet.getString("foto"));
                pokemon.setNombre(resultSet.getString("nombre"));
                pokemon.setVitalidad(resultSet.getInt("vitalidad"));
                pokemon.setVelocidad(resultSet.getInt("velocidad"));
                pokemon.setEstamina(resultSet.getInt("estamina"));
                pokemon.setAtaque(resultSet.getInt("ataque"));
                pokemon.setDefensa(resultSet.getInt("defensa"));
                pokemon.setAtaqueEspecial(resultSet.getInt("ataque_especial"));
                pokemon.setDefensaEspecial(resultSet.getInt("defensa_especial"));
                pokemon.setTipo1(Tipo.valueOf(resultSet.getString("tipo1").toUpperCase()));
                String tipo2 = resultSet.getString("tipo2");
                if (tipo2 != null) {
                    pokemon.setTipo2(Tipo.valueOf(tipo2.toUpperCase()));
                }

                return pokemon;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Mediante un random del 1 al 50, consulta un Ataque en la base de datos con la ID que salga en el random.
     *
     * @return Ataque random
     */

    public static Ataque generarAtaque() {
        try {
            Connection db = MySQL.getConexion();
            String sql = "SELECT * FROM movimiento WHERE id_movimiento = ? AND tipo_movimiento = 'Ataque'";
            PreparedStatement preparedStatement = db.prepareStatement(sql);
            int idRandom = Funcion.random(1, 50);

            preparedStatement.setInt(1, idRandom);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Ataque ataque = new Ataque();
                ataque.setNombreMovimiento(resultSet.getString("nombre"));
                ataque.setPotencia(resultSet.getInt("potencia"));
                ataque.setTipo(Tipo.valueOf(resultSet.getString("tipo_ataque").toUpperCase()));

                return ataque;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Mediante un random del 61 al 84, consulta un Estado en la base de datos con la ID que salga en el random.
     *
     * @return Estado random
     */

    public static Estado generarEstado() {
        try {
            Connection db = MySQL.getConexion();
            String sql = "SELECT * FROM movimiento WHERE id_movimiento = ? AND tipo_movimiento = 'Estado'";
            PreparedStatement preparedStatement = db.prepareStatement(sql);

            int idRandom = Funcion.random(61, 84);

            preparedStatement.setInt(1, idRandom);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Estado estado = new Estado();
                estado.setNombreMovimiento(resultSet.getString("nombre"));
                estado.setTurnos(resultSet.getInt("turno"));
                estado.setTipo(Tipo.valueOf(resultSet.getString("tipo_ataque").toUpperCase()));

                return estado;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Mediante un random del 51 al 60, consulta una Mejora en la base de datos con la ID que salga en el random.
     *
     * @return Mejora random
     */

    public static Mejora generarMejora() {
        try {
            Connection db = MySQL.getConexion();
            String sql = "SELECT * FROM movimiento WHERE id_movimiento = ? AND tipo_movimiento = 'Mejora'";
            PreparedStatement preparedStatement = db.prepareStatement(sql);
            int idRandom = Funcion.random(51, 60);

            preparedStatement.setInt(1, idRandom);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Mejora mejora = new Mejora();
                mejora.setNombreMovimiento(resultSet.getString("nombre"));
                mejora.setTurnos(resultSet.getInt("turno"));
                mejora.setTipo(Tipo.valueOf(resultSet.getString("tipo_ataque").toUpperCase()));

                return mejora;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Genera un número del 1 al 3. Dependiendo del número asigna un movimiento de tipo Ataque, Estado o Mejora.
     *
     * @return Movimiento random
     */

    public static Movimiento generarMovimiento() {

        Movimiento movimiento;
        int num = Funcion.random(1, 3);

        if (num == 1) {
            movimiento = generarAtaque();
        } else if (num == 2) {
            movimiento = generarEstado();
        } else {
            movimiento = generarMejora();
        }
        return movimiento;
    }


    public static void main(String[] args) {

        Pokemon pokemon = generarPokemon();

        pokemon.setMovimiento1(generarMovimiento());
        pokemon.setMovimiento2(generarMovimiento());
        pokemon.setMovimiento3(generarMovimiento());
        pokemon.setMovimiento4(generarMovimiento());

        System.out.println(pokemon.getMovimiento1());
        System.out.println(pokemon.getMovimiento2());
        System.out.println(pokemon.getMovimiento3());
        System.out.println(pokemon.getMovimiento4());




    }

}


