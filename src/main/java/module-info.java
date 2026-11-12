module pokemon {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires transitive javafx.media;
    requires java.sql;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    exports pokemon.ui;
    exports pokemon.menus;
    exports pokemon.combate;
    exports pokemon.combate.movimientos;
    exports pokemon.database;
    exports pokemon.entrenador;
    exports pokemon.funcionalidad;
    exports pokemon.pokemon;
    exports pokemon.tienda;

    opens pokemon.menus to javafx.fxml;
    opens pokemon.pokemon to javafx.fxml;
}
