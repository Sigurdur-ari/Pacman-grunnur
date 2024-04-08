package vidmot.pacman;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class PacmanApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Scene s = new Scene(new Pane());
        ViewSwitcher.setScene (s);
        ViewSwitcher.switchTo(View.LEIKUR);
        PacmanController goldController = (PacmanController) ViewSwitcher.lookup(View.LEIKUR);
        stage.setTitle("PACMAN");
        stage.setScene(s);
        stage.show();

        //setja upp orvatakka og hefja leikinn.
        goldController.orvatakkar();
        goldController.hefjaLeik();
    }


    public static void main(String[] args) {
        launch();
    }
}
