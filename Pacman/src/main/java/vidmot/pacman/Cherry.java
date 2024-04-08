package vidmot.pacman;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class Cherry extends ImageView {

    public Cherry(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cherry-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
