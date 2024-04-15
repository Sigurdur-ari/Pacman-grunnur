package vidmot.pacman;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DialogController {

    @FXML
    private Label lokastig;


    /**
     * Handler fyrir hætta takkan í dialoginum.
     */
    public void onHaetta(){
        System.exit(0);
    }

    /**
     * Setur lokastigin sem text í label á dialoginum.
     * @param stig Lokastig leiksins.
     */
    public void setjaLokastig(int stig){
        lokastig.setText(String.valueOf(stig));
    }
}
