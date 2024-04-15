package vidmot.pacman;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class PacmanApplication extends Application {
    @Override
    /**
     * Setur senuna og skiptir í leikborðs view. Setur upp örvatakka og hefur leikinn.
     */
    public void start(Stage stage) throws IOException {
        Scene s = new Scene(new Pane());
        ViewSwitcher.setScene (s);
        ViewSwitcher.switchTo(View.LEIKUR);
        PacmanController pacmanController = (PacmanController) ViewSwitcher.lookup(View.LEIKUR);
        stage.setTitle("PACMAN");
        stage.setScene(s);
        stage.show();

        //setja upp orvatakka og hefja leikinn.
        pacmanController.orvatakkar();
        pacmanController.hefjaLeik();
    }


    public static void main(String[] args) {
        launch();
    }
}
