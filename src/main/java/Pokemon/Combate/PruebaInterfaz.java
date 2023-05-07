package Pokemon.Combate;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

//AQUÍ SE ESTÁ CREANDO LA CLASE MENUENTRENADOR
public class PruebaInterfaz extends Application {

	private static final int SCENE_WIDTH = 1080;
	private static final int SCENE_HEIGHT = 650;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// CREAR UN CONTENEDOR PRINCIPAL
		VBox root = new VBox();
		//AJUSTANDO ESTOS PARAMETROS SE UBICAN EL CONJUNTO DE ELEMENTOS
		root.setSpacing(30);
		root.setPadding(new Insets(10));
		root.setAlignment(Pos.TOP_LEFT);

		// CREAR UN MENÚ DESPLEGABLE PARA EL EQUIPO
		MenuButton teamMenu = new MenuButton("EQUIPO");
		teamMenu.getItems().addAll(new MenuItem("Pikachu"), new MenuItem("Charmander"), new MenuItem("Squirtle"));
		root.getChildren().add(teamMenu);

		// CREAR UN CONTENEDOR PARA LA PARTE SUPERIOR
		HBox topContainer = new HBox();
		topContainer.setSpacing(10);
		topContainer.setAlignment(Pos.CENTER);
		root.getChildren().add(topContainer);

		// CREAR UNA IMAGEN PARA EL POKÉMON RIVAL
//		Image rivalImage = new Image("img/Gif/machop.gif");
//		ImageView rivalImageView = new ImageView(rivalImage);
//		rivalImageView.setFitWidth(200);
//		rivalImageView.setPreserveRatio(true);
//		HBox.setMargin(rivalImageView, new Insets(0, 0, 0, 50)); // Margen izquierdo de 20px
//		topContainer.getChildren().add(rivalImageView);

		// CREAR UNA BARRA DE VIDA PARA EL POKÉMON RIVAL
		ProgressBar rivalHealthBar = new ProgressBar();
		rivalHealthBar.setPrefWidth(250);
		HBox.setMargin(rivalHealthBar, new Insets(0, 0, 0, 50)); // Margen izquierdo de 20px
		topContainer.getChildren().add(rivalHealthBar);

		// CREAR UN CONTENEDOR PARA LA PARTE INFERIOR
		HBox bottomContainer = new HBox();
		bottomContainer.setSpacing(10);
		bottomContainer.setAlignment(Pos.CENTER);
		root.getChildren().add(bottomContainer);

		// CREAR UNA IMAGEN PARA EL POKÉMON DEL JUGADOR
//		Image playerImage = new Image("img/Gif/psyduck.gif");
//		ImageView playerImageView = new ImageView(playerImage);
//		playerImageView.setFitWidth(300);
//		playerImageView.setPreserveRatio(true);
//		HBox.setMargin(playerImageView, new Insets(0, 20, 0, 0)); // Margen derecho de 20px
//		bottomContainer.getChildren().add(playerImageView);

		// CREAR UNA BARRA DE VIDA PARA EL POKÉMON DEL JUGADOR
		ProgressBar playerHealthBar = new ProgressBar();
		playerHealthBar.setPrefWidth(300);
		HBox.setMargin(playerHealthBar, new Insets(0, 20, 0, 0)); // Margen derecho de 20px
		bottomContainer.getChildren().add(playerHealthBar);

		// CREAR BOTONES PARA LOS MOVIMIENTOS DEL POKÉMON
		Button move1Button = new Button("Movimiento 1");
		Button move2Button = new Button("Movimiento 2");
		Button move3Button = new Button("Movimiento 3");
		Button move4Button = new Button("Movimiento 4");
		VBox movesContainer = new VBox();
		movesContainer.getChildren().addAll(move1Button, move2Button, move3Button, move4Button);
		movesContainer.setAlignment(Pos.BASELINE_RIGHT);
		movesContainer.setSpacing(10);
		bottomContainer.getChildren().add(movesContainer);
		// CREAR UN CAJÓN RECTANGULAR PARA MOSTRAR TEXTO
		Label textLabel = new Label("HOOOOOHOHOOHOOHOH");
		Rectangle textBackground = new Rectangle(SCENE_WIDTH - 20, 40);
		textBackground.setOpacity(0.3);
		StackPane textContainer = new StackPane(textBackground, textLabel);
		root.getChildren().add(textContainer);

		// MOSTRAR LA ESCENA
		Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}