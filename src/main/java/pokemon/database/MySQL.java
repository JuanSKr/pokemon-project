package pokemon.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    /** Nombre de la base de datos. Se puede cambiar arrancando con -Dpokemon.db=otra_base */
    private static final String BASE = System.getProperty("pokemon.db", "pokemon");
    private static final String URL = "jdbc:mysql://localhost:3306/" + BASE
            + "?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";

    private static Connection conexion;

    /**
     * Devuelve la conexión con la base de datos de localhost. Si se ha cerrado (por ejemplo
     * porque el servidor la cortó por inactividad) se vuelve a abrir.
     */
    public static Connection getConexion() throws SQLException {
        if (conexion == null || conexion.isClosed() || !conexion.isValid(2)) {
            conexion = DriverManager.getConnection(URL, "root", "");
        }
        return conexion;
    }
}
