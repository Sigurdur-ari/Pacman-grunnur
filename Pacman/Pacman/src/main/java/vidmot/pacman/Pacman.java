package vidmot.pacman;

import javafx.fxml.FXMLLoader;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class Pacman extends Circle {
    private Stefna stefna = Stefna.HAEGRI;
    private Stefna fyrriStefna;
    private final int HRADI = 1;

    /**
     * Les inn custom componentinn pacman þegar maze skráin er búin til. Frumstillir stefnuna á honum
     * til hægri.
     */
    public Pacman(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pacman-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        setStefna(Stefna.HAEGRI);
    }

    /**
     * Aðferð sem færir pacman áfram eftir hver stefnan er. Loopar frá hægri til vinstri og öfugt
     * en engin mörk eru eins og er á upp og niður færslunni. Tekur tillit til þess að pacman byrjar í
     * stöðunni x=400 og y=400 (sjá pacman-view.fxml).
     */
    public void afram(){
        Maze parent = (Maze) this.getParent();

        double newX = this.getCenterX() + Math.cos(Math.toRadians(getStefnaGradur())) * HRADI;
        if (newX < -20) {
            newX = parent.getWidth() - 21;
        } else if (newX > parent.getWidth()-20) {
            newX = -20;
        }
        setCenterX(newX);

        setCenterY((int) (this.getCenterY() - Math.sin(Math.toRadians(getStefnaGradur())) * HRADI) % parent.getHeight());
    }

    public double getStefnaGradur() {
        return stefna.getGradur();
    }

    public void setStefna(Stefna stefna) {
        fyrriStefna = this.stefna;
        this.stefna = stefna;
    }

    public Stefna getFyrriStefna() {
        return fyrriStefna;
    }
}
