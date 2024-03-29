package vidmot.pacman;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class Maze extends Pane {
    @FXML
    private Pacman fxPacman;

    @FXML
    private Group veggirGroup;

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

    public boolean erAVegg(){
        for (Node node : veggirGroup.getChildren()) {
            Rectangle wall = (Rectangle) node;
            if (fxPacman.getBoundsInParent().intersects(wall.getBoundsInParent())) {
                return true;
            }
        }
        return false;
    }



    /**
     * Setur stefnuna á pacman
     */
    public void setStefna(Stefna stefna){
        fxPacman.setStefna(stefna);
    }

    public Stefna getFyrriPacmanStefna(){
        return fxPacman.getFyrriStefna();
    }

    public int getPacmanLayoutX(){
        return (int) fxPacman.getLayoutX();
    }
    public int getPacmanLayoutY(){
        return (int) fxPacman.getLayoutY();
    }

    public void setPacmanLayoutX(int nuvX, int breyt){
        fxPacman.setLayoutX(nuvX + breyt);
    }
    public void setPacmanLayoutY(int nuvY, int breyt){
        fxPacman.setLayoutY(nuvY + breyt);
    }



    /**
     * Kallar á afram aðferðinni á pacman.
     */
    public void afram(){
        if(!erAVegg()){
            fxPacman.afram();
        }
    }

    public boolean erLoglegStefna(Stefna nuverandiStefna) {
        double pacmanX = fxPacman.getLayoutX();
        double pacmanY = fxPacman.getLayoutY();

        int nyttX = (int) pacmanX;
        int nyttY = (int) pacmanY;

        switch (nuverandiStefna) {
            case Stefna.UPP:
                nyttY += 5;
                break;
            case Stefna.NIDUR:
                nyttY -= 5;
                break;
            case Stefna.HAEGRI:
                nyttX -= 5;
                break;
            case Stefna.VINSTRI:
                nyttX += 5;
                break;
            default:
                return false;
        }

        if (nyttX < 0 || nyttX >= this.getWidth() || nyttY < 0 || nyttY >= this.getHeight()) {
            return false;
        }
        if (ennIVegg(nyttX, nyttY)) {
            return false;
        }
        return true;
    }

    public boolean ennIVegg(int x, int y){
        for (Node barn : veggirGroup.getChildren()) {
            Rectangle veggur = (Rectangle) barn;
            double minX = veggur.getLayoutX();
            double maxX = minX + veggur.getWidth();
            double minY = veggur.getLayoutY();
            double maxY = minY + veggur.getHeight();

            if (x >= minX && x < maxX && y >= minY && y < maxY) {
                return true;
            }
        }
        return false;
    }

}
