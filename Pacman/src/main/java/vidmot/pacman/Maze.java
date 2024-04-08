package vidmot.pacman;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.Iterator;

public class Maze extends Pane {
    @FXML
    private Pacman fxPacman;

    @FXML
    private Maze fxMaze;

    @FXML
    private Group veggirGroup;

    @FXML
    private Group pelletGroup;

    @FXML
    private Group cherryGroup;

    @FXML
    private Label fxStig;


    @FXML
    private Label fxTimi;

    private IntegerProperty Stig = new SimpleIntegerProperty(0);

    private IntegerProperty Timi = new SimpleIntegerProperty(120);

    public int getStig() {
        return Stig.get();
    }

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
        fxStig.textProperty().bind(Stig.asString("Stig: %d"));
        fxTimi.textProperty().bind(Timi.asString("Tími: %d"));

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

    public void safnaPellet() {
        Iterator<Node> iterator = pelletGroup.getChildren().iterator();
        while (iterator.hasNext()) {
            Node pellet = iterator.next();
            if (Collectables.erAPellet(fxPacman, pellet)) {
                iterator.remove();
                gefaStig(100);
            }
        }
    }

    public void safnaCherry() {
        Iterator<Node> iterator = cherryGroup.getChildren().iterator();
        while (iterator.hasNext()) {
            Node cherry = iterator.next();
            if (Collectables.erACherry(fxPacman, cherry)) {
                iterator.remove();
                gefaStig(500);
            }
        }
    }

    public void buaTilPellets(Maze fxMaze) {
        for (int x = 0; x <= 800; x += 25) {
            for (int y = 0; y <= 800; y += 25) {
                Iterator<Node> veggirIterator = veggirGroup.getChildren().iterator();
                Iterator<Node> cherryIterator = cherryGroup.getChildren().iterator();
                Pellet newPellet = new Pellet();
                newPellet.setLayoutX(x);
                newPellet.setLayoutY(y);
                pelletGroup.getChildren().add(newPellet);
                while (veggirIterator.hasNext()) {
                    Node veggir = veggirIterator.next();
                    if (Collectables.erEkkiEinmana(newPellet, veggir)) {
                        pelletGroup.getChildren().remove(newPellet);
                    }
                }
                while (cherryIterator.hasNext()) {
                    Node cherry = cherryIterator.next();
                    if (Collectables.erEkkiEinmana(newPellet, cherry)) {
                        pelletGroup.getChildren().remove(newPellet);
                    }
                }
                if (Collectables.erAPellet(fxPacman,newPellet)) {
                    pelletGroup.getChildren().remove(newPellet);
                }
            }
        }
    }

    private void gefaStig(int gefinnStig) {
        Stig.set(Stig.get() + gefinnStig);
    }

    public void minnkaTima() {
        Timi.set(Timi.get() - 1);
    }

    public boolean erLeikLokid() {
        return Timi.get() == 0;
    }
}
