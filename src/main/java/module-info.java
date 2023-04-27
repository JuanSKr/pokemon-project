module Pokemon.Menus {
    requires javafx.controls;
    requires javafx.fxml;


    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires javafx.graphics;
    requires javafx.media;

    opens Pokemon.Menus to javafx.fxml;
    exports Pokemon.Menus;
    exports Pokemon.Capturar to javafx.graphics;
    exports Pokemon.Entrenador to javafx.graphics;
    exports Pokemon.Tienda to javafx.graphics;
}