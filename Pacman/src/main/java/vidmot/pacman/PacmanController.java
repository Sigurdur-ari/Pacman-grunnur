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
    public Maze fxLeikbord;

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
        fxLeikbord.getScene().addEventFilter(KeyEvent.ANY, this::stefna);
    }

    /**
     * Breytir stefnunni á leikborðinu (sem kallar svo á pacman) þannig að pacman breytir um stefnu.
     * Stefna fengin úr map í orvatakkar fallinu.
     */
    private void stefna(KeyEvent event){
        if(map.get(event.getCode()) != null){
            fxLeikbord.setStefna(map.get(event.getCode()));
        }
    }

    /**
     * Hefur loop leiksins sem lætur pacamn hreyfast sjálfur. Kallar á 20ms fresti á afram aðferðina sem
     * er skilgrein í maze þar sem kallað er á pacman. Timelineið hefur delay 3 sek til þess að gefa notanda
     * smá breather áður en leikur hefst. Keyrir endalaust.
     */
    public void hefjaLeik() {
        KeyFrame k = new KeyFrame(Duration.millis(20),
                e -> {
                    fxLeikbord.afram();
                });
        Timeline t = new Timeline(k);
        t.setDelay(Duration.seconds(3));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
    }
}
