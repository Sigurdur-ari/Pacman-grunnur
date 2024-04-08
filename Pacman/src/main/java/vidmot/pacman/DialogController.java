package vidmot.pacman;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DialogController {

    @FXML
    private Label lokastig;


    public void onHaetta(){
        System.exit(0);
    }

    public void setjaLokastig(int stig){
        lokastig.setText(String.valueOf(stig));
    }
}
