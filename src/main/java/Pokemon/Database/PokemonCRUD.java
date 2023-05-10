package Pokemon.Database;

import Pokemon.Combate.Movimientos.Ataque;
import Pokemon.Combate.Movimientos.Estado;
import Pokemon.Combate.Movimientos.Mejora;
import Pokemon.Combate.Movimientos.Movimiento;
import Pokemon.Entrenador.Entrenador;
import Pokemon.Entrenador.Mochila;
import Pokemon.Funcionalidad.Funcion;
import Pokemon.Pokemon.ListaEstados;
import Pokemon.Pokemon.Pokemon;
import Pokemon.Pokemon.Tipo;
import Pokemon.Tienda.Objeto;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static Pokemon.Entrenador.Entrenador.*;

public class PokemonCRUD {

    public static boolean login;
    public static boolean registrado;

    /**
     * Método que inserta el Pokemon que se le pasa por parámetro en la tabla Capturado.
     * @param pokemon
     */

    public static void createCapturado(Pokemon pokemon) {

        try {

            Connection db = MySQL.getConexion();
            String sql = "INSERT INTO capturado (id_capturado, nombre, mote, equipo, vitalidad, fertilidad, velocidad, estamina, ataque, defensa, ataque_especial," +
                    "defensa_especial, tipo1, tipo2, movimiento1, id_entrenador, id_pokedex, nivel, xp, foto, foto_espalda)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            int nuevaId = pokemon.getId() + Funcion.random(1, 9999);
            int equipo = Entrenador.addPokemon(pokemon);


            PreparedStatement preparedStatement = db.prepareStatement(sql);
            preparedStatement.setInt(1, nuevaId);
            preparedStatement.setString(2, pokemon.getNombre());
            preparedStatement.setString(3, pokemon.getMote());
            preparedStatement.setInt(4, equipo);
            preparedStatement.setDouble(5, pokemon.getVitalidad());
            preparedStatement.setInt(6, 5);
            preparedStatement.setDouble(7, pokemon.getVelocidad());
            preparedStatement.setDouble(8, pokemon.getEstamina());
            preparedStatement.setDouble(9, pokemon.getAtaque());
            preparedStatement.setDouble(10, pokemon.getDefensa());
            preparedStatement.setDouble(11, pokemon.getAtaqueEspecial());
            preparedStatement.setDouble(12, pokemon.getDefensaEspecial());
            preparedStatement.setString(13, String.valueOf(pokemon.getTipo1()));
            preparedStatement.setString(14, String.valueOf(pokemon.getTipo2()));
            preparedStatement.setInt(15, idMovimiento(generarMovimiento()));
            preparedStatement.setInt(16, idEntrenador()); //CAMBIAR METODO DESPUES DE TERMINAR PRUEBAS
            preparedStatement.setInt(17, pokemon.getId());
            preparedStatement.setInt(18, 1);
            preparedStatement.setInt(19, 0);
            preparedStatement.setString(20, pokemon.getFoto());
            preparedStatement.setString(21, pokemon.getFotoEspalda());

            preparedStatement.executeUpdate();

            System.out.println("Pokemon insertado correctamente.");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para obtener toda la información de un Pokemon en la base de datos.
     * Hay que pasarle la ID del Pokemon por parámetro para indicarle que Pokemon quieres.
     * @param id
     * @return Pokemon
     * @usage getPokemon(55);
     */

    public static Pokemon getPokemon(int id) {
        Pokemon pokemon = new Pokemon();
        try {
            Connection db = MySQL.getConexion();
            String sql = "SELECT * FROM capturado WHERE id_capturado = ?";
            PreparedStatement preparedStatement = db.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                pokemon.setId(rs.getInt("id_capturado"));
                pokemon.setNombre(rs.getString("nombre"));
                pokemon.setMote(rs.getString("mote"));
                pokemon.setVitalidad(rs.getDouble("vitalidad"));
                pokemon.setFertilidad(rs.getInt("fertilidad"));
                pokemon.setVelocidad(rs.getDouble("velocidad"));
                pokemon.setEstamina(rs.getDouble("estamina"));
                pokemon.setAtaque(rs.getDouble("ataque"));
                pokemon.setDefensa(rs.getDouble("defensa"));
                pokemon.setAtaqueEspecial(rs.getDouble("ataque_especial"));
                pokemon.setDefensaEspecial(rs.getDouble
                        ("defensa_especial"));
                pokemon.setTipo1(Tipo.valueOf(rs.getString("tipo1").toUpperCase()));
                String tipo2 = rs.getString("tipo2");
                if (tipo2 != null) { // Controlar si tipo2 es nulo o no.
                    try {
                        pokemon.setTipo2(Tipo.valueOf(tipo2.toUpperCase()));
                    } catch (IllegalArgumentException e) {

                    }
                }
                pokemon.setNivel(rs.getInt("nivel"));
                pokemon.setXp(rs.getInt("xp"));
                pokemon.setFoto(rs.getString("foto"));
                pokemon.setFotoEspalda(rs.getString("foto_espalda"));
                pokemon.setMovimiento1(selectMovimiento(rs.getInt("movimiento1")));
                pokemon.setMovimiento2(selectMovimiento(rs.getInt("movimiento2")));
                pokemon.setMovimiento3(selectMovimiento(rs.getInt("movimiento3")));
                pokemon.setMovimiento4(selectMovimiento(rs.getInt("movimiento4")));
                return pokemon;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pokemon;

    }

    /**
     * Método para rellenar la lista de equipo1 del Entrenador con los Pokemons de la tabla capturado.
     * @param equipo1
     * @param idEntrenador
     * @return
     */

    public static List<Pokemon> getEquipo1(LinkedList<Pokemon> equipo1, int idEntrenador) {

        try {

            Connection db = MySQL.getConexion();
            String sql = "SELECT * FROM capturado WHERE id_entrenador = ? AND equipo = 1";
            PreparedStatement preparedStatement = db.prepareStatement(sql);
            preparedStatement.setInt(1, idEntrenador);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Pokemon pokemon = new Pokemon();
                pokemon.setId(rs.getInt("id_capturado"));
                pokemon.setNombre(rs.getString("nombre"));
                pokemon.setMote(rs.getString("mote"));
                pokemon.setVitalidad(rs.getDouble("vitalidad"));
                pokemon.setFertilidad(rs.getInt("fertilidad"));
                pokemon.setVelocidad(rs.getDouble("velocidad"));
                pokemon.setEstamina(rs.getDouble("estamina"));
                pokemon.setAtaque(rs.getDouble("ataque"));
                pokemon.setDefensa(rs.getDouble("defensa"));
                pokemon.setAtaqueEspecial(rs.getDouble("ataque_especial"));
                pokemon.setDefensaEspecial(rs.getDouble("defensa_especial"));
                pokemon.setTipo1(Tipo.valueOf(rs.getString("tipo1").toUpperCase()));
                String tipo2 = rs.getString("tipo2");
                if (tipo2 != null) { // Controlar si tipo2 es nulo o no.
                    try {
                        pokemon.setTipo2(Tipo.valueOf(tipo2.toUpperCase()));
                    } catch (IllegalArgumentException e) {

                    }
                }
                pokemon.setNivel(rs.getInt("nivel"));
                pokemon.setXp(rs.getInt("xp"));
                pokemon.setFoto(rs.getString("foto"));
                pokemon.setFotoEspalda(rs.getString("foto_espalda"));

                equipo1.add(pokemon);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return equipo1;

    }

    /**
     * Método para rellenar la lista de equipo2 del Entrenador con los Pokemons de la tabla capturado.
     * @param equipo2
     * @param idEntrenador
     * @return
     */

    public static List<Pokemon> getEquipo2(LinkedList<Pokemon> equipo2, int idEntrenador) {

        try {

            Connection db = MySQL.getConexion();
            String sql = "SELECT * FROM capturado WHERE id_entrenador = ? AND equipo = 2";
            PreparedStatement preparedStatement = db.prepareStatement(sql);
            preparedStatement.setInt(1, idEntrenador);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Pokemon pokemon = new Pokemon();
                pokemon.setId(rs.getInt("id_capturado"));
                pokemon.setNombre(rs.getString("nombre"));
                pokemon.setMote(rs.getString("mote"));
                pokemon.setVitalidad(rs.getDouble("vitalidad"));
                pokemon.setFertilidad(rs.getInt("fertilidad"));
                pokemon.setVelocidad(rs.getDouble("velocidad"));
                pokemon.setEstamina(rs.getDouble("estamina"));
                pokemon.setAtaque(rs.getDouble("ataque"));
                pokemon.setDefensa(rs.getDouble("defensa"));
                pokemon.setAtaqueEspecial(rs.getDouble("ataque_especial"));
                pokemon.setDefensaEspecial(rs.getDouble("defensa_especial"));
                pokemon.setTipo1(Tipo.valueOf(rs.getString("tipo1").toUpperCase()));
                String tipo2 = rs.getString("tipo2");
                if (tipo2 != null) { // Controlar si tipo2 es nulo o no.
                    try {
                        pokemon.setTipo2(Tipo.valueOf(tipo2.toUpperCase()));
                    } catch (IllegalArgumentException e) {

                    }
                }
                pokemon.setNivel(rs.getInt("nivel"));
                pokemon.setXp(rs.getInt("xp"));
                pokemon.setFoto(rs.getString("foto"));
                pokemon.setFotoEspalda(rs.getString("foto_espalda"));

                equipo2.add(pokemon);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return equipo2;

    }

    /**
     * Método para rellenar la lista de la caja del Entrenador con los Pokemons de la tabla capturado.
     * @param caja
     * @param idEntrenador
     * @return
     */

    public static List<Pokemon> getCaja(LinkedList<Pokemon> caja, int idEntrenador) {

        try {

            Connection db = MySQL.getConexion();
            String sql = "SELECT * FROM capturado WHERE id_entrenador = ? AND equipo = 3";
            PreparedStatement preparedStatement = db.prepareStatement(sql);
            preparedStatement.setInt(1, idEntrenador);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Pokemon pokemon = new Pokemon();
                pokemon.setId(rs.getInt("id_capturado"));
                pokemon.setNombre(rs.getString("nombre"));
                pokemon.setMote(rs.getString("mote"));
                pokemon.setVitalidad(rs.getDouble("vitalidad"));
                pokemon.setFertilidad(rs.getInt("fertilidad"));
                pokemon.setVelocidad(rs.getDouble("velocidad"));
                pokemon.setEstamina(rs.getDouble("estamina"));
                pokemon.setAtaque(rs.getDouble("ataque"));
                pokemon.setDefensa(rs.getDouble("defensa"));
                pokemon.setAtaqueEspecial(rs.getDouble("ataque_especial"));
                pokemon.setDefensaEspecial(rs.getDouble("defensa_especial"));
                pokemon.setTipo1(Tipo.valueOf(rs.getString("tipo1").toUpperCase()));
                String tipo2 = rs.getString("tipo2");
                if (tipo2 != null) { // Controlar si tipo2 es nulo o no.
                    try {
                        pokemon.setTipo2(Tipo.valueOf(tipo2.toUpperCase()));
                    } catch (IllegalArgumentException e) {

                    }
                }
                pokemon.setNivel(rs.getInt("nivel"));
                pokemon.setXp(rs.getInt("xp"));
                pokemon.setFoto(rs.getString("foto"));
                pokemon.setFotoEspalda(rs.getString("foto_espalda"));

                caja.add(pokemon);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return caja;

    }

    /**
     * Método que comprueba el nombre y la contraseña que se han ingresado en los parametros.
     * Si el login = true se ejecuta el método cargarEntrenador para cargar toda su información.
     * Si el login = false no entrará en el programa.
     * @param usuarioIngresado
     * @param passIngresada
     */

    public static void login(String usuarioIngresado, String passIngresada) {
        try {
            Connection db = MySQL.getConexion();
            String sql = "SELECT nombre, pass FROM entrenador WHERE nombre = ?";
            PreparedStatement preparedStatement = db.prepareStatement(sql);
            preparedStatement.setString(1, usuarioIngresado);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String usuario = rs.getString("nombre");
                String pass = rs.getString("pass");

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
     * Método para cargar la información del entrenador de la base de datos en el programa.
     *
     * @param nombre
     * @return Entrenador
     */

    public static Entrenador cargarEntrenador(String nombre) {
        try {
            Connection db = MySQL.getConexion();
            String sql = "SELECT * FROM entrenador WHERE nombre = ?";

            PreparedStatement preparedStatement = db.prepareStatement(sql);
            preparedStatement.setString(1, nombre);

            ResultSet rs = preparedStatement.executeQuery();

            Entrenador entrenador = null;

            if (rs.next()) {
                // Obtener todos los detalles del usuario de la base de datos
                String contrasena = rs.getString("pass");
                int dinero = rs.getInt("dinero");

                // Crear un objeto Entrenador con los detalles cargados
                entrenador = new Entrenador(id, nombre, dinero, new LinkedList<>(), new HashMap<>(), contrasena, new LinkedList<>(), new LinkedList<>(), new LinkedList<>());
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
            PreparedStatement pStatement = db.prepareStatement(sql);
            ResultSet rs = pStatement.executeQuery();
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
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Pokemon pokemon = new Pokemon();

                pokemon.setId(rs.getInt("id_pokedex"));
                pokemon.setNombre(rs.getString("nombre"));
                pokemon.setVitalidad(rs.getDouble("vitalidad"));
                pokemon.setVelocidad(rs.getDouble("velocidad"));
                pokemon.setEstamina(rs.getDouble("estamina"));
                pokemon.setAtaque(rs.getDouble("ataque"));
                pokemon.setDefensa(rs.getDouble("defensa"));
                pokemon.setAtaqueEspecial(rs.getDouble("ataque_especial"));
                pokemon.setDefensaEspecial(rs.getDouble("defensa_especial"));
                pokemon.setTipo1(Tipo.valueOf(rs.getString("tipo1").toUpperCase()));
                String tipo2 = rs.getString("tipo2");
                if (tipo2 != null) {
                    pokemon.setTipo2(Tipo.valueOf(tipo2.toUpperCase()));
                }
                pokemon.setFoto(rs.getString("foto"));
                pokemon.setFotoEspalda(rs.getString("foto_espalda"));

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
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Ataque ataque = new Ataque();
                ataque.setNombreMovimiento(rs.getString("nombre"));
                ataque.setPotencia(rs.getInt("potencia"));
                ataque.setTipo(Tipo.valueOf(rs.getString("tipo_ataque").toUpperCase()));

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
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Estado estado = new Estado();
                estado.setNombreMovimiento(rs.getString("nombre"));
                estado.setTurnos(rs.getInt("turno"));
                estado.setTipo(Tipo.valueOf(rs.getString("tipo_ataque").toUpperCase()));

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
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Mejora mejora = new Mejora();
                mejora.setNombreMovimiento(rs.getString("nombre"));
                mejora.setTurnos(rs.getInt("turno"));
                mejora.setTipo(Tipo.valueOf(rs.getString("tipo_ataque").toUpperCase()));

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

    /**
     * Introduciendo la ID del Ataque, te devuelve un objeto de tipo Ataque.
     *
     * @param id
     * @return Ataque
     */

    public static Ataque obtenerAtaque(int id) { //ID ataque: 1-50
        try {
            Connection db = MySQL.getConexion();
            String sql = "SELECT * FROM movimiento WHERE id_movimiento = ? AND tipo_movimiento = 'Ataque'";
            PreparedStatement preparedStatement = db.prepareStatement(sql);


            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Ataque ataque = new Ataque();
                ataque.setNombreMovimiento(rs.getString("nombre"));
                ataque.setPotencia(rs.getInt("potencia"));
                ataque.setTipo(Tipo.valueOf(rs.getString("tipo_ataque").toUpperCase()));

                return ataque;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Introduciendo la ID del Estado, te devuelve un objeto de tipo Estado.
     *
     * @param id
     * @return Estado
     */

    public static Estado obtenerEstado(int id) { //ID Estado: 61-84
        try {
            Connection db = MySQL.getConexion();
            String sql = "SELECT * FROM movimiento WHERE id_movimiento = ? AND tipo_movimiento = 'Estado'";
            PreparedStatement preparedStatement = db.prepareStatement(sql);


            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Estado estado = new Estado();
                estado.setNombreMovimiento(rs.getString("nombre"));
                estado.setTurnos(rs.getInt("turno"));
                estado.setTipo(Tipo.valueOf(rs.getString("tipo_ataque").toUpperCase()));
                estado.setEstado(ListaEstados.valueOf((rs.getString("estado").toUpperCase())));

                return estado;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Introduciendo la ID de la Mejora, te devuelve un objeto de tipo Mejora.
     *
     * @param id
     * @return Mejora
     */

    public static Mejora obtenerMejora(int id) { //ID Estado: 51-60
        try {
            Connection db = MySQL.getConexion();
            String sql = "SELECT * FROM movimiento WHERE id_movimiento = ? AND tipo_movimiento = 'Mejora'";
            PreparedStatement preparedStatement = db.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Mejora mejora = new Mejora();
                mejora.setNombreMovimiento(rs.getString("nombre"));
                mejora.setTurnos(rs.getInt("turno"));
                mejora.setTipo(Tipo.valueOf(rs.getString("tipo_ataque").toUpperCase()));

                return mejora;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Actúa como si fuese un valueOf para convertir un objeto de tipo "int" a un objeto en tipo "Movimiento".
     * @param id
     * @return
     */

    public static Movimiento selectMovimiento(int id) {

        try {

            Connection db = MySQL.getConexion();

            String sql = "SELECT tipo_movimiento FROM movimiento WHERE id_movimiento = ?";
            PreparedStatement preparedStatement = db.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String tipoMovimiento = rs.getString("tipo_movimiento").toLowerCase();

                Movimiento movimiento;

                if (tipoMovimiento.equals("ataque")) {
                    return obtenerAtaque(id);
                } else if (tipoMovimiento.equals("estado")) {
                    return obtenerEstado(id);
                } else {
                    return obtenerMejora(id);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Método que busca el movimiento por nombre en la DB y devuelve su ID.
     *
     * @param movimiento
     * @return id_movimiento
     */

    public static int idMovimiento(Movimiento movimiento) {

        try {

            Connection db = MySQL.getConexion();

            int idMovimiento;
            String sql = "SELECT id_movimiento FROM movimiento WHERE nombre = ?";
            PreparedStatement preparedStatement = db.prepareStatement(sql);
            preparedStatement.setString(1, movimiento.getNombreMovimiento());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                idMovimiento = rs.getInt("id_movimiento");
                return idMovimiento;
            }


        } catch (SQLException e) {
            e.printStackTrace();

        }

        return 0;

    }
    // ⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇
    // CAMBIAR
    // DESPUES DE
    // TERMINAR LAS
    // PRUEBAS
    // ⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇

    public static int idEntrenador() {

        Entrenador.setNombre("Juan"); //Se cambia esto

        try {

            Connection db = MySQL.getConexion();

            int idEntrenador;
            String sql = "SELECT id_entrenador FROM entrenador WHERE nombre = ?";
            PreparedStatement preparedStatement = db.prepareStatement(sql);
            preparedStatement.setString(1, Entrenador.getNombre());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                idEntrenador = rs.getInt("id_entrenador");
                return idEntrenador;
            }


        } catch (SQLException e) {
            e.printStackTrace();

        }

        return 0;

    }

    public static Objeto getObjeto (int idObjeto) {

        Objeto objeto = new Objeto();

        try {
            Connection db = MySQL.getConexion();

            String sql = "SELECT * FROM objeto WHERE id_objeto = ?";
            PreparedStatement preparedStatement = db.prepareStatement(sql);
            preparedStatement.setInt(1, idObjeto);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                objeto.setId(rs.getInt("id_objeto"));
                objeto.setNombre(rs.getString("nombre"));
                objeto.setPrecio(rs.getInt("precio"));
                objeto.setDescripcion(rs.getString("descripcion"));
                objeto.setAtaque(rs.getDouble("ataque"));
                objeto.setDefensa(rs.getDouble("defensa"));
                objeto.setDefensaEspecial(rs.getDouble("defensa_especial"));
                objeto.setVelocidad(rs.getDouble("velocidad"));
                objeto.setEstamina(rs.getDouble("estamina"));
                return objeto;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Método que devuelve la mochila pero muestra las IDs.
     * Se utiliza como método complementario para mostrarMochila().
     * @param idEntrenador
     * @return mochila (en IDs)
     */

    public static Mochila getMochila(int idEntrenador) {

        Mochila mochila = new Mochila();

        try {

            Connection db = MySQL.getConexion();
            String sql = "SELECT * FROM mochila WHERE id_entrenador = ?";
            PreparedStatement preparedStatement = db.prepareStatement(sql);
            preparedStatement.setInt(1, idEntrenador);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                mochila.setIdMochila(rs.getInt("id_mochila"));
                mochila.setIdObjeto(rs.getInt("id_objeto"));
                mochila.setIdObjeto2(rs.getInt("id_objeto2"));
                mochila.setIdObjeto3(rs.getInt("id_objeto3"));
                mochila.setIdObjeto4(rs.getInt("id_objeto4"));
                mochila.setIdObjeto5(rs.getInt("id_objeto5"));
                mochila.setIdObjeto6(rs.getInt("id_objeto6"));
                mochila.setIdEntrenador(rs.getInt("id_entrenador"));

                return mochila;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }



//    public static String mostrarMochila(int idObjeto) {
//
//        try {
//
//            Connection db = MySQL.getConexion();
//            String sql = "SELECT nombre FROM objeto WHERE id_mochila = ?";
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//    }

//    public static Movimiento selectMovimiento(int id) {
//
//        try {
//
//            Connection db = MySQL.getConexion();
//
//            String sql = "SELECT tipo_movimiento FROM movimiento WHERE id_movimiento = ?";
//            PreparedStatement preparedStatement = db.prepareStatement(sql);
//            preparedStatement.setInt(1, id);
//            ResultSet rs = preparedStatement.executeQuery();
//
//            if (rs.next()) {
//                String tipoMovimiento = rs.getString("tipo_movimiento").toLowerCase();
//
//                Movimiento movimiento;
//
//                if (tipoMovimiento.equals("ataque")) {
//                    return obtenerAtaque(id);
//                } else if (tipoMovimiento.equals("estado")) {
//                    return obtenerEstado(id);
//                } else {
//                    return obtenerMejora(id);
//                }
//
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static Pokemon mostrarPokemon() {

        PokemonCRUD.getEquipo1(Entrenador.equipo1, Entrenador.getId());
        int contador = 0;

        for (Pokemon pokemon : Entrenador.equipo1) {
            contador++;
            System.out.println(contador + ". " + pokemon.getNombre());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Selecciona un pokemon: ");
        int opcion = scanner.nextInt();

        if (Entrenador.equipo1.size() >= opcion) {
            switch (opcion) {
                case 1:
                    return PokemonCRUD.getPokemon(Entrenador.equipo1.get(0).getId());
                case 2:
                    return PokemonCRUD.getPokemon(Entrenador.equipo1.get(1).getId());
                case 3:
                    return PokemonCRUD.getPokemon(Entrenador.equipo1.get(2).getId());
                case 4:
                    return PokemonCRUD.getPokemon(Entrenador.equipo1.get(3).getId());
                case 5:
                    return PokemonCRUD.getPokemon(Entrenador.equipo1.get(4).getId());
                case 6:
                    return PokemonCRUD.getPokemon(Entrenador.equipo1.get(5).getId());
                default:
                    System.out.println("Opción no válida.");
                    return null;
            }
        } else {
            System.out.println("Opción no válida.");
            return null;
        }
    }



    public static void main(String[] args) {

        Entrenador.setId(6);

        verEquipos();

    }

}


