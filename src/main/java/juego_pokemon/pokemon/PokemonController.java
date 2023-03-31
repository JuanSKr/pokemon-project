package juego_pokemon.pokemon;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PokemonController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }      
}