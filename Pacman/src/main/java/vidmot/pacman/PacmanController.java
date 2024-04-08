package vidmot.pacman;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.util.HashMap;

public class PacmanController {
    private final HashMap<KeyCode, Stefna> map= new HashMap<KeyCode, Stefna>();

    @FXML
    private Maze fxMaze;

    public void initialize(){

    }

    /**
     * Setur upp örvatakka og eventfilter þegar ýtt er á örvatakka sem breytir þá stefnunni.
     */
    public void orvatakkar() {
        map.put(KeyCode.UP, Stefna.UPP);
        map.put(KeyCode.DOWN, Stefna.NIDUR);
        map.put(KeyCode.RIGHT, Stefna.HAEGRI);
        map.put(KeyCode.LEFT, Stefna.VINSTRI);
        fxMaze.getScene().addEventFilter(KeyEvent.ANY, this::stefna);
    }

    /**
     * Breytir stefnunni á leikborðinu (sem kallar svo á pacman) þannig að pacman breytir um stefnu.
     * Stefna fengin úr map í orvatakkar fallinu.
     */
    private void stefna(KeyEvent event){
        if(map.get(event.getCode()) != null && fxMaze.erLoglegStefna(map.get(event.getCode()))){
            fxMaze.setStefna(map.get(event.getCode()));
        }
        if(fxMaze.erAVegg()){
            switch (fxMaze.getFyrriPacmanStefna()) {
                case Stefna.UPP:
                    if(map.get(event.getCode()) != Stefna.UPP){
                        fxMaze.setPacmanLayoutY(fxMaze.getPacmanLayoutY(), +1);
                    }
                    break;
                case Stefna.NIDUR:
                    if(map.get(event.getCode()) != Stefna.NIDUR){
                        fxMaze.setPacmanLayoutY(fxMaze.getPacmanLayoutY(), -1);
                    }
                    break;
                case Stefna.HAEGRI:
                    if(map.get(event.getCode()) != Stefna.HAEGRI){
                        fxMaze.setPacmanLayoutX(fxMaze.getPacmanLayoutX(), -1);
                    }
                    break;
                case Stefna.VINSTRI:
                    if(map.get(event.getCode()) != Stefna.VINSTRI){
                        fxMaze.setPacmanLayoutX(fxMaze.getPacmanLayoutX(), +1);
                    }
                    break;
            }
        }
    }




    /**
     * Hefur loop leiksins sem lætur pacamn hreyfast sjálfur. Kallar á 20ms fresti á afram aðferðina sem
     * er skilgrein í maze þar sem kallað er á pacman. Timelineið hefur delay 3 sek til þess að gefa notanda
     * smá breather áður en leikur hefst. Keyrir endalaust.
     */
    public void hefjaLeik() {
        fxMaze.buaTilPellets(fxMaze);
        KeyFrame k = new KeyFrame(Duration.millis(10),
                e ->{
                    fxMaze.afram();
                    fxMaze.safnaPellet();
                    fxMaze.safnaCherry();

                });
        KeyFrame j = new KeyFrame(Duration.seconds(1),
                e ->{
                    fxMaze.minnkaTima();

                });
        Timeline t = new Timeline(k);
        Timeline y = new Timeline(j);
        t.setDelay(Duration.seconds(3));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
        y.setDelay(Duration.seconds(3));
        y.setCycleCount(5);
        y.setOnFinished(event -> {
            if (fxMaze.erLeikLokid()){
                System.out.println("stopp");
                t.stop();
                y.stop();
                ViewSwitcher.switchTo(View.DIALOG);
            }
        });
        y.play();
    }
}
