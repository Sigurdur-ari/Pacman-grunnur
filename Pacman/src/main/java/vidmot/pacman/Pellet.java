package vidmot.pacman;

import javafx.fxml.FXMLLoader;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class Pellet extends Rectangle {

    public Pellet(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pellet-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
