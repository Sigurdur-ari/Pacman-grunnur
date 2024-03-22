package vidmot.pacman;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Maze extends Pane {
    @FXML
    private Pacman fxPacman;

    /**
     * Les inn FXML skránna fyrir Maze, til að gera tvö borð væri hægt að útfæra Maze1 og Maze2 t.d.
     * og hafa þá svipaða skrá sem heldur bara utan um sitt hvort mazeið.
     */
    public Maze(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("maze-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Setur stefnuna á pacman
     */
    public void setStefna(Stefna stefna){
        fxPacman.setStefna(stefna);
    }

    /**
     * Kallar á afram aðferðinni á pacman.
     */
    public void afram(){
        fxPacman.afram();
    }
}
