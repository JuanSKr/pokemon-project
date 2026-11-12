package pokemon.database;

import pokemon.combate.movimientos.Ataque;
import pokemon.combate.movimientos.Estado;
import pokemon.combate.movimientos.Mejora;
import pokemon.combate.movimientos.Movimiento;
import pokemon.entrenador.Entrenador;
import pokemon.entrenador.Mochila;
import pokemon.pokemon.ListaEstados;
import pokemon.pokemon.Pokemon;
import pokemon.pokemon.Tipo;
import pokemon.tienda.Objeto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Todo el acceso a la base de datos del juego.
 * Los errores de SQL se convierten en {@link ErrorBaseDatos}, que la interfaz muestra en un diálogo.
 */
public class PokemonCRUD {

    /** Resultado de intentar registrar un entrenador nuevo. */
    public enum Registro { CORRECTO, YA_EXISTE }

    /** Dinero con el que empieza un entrenador nuevo. */
    public static final int DINERO_INICIAL = 800;

    private static Map<Integer, Movimiento> movimientos;
    private static List<Objeto> articulos;

    private PokemonCRUD() {
    }

    /** Error al acceder a la base de datos (con un mensaje que se puede enseñar al jugador). */
    public static class ErrorBaseDatos extends RuntimeException {
        public ErrorBaseDatos(String mensaje, Throwable causa) {
            super(mensaje, causa);
        }
    }

    private static ErrorBaseDatos error(String accion, SQLException e) {
        e.printStackTrace();
        return new ErrorBaseDatos("No se pudo " + accion + ". Comprueba que MySQL (XAMPP) está encendido.", e);
    }

    // =====================================================================================
    // Entrenador: login, registro y datos
    // =====================================================================================

    /** Comprueba usuario y contraseña. Si son correctos, carga el entrenador y devuelve true. */
    public static boolean login(String usuario, String pass) {
        String sql = "SELECT id_entrenador, nombre, pass, dinero, foto FROM entrenador WHERE nombre = ?";
        try (PreparedStatement ps = MySQL.getConexion().prepareStatement(sql)) {
            ps.setString(1, usuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next() && rs.getString("pass").equals(pass)) {
                    Entrenador.iniciarSesion(rs.getInt("id_entrenador"), rs.getString("nombre"),
                            (int) Math.round(rs.getDouble("dinero")), rs.getString("foto"));
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            throw error("iniciar sesión", e);
        }
    }

    /** Crea un entrenador nuevo con 800 de dinero y 5 Poké Balls de regalo. */
    public static Registro registrar(String nombre, String pass) {
        try {
            Connection db = MySQL.getConexion();
            try (PreparedStatement ps = db.prepareStatement("SELECT 1 FROM entrenador WHERE nombre = ?")) {
                ps.setString(1, nombre);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) return Registro.YA_EXISTE;
                }
            }
            int nuevaId = siguienteId(db, "entrenador", "id_entrenador");
            try (PreparedStatement ps = db.prepareStatement(
                    "INSERT INTO entrenador (id_entrenador, nombre, pass, dinero, foto) VALUES (?, ?, ?, ?, NULL)")) {
                ps.setInt(1, nuevaId);
                ps.setString(2, nombre);
                ps.setString(3, pass);
                ps.setInt(4, DINERO_INICIAL);
                ps.executeUpdate();
            }
            try (PreparedStatement ps = db.prepareStatement(
                    "INSERT INTO inventario (id_entrenador, id_articulo, cantidad) VALUES (?, 1, 5)")) {
                ps.setInt(1, nuevaId);
                ps.executeUpdate();
            }
            return Registro.CORRECTO;
        } catch (SQLException e) {
            throw error("crear la cuenta", e);
        }
    }

    public static int idEntrenador() {
        return Entrenador.getId();
    }

    /** Cambia el dinero del entrenador (en memoria y en la base de datos). */
    public static void actualizarDinero(int nuevoDinero) {
        Entrenador.setDinero(nuevoDinero);
        try (PreparedStatement ps = MySQL.getConexion().prepareStatement(
                "UPDATE entrenador SET dinero = ? WHERE id_entrenador = ?")) {
            ps.setInt(1, Entrenador.getDinero());
            ps.setInt(2, Entrenador.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw error("guardar el dinero", e);
        }
    }

    public static void updateFoto(String foto) {
        Entrenador.setFoto(foto);
        try (PreparedStatement ps = MySQL.getConexion().prepareStatement(
                "UPDATE entrenador SET foto = ? WHERE id_entrenador = ?")) {
            ps.setString(1, foto);
            ps.setInt(2, Entrenador.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw error("guardar el avatar", e);
        }
    }

    // =====================================================================================
    // Pokémon capturados
    // =====================================================================================

    /** Pokémon del entrenador en un equipo (1 = titular, 2 = equipo 2, 3 = caja), en orden de captura. */
    public static List<Pokemon> getEquipo(int equipo) {
        return consultarCapturados("SELECT * FROM capturado WHERE id_entrenador = ? AND equipo = ? ORDER BY id_capturado",
                Entrenador.getId(), equipo);
    }

    /** Recarga las tres listas del entrenador (equipo 1, equipo 2 y caja) desde la base de datos. */
    public static void recargarEquipos() {
        Entrenador.equipo1.clear();
        Entrenador.equipo2.clear();
        Entrenador.caja.clear();
        for (Pokemon p : consultarCapturados("SELECT * FROM capturado WHERE id_entrenador = ? ORDER BY id_capturado",
                Entrenador.getId())) {
            switch (p.getEquipo()) {
                case 1 -> Entrenador.equipo1.add(p);
                case 2 -> Entrenador.equipo2.add(p);
                default -> Entrenador.caja.add(p);
            }
        }
    }

    public static Pokemon getPokemon(int idCapturado) {
        List<Pokemon> lista = consultarCapturados("SELECT * FROM capturado WHERE id_capturado = ?", idCapturado);
        // Sin operador ternario: el compilador de VS Code (ECJ) genera mal ese caso con pokemon.pokemon.Pokemon.
        if (lista.isEmpty()) return null;
        return lista.get(0);
    }

    private static List<Pokemon> consultarCapturados(String sql, int... parametros) {
        List<Pokemon> lista = new ArrayList<>();
        try (PreparedStatement ps = MySQL.getConexion().prepareStatement(sql)) {
            for (int i = 0; i < parametros.length; i++) {
                ps.setInt(i + 1, parametros[i]);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Pokemon p = new Pokemon();
                    p.setId(rs.getInt("id_capturado"));
                    p.setIdPokedex(rs.getInt("id_pokedex"));
                    p.setNombre(rs.getString("nombre"));
                    p.setMote(rs.getString("mote"));
                    p.setEquipo(rs.getInt("equipo"));
                    p.setVitalidad(rs.getDouble("vitalidad"));
                    p.setFertilidad(rs.getInt("fertilidad"));
                    p.setVelocidad(rs.getDouble("velocidad"));
                    p.setEstamina(rs.getDouble("estamina"));
                    p.setAtaque(rs.getDouble("ataque"));
                    p.setDefensa(rs.getDouble("defensa"));
                    p.setAtaqueEspecial(rs.getDouble("ataque_especial"));
                    p.setDefensaEspecial(rs.getDouble("defensa_especial"));
                    p.setTipo1(Tipo.desdeTexto(rs.getString("tipo1")));
                    p.setTipo2(Tipo.desdeTexto(rs.getString("tipo2")));
                    p.setNivel(rs.getInt("nivel"));
                    p.setXp(rs.getInt("xp"));
                    p.setFoto(rs.getString("foto"));
                    p.setFotoEspalda(rs.getString("foto_espalda"));
                    p.setMovimiento1(getMovimiento(rs.getInt("movimiento1")));
                    p.setMovimiento2(getMovimiento(rs.getInt("movimiento2")));
                    p.setMovimiento3(getMovimiento(rs.getInt("movimiento3")));
                    p.setMovimiento4(getMovimiento(rs.getInt("movimiento4")));
                    lista.add(p);
                }
            }
        } catch (SQLException e) {
            throw error("cargar tus Pokémon", e);
        }
        return lista;
    }

    /**
     * Guarda un Pokémon recién capturado. Va al equipo titular si cabe; si no, al equipo 2; si no, a la caja.
     * @return el equipo donde se ha guardado (1, 2 o 3), o 0 si no queda sitio en ningún lado.
     */
    public static int guardarCapturado(Pokemon pokemon) {
        recargarEquipos();
        int equipo;
        if (Entrenador.equipo1.size() < Entrenador.TAMANO_EQUIPO) equipo = 1;
        else if (Entrenador.equipo2.size() < Entrenador.TAMANO_EQUIPO) equipo = 2;
        else if (Entrenador.caja.size() < Entrenador.TAMANO_CAJA) equipo = 3;
        else return 0;

        if (pokemon.getMovimientos().size() < 4) {
            pokemon.setMovimientos(generarMovimientos(pokemon));
        }
        List<Movimiento> movs = pokemon.getMovimientos();

        String sql = "INSERT INTO capturado (id_capturado, nombre, mote, equipo, vitalidad, fertilidad, velocidad, estamina, "
                + "ataque, defensa, ataque_especial, defensa_especial, tipo1, tipo2, movimiento1, movimiento2, movimiento3, "
                + "movimiento4, id_entrenador, id_pokedex, nivel, xp, foto, foto_espalda) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection db = MySQL.getConexion();
            int nuevaId = siguienteId(db, "capturado", "id_capturado");
            try (PreparedStatement ps = db.prepareStatement(sql)) {
                ps.setInt(1, nuevaId);
                ps.setString(2, pokemon.getNombre());
                ps.setString(3, pokemon.getMote() == null ? "" : pokemon.getMote().trim());
                ps.setInt(4, equipo);
                ps.setInt(5, (int) Math.round(pokemon.getVitalidad()));
                ps.setInt(6, 5);
                ps.setInt(7, (int) Math.round(pokemon.getVelocidad()));
                ps.setInt(8, (int) Math.round(pokemon.getEstamina()));
                ps.setInt(9, (int) Math.round(pokemon.getAtaque()));
                ps.setInt(10, (int) Math.round(pokemon.getDefensa()));
                ps.setInt(11, (int) Math.round(pokemon.getAtaqueEspecial()));
                ps.setInt(12, (int) Math.round(pokemon.getDefensaEspecial()));
                ps.setString(13, pokemon.getTipo1() == null ? "NORMAL" : pokemon.getTipo1().name());
                ps.setString(14, pokemon.getTipo2() == null ? null : pokemon.getTipo2().name());
                for (int i = 0; i < 4; i++) {
                    if (i < movs.size()) ps.setInt(15 + i, movs.get(i).getId());
                    else ps.setNull(15 + i, java.sql.Types.INTEGER);
                }
                ps.setInt(19, Entrenador.getId());
                ps.setInt(20, pokemon.getIdPokedex());
                ps.setInt(21, pokemon.getNivel());
                ps.setInt(22, 0);
                ps.setString(23, pokemon.getFoto());
                ps.setString(24, pokemon.getFotoEspalda());
                ps.executeUpdate();
            }
            pokemon.setId(nuevaId);
            pokemon.setEquipo(equipo);
            return equipo;
        } catch (SQLException e) {
            throw error("guardar el Pokémon capturado", e);
        }
    }

    /** Cambia un Pokémon de equipo (1, 2 o 3 = caja). */
    public static void moverPokemon(int idCapturado, int equipo) {
        ejecutar("mover el Pokémon", "UPDATE capturado SET equipo = ? WHERE id_capturado = ? AND id_entrenador = ?",
                equipo, idCapturado, Entrenador.getId());
    }

    /** Libera (borra) un Pokémon del entrenador. */
    public static void liberarPokemon(int idCapturado) {
        ejecutar("liberar el Pokémon", "DELETE FROM capturado WHERE id_capturado = ? AND id_entrenador = ?",
                idCapturado, Entrenador.getId());
    }

    public static void actualizarMote(int idCapturado, String mote) {
        try (PreparedStatement ps = MySQL.getConexion().prepareStatement(
                "UPDATE capturado SET mote = ? WHERE id_capturado = ? AND id_entrenador = ?")) {
            ps.setString(1, mote == null ? "" : mote.trim());
            ps.setInt(2, idCapturado);
            ps.setInt(3, Entrenador.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw error("cambiar el mote", e);
        }
    }

    /** Guarda el nivel, la experiencia y las estadísticas de un Pokémon después de un combate. */
    public static void guardarProgreso(Pokemon p) {
        ejecutar("guardar la experiencia",
                "UPDATE capturado SET nivel = ?, xp = ?, vitalidad = ?, ataque = ?, defensa = ?, ataque_especial = ?, "
                        + "defensa_especial = ?, velocidad = ?, estamina = ? WHERE id_capturado = ?",
                p.getNivel(), p.getXp(), (int) Math.round(p.getVitalidad()), (int) Math.round(p.getAtaque()),
                (int) Math.round(p.getDefensa()), (int) Math.round(p.getAtaqueEspecial()),
                (int) Math.round(p.getDefensaEspecial()), (int) Math.round(p.getVelocidad()),
                (int) Math.round(p.getEstamina()), p.getId());
    }

    /** Si al Pokémon le faltan movimientos (los capturados antiguos solo tenían uno), le enseña hasta tener 4. */
    public static void completarMovimientos(Pokemon p) {
        List<Movimiento> actuales = p.getMovimientos();
        if (actuales.size() >= 4) return;
        List<Movimiento> nuevos = generarMovimientos(p);
        List<Movimiento> resultado = new ArrayList<>(actuales);
        for (Movimiento m : nuevos) {
            if (resultado.size() >= 4) break;
            if (resultado.stream().noneMatch(x -> x.getId() == m.getId())) resultado.add(m);
        }
        p.setMovimientos(resultado);
        Integer[] ids = new Integer[4];
        for (int i = 0; i < 4; i++) ids[i] = i < resultado.size() ? resultado.get(i).getId() : null;
        try (PreparedStatement ps = MySQL.getConexion().prepareStatement(
                "UPDATE capturado SET movimiento1 = ?, movimiento2 = ?, movimiento3 = ?, movimiento4 = ? WHERE id_capturado = ?")) {
            for (int i = 0; i < 4; i++) {
                if (ids[i] == null) ps.setNull(i + 1, java.sql.Types.INTEGER);
                else ps.setInt(i + 1, ids[i]);
            }
            ps.setInt(5, p.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw error("guardar los movimientos", e);
        }
    }

    // =====================================================================================
    // Pokédex
    // =====================================================================================

    public static Pokemon getPokedex(int idPokedex) {
        List<Pokemon> l = consultarPokedex("SELECT * FROM pokedex WHERE id_pokedex = ?", String.valueOf(idPokedex));
        if (l.isEmpty()) return null;
        return l.get(0);
    }

    /** Busca en la Pokédex por número o por (parte del) nombre, sin distinguir mayúsculas. */
    public static List<Pokemon> buscarPokedex(String texto) {
        String t = texto.trim();
        if (t.matches("\\d+")) {
            return consultarPokedex("SELECT * FROM pokedex WHERE id_pokedex = ?", t);
        }
        return consultarPokedex("SELECT * FROM pokedex WHERE nombre LIKE ? ORDER BY (nombre = ?) DESC, "
                + "(nombre LIKE ?) DESC, id_pokedex", "%" + t + "%", t, t + "%");
    }

    public static int totalPokedex() {
        try (Statement st = MySQL.getConexion().createStatement();
             ResultSet rs = st.executeQuery("SELECT MAX(id_pokedex) FROM pokedex")) {
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            throw error("leer la Pokédex", e);
        }
    }

    /** Un Pokémon aleatorio de la Pokédex (los más fuertes salen con menos frecuencia). */
    public static Pokemon generarPokemon() {
        List<Pokemon> todos = todosPokedex();
        double total = 0;
        double[] pesos = new double[todos.size()];
        for (int i = 0; i < todos.size(); i++) {
            pesos[i] = 1.0 / Math.pow(todos.get(i).sumaStats() / 250.0, 2.2);
            total += pesos[i];
        }
        double r = Math.random() * total;
        for (int i = 0; i < todos.size(); i++) {
            r -= pesos[i];
            if (r <= 0) return new Pokemon(todos.get(i));
        }
        return new Pokemon(todos.get(todos.size() - 1));
    }

    private static List<Pokemon> pokedexCache;

    private static List<Pokemon> todosPokedex() {
        if (pokedexCache == null) {
            pokedexCache = consultarPokedex("SELECT * FROM pokedex ORDER BY id_pokedex");
        }
        return pokedexCache;
    }

    private static List<Pokemon> consultarPokedex(String sql, String... parametros) {
        List<Pokemon> lista = new ArrayList<>();
        try (PreparedStatement ps = MySQL.getConexion().prepareStatement(sql)) {
            for (int i = 0; i < parametros.length; i++) ps.setString(i + 1, parametros[i]);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Pokemon p = new Pokemon();
                    p.setId(rs.getInt("id_pokedex"));
                    p.setIdPokedex(rs.getInt("id_pokedex"));
                    p.setNombre(rs.getString("nombre"));
                    p.setMote("");
                    p.setVitalidad(rs.getDouble("vitalidad"));
                    p.setVelocidad(rs.getDouble("velocidad"));
                    p.setEstamina(rs.getDouble("estamina"));
                    p.setAtaque(rs.getDouble("ataque"));
                    p.setDefensa(rs.getDouble("defensa"));
                    p.setAtaqueEspecial(rs.getDouble("ataque_especial"));
                    p.setDefensaEspecial(rs.getDouble("defensa_especial"));
                    p.setTipo1(Tipo.desdeTexto(rs.getString("tipo1")));
                    p.setTipo2(Tipo.desdeTexto(rs.getString("tipo2")));
                    p.setFoto(rs.getString("foto"));
                    p.setFotoEspalda(rs.getString("foto_espalda"));
                    p.setNivel(1);
                    lista.add(p);
                }
            }
        } catch (SQLException e) {
            throw error("leer la Pokédex", e);
        }
        return lista;
    }

    // =====================================================================================
    // Movimientos
    // =====================================================================================

    /** Carga (una sola vez) todos los movimientos de la base de datos. */
    private static Map<Integer, Movimiento> movimientos() {
        if (movimientos != null) return movimientos;
        Map<Integer, Movimiento> mapa = new LinkedHashMap<>();
        try (Statement st = MySQL.getConexion().createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM movimiento ORDER BY id_movimiento")) {
            while (rs.next()) {
                String clase = rs.getString("tipo_movimiento").toLowerCase();
                Movimiento m;
                switch (clase) {
                    case "estado" -> {
                        Estado e = new Estado();
                        e.setEstado(ListaEstados.desdeTexto(rs.getString("estado")));
                        m = e;
                    }
                    case "mejora" -> {
                        Mejora mj = new Mejora();
                        mj.setAtaque(rs.getInt("ataque"));
                        mj.setDefensa(rs.getInt("defensa"));
                        mj.setAtaqueEspecial(rs.getInt("ataque_especial"));
                        mj.setDefensaEspecial(rs.getInt("defensa_especial"));
                        mj.setVitalidad(rs.getInt("vitalidad"));
                        m = mj;
                    }
                    default -> {
                        Ataque a = new Ataque();
                        a.setPotencia(rs.getInt("potencia"));
                        m = a;
                    }
                }
                m.setId(rs.getInt("id_movimiento"));
                m.setNombreMovimiento(rs.getString("nombre"));
                m.setTipo(Tipo.desdeTexto(rs.getString("tipo_ataque")));
                m.setTipoMovimiento(clase);
                m.setTurnos(rs.getInt("turno"));
                mapa.put(m.getId(), m);
            }
        } catch (SQLException e) {
            throw error("cargar los movimientos", e);
        }
        movimientos = mapa;
        return mapa;
    }

    /** Movimiento por su id, o null si el id es 0 o no existe. */
    public static Movimiento getMovimiento(int id) {
        if (id <= 0) return null;
        return movimientos().get(id);
    }

    /**
     * Elige 4 movimientos para un Pokémon: hasta dos ataques de sus tipos, un ataque de otro tipo
     * y un movimiento de estado o de mejora.
     */
    public static List<Movimiento> generarMovimientos(Pokemon p) {
        List<Movimiento> ataquesPropios = new ArrayList<>();
        List<Movimiento> ataquesOtros = new ArrayList<>();
        List<Movimiento> apoyo = new ArrayList<>();
        java.util.Set<String> nombres = new java.util.HashSet<>();
        for (Movimiento m : movimientos().values()) {
            if (m instanceof Ataque) {
                if (p.tieneTipo(m.getTipo())) ataquesPropios.add(m);
                else ataquesOtros.add(m);
            } else if (!(m instanceof Estado e) || e.getEstado() != null) {
                apoyo.add(m);
            }
        }
        Collections.shuffle(ataquesPropios);
        Collections.shuffle(ataquesOtros);
        Collections.shuffle(apoyo);

        List<Movimiento> elegidos = new ArrayList<>();
        for (Movimiento m : ataquesPropios) {
            if (elegidos.size() >= 2) break;
            if (nombres.add(m.getNombreMovimiento())) elegidos.add(m);
        }
        for (Movimiento m : ataquesOtros) {
            if (elegidos.size() >= 3) break;
            if (nombres.add(m.getNombreMovimiento())) elegidos.add(m);
        }
        for (Movimiento m : apoyo) {
            if (elegidos.size() >= 4) break;
            if (nombres.add(m.getNombreMovimiento())) elegidos.add(m);
        }
        return elegidos;
    }

    // =====================================================================================
    // Tienda y mochila
    // =====================================================================================

    /** Catálogo de la tienda. */
    public static List<Objeto> getArticulos() {
        if (articulos != null) return articulos;
        List<Objeto> lista = new ArrayList<>();
        try (Statement st = MySQL.getConexion().createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM articulo ORDER BY id_articulo")) {
            while (rs.next()) {
                lista.add(new Objeto(rs.getInt("id_articulo"), rs.getString("nombre"), rs.getInt("precio"),
                        rs.getString("descripcion"), Objeto.Categoria.valueOf(rs.getString("categoria")),
                        rs.getDouble("valor")));
            }
        } catch (SQLException e) {
            throw error("cargar la tienda", e);
        }
        articulos = lista;
        return lista;
    }

    /** Objetos que tiene el entrenador. */
    public static Mochila getMochila() {
        Mochila mochila = new Mochila();
        Map<Integer, Objeto> porId = new LinkedHashMap<>();
        for (Objeto o : getArticulos()) porId.put(o.getId(), o);
        try (PreparedStatement ps = MySQL.getConexion().prepareStatement(
                "SELECT id_articulo, cantidad FROM inventario WHERE id_entrenador = ? AND cantidad > 0 ORDER BY id_articulo")) {
            ps.setInt(1, Entrenador.getId());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Objeto o = porId.get(rs.getInt("id_articulo"));
                    if (o != null) mochila.poner(o, rs.getInt("cantidad"));
                }
            }
        } catch (SQLException e) {
            throw error("cargar la mochila", e);
        }
        return mochila;
    }

    /** Suma (o resta, si es negativo) unidades de un objeto en la mochila. */
    public static void cambiarCantidad(Objeto objeto, int diferencia) {
        if (diferencia >= 0) {
            ejecutar("actualizar la mochila",
                    "INSERT INTO inventario (id_entrenador, id_articulo, cantidad) VALUES (?, ?, ?) "
                            + "ON DUPLICATE KEY UPDATE cantidad = cantidad + VALUES(cantidad)",
                    Entrenador.getId(), objeto.getId(), diferencia);
        } else {
            ejecutar("actualizar la mochila",
                    "UPDATE inventario SET cantidad = GREATEST(0, cantidad + ?) WHERE id_entrenador = ? AND id_articulo = ?",
                    diferencia, Entrenador.getId(), objeto.getId());
        }
    }

    /**
     * Compra objetos: resta el dinero y los añade a la mochila en una sola transacción.
     * @return false si no hay dinero suficiente.
     */
    public static boolean comprar(Objeto objeto, int cantidad) {
        int total = objeto.getPrecio() * cantidad;
        if (cantidad <= 0 || total > Entrenador.getDinero()) return false;
        try {
            Connection db = MySQL.getConexion();
            db.setAutoCommit(false);
            try {
                try (PreparedStatement ps = db.prepareStatement(
                        "UPDATE entrenador SET dinero = dinero - ? WHERE id_entrenador = ? AND dinero >= ?")) {
                    ps.setInt(1, total);
                    ps.setInt(2, Entrenador.getId());
                    ps.setInt(3, total);
                    if (ps.executeUpdate() == 0) {
                        db.rollback();
                        return false;
                    }
                }
                try (PreparedStatement ps = db.prepareStatement(
                        "INSERT INTO inventario (id_entrenador, id_articulo, cantidad) VALUES (?, ?, ?) "
                                + "ON DUPLICATE KEY UPDATE cantidad = cantidad + VALUES(cantidad)")) {
                    ps.setInt(1, Entrenador.getId());
                    ps.setInt(2, objeto.getId());
                    ps.setInt(3, cantidad);
                    ps.executeUpdate();
                }
                db.commit();
                Entrenador.setDinero(Entrenador.getDinero() - total);
                return true;
            } catch (SQLException e) {
                db.rollback();
                throw e;
            } finally {
                db.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw error("completar la compra", e);
        }
    }

    // =====================================================================================
    // Utilidades
    // =====================================================================================

    private static int siguienteId(Connection db, String tabla, String columna) throws SQLException {
        try (Statement st = db.createStatement();
             ResultSet rs = st.executeQuery("SELECT COALESCE(MAX(" + columna + "), 0) + 1 FROM " + tabla)) {
            rs.next();
            return rs.getInt(1);
        }
    }

    private static void ejecutar(String accion, String sql, int... parametros) {
        try (PreparedStatement ps = MySQL.getConexion().prepareStatement(sql)) {
            for (int i = 0; i < parametros.length; i++) ps.setInt(i + 1, parametros[i]);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw error(accion, e);
        }
    }
}
