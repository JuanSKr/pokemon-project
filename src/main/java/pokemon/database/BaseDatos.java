package pokemon.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Prepara la base de datos al arrancar el juego: crea las tablas nuevas si no existen
 * (catálogo de la tienda e inventario) y corrige datos antiguos. Se puede ejecutar
 * todas las veces que haga falta: si ya está todo bien, no cambia nada.
 */
public class BaseDatos {

    private BaseDatos() {
    }

    public static void inicializar() throws SQLException {
        Connection db = MySQL.getConexion();
        boolean inventarioExistia = existeTabla(db, "inventario");

        try (Statement st = db.createStatement()) {
            st.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS articulo (
                      id_articulo INT NOT NULL PRIMARY KEY,
                      nombre VARCHAR(30) NOT NULL,
                      descripcion VARCHAR(160) NOT NULL,
                      precio INT NOT NULL,
                      categoria VARCHAR(15) NOT NULL,
                      valor DECIMAL(6,2) NOT NULL
                    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci""");

            st.executeUpdate("""
                    INSERT INTO articulo (id_articulo, nombre, descripcion, precio, categoria, valor) VALUES
                    (1, 'Poké Ball', 'Una ball básica para capturar Pokémon salvajes.', 200, 'BALL', 1.00),
                    (2, 'Super Ball', 'Ball de buena calidad. Captura con más facilidad que la Poké Ball.', 600, 'BALL', 1.50),
                    (3, 'Ultra Ball', 'Ball de altas prestaciones. Ideal para Pokémon difíciles.', 1200, 'BALL', 2.20),
                    (4, 'Poción', 'Restaura el 40% de la vida de un Pokémon en combate.', 300, 'CURACION', 40),
                    (5, 'Superpoción', 'Restaura el 80% de la vida de un Pokémon en combate.', 700, 'CURACION', 80),
                    (6, 'Éter', 'Recupera el 60% de la estamina de un Pokémon en combate.', 400, 'ESTAMINA', 60),
                    (7, 'Revivir', 'Reanima a un Pokémon debilitado con la mitad de su vida.', 1500, 'REVIVIR', 50)
                    ON DUPLICATE KEY UPDATE nombre = VALUES(nombre), descripcion = VALUES(descripcion),
                      precio = VALUES(precio), categoria = VALUES(categoria), valor = VALUES(valor)""");

            st.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS inventario (
                      id_entrenador INT NOT NULL,
                      id_articulo INT NOT NULL,
                      cantidad INT NOT NULL DEFAULT 0,
                      PRIMARY KEY (id_entrenador, id_articulo),
                      CONSTRAINT inventario_entrenador_fk FOREIGN KEY (id_entrenador)
                        REFERENCES entrenador (id_entrenador) ON DELETE CASCADE,
                      CONSTRAINT inventario_articulo_fk FOREIGN KEY (id_articulo)
                        REFERENCES articulo (id_articulo)
                    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci""");

            if (!inventarioExistia) {
                // Regalo de bienvenida para los entrenadores que ya existían: 5 Poké Balls.
                st.executeUpdate("INSERT IGNORE INTO inventario (id_entrenador, id_articulo, cantidad) "
                        + "SELECT id_entrenador, 1, 5 FROM entrenador");
            }

            // Rutas de sprites que no existían.
            for (String tabla : new String[]{"pokedex", "capturado"}) {
                st.executeUpdate("UPDATE " + tabla + " SET foto_espalda = '/img/Gif/nidoranespalda.gif' "
                        + "WHERE foto_espalda IN ('/img/Gif/nidoranfespalda.gif', '/img/Gif/nidoran-maleespalda.gif')");
                st.executeUpdate("UPDATE " + tabla + " SET foto_espalda = '/img/Gif/mukespalda.gif' "
                        + "WHERE foto_espalda = '/img/Gif/mukespaldaespalda.gif'");
            }
            // Avatar que no existe en el proyecto: se deja vacío para usar el de por defecto.
            st.executeUpdate("UPDATE entrenador SET foto = NULL WHERE foto = '/img/alonso.png'");
        }
    }

    private static boolean existeTabla(Connection db, String tabla) throws SQLException {
        DatabaseMetaData meta = db.getMetaData();
        try (ResultSet rs = meta.getTables(db.getCatalog(), null, tabla, new String[]{"TABLE"})) {
            return rs.next();
        }
    }
}
