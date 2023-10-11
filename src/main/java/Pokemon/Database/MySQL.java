package Pokemon.Database;

import java.sql.*;

public class MySQL {

    private static Connection conexion;

    /**
     * Este método crea la conexión con la base de datos de localhost
     * @return
     * @throws SQLException
     */

    public static Connection getConexion() throws SQLException {
        if (conexion == null)
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pokemon", "root", "");
//            conexion = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/pokemondbjym", "jympokemon", "00xz45dkIrka145!fkgfir459azir");
        return conexion;
    }


}
