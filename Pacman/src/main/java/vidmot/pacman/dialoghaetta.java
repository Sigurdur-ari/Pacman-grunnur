package vidmot.pacman;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;


public class dialoghaetta {

    private static final String I_LAGI = "Í lagi";
    public static final ButtonType BTYPE = new ButtonType(I_LAGI,
            ButtonBar.ButtonData.OK_DONE);
    private static final String LOKA = "Loka";
    public static final ButtonType HTYPE = new ButtonType(LOKA,
            ButtonBar.ButtonData.OK_DONE);


    public static Alert dialoghaetta() {
        Alert a = new Alert(Alert.AlertType.NONE,  "Leikurinn er búinn", BTYPE, HTYPE);
        a.setTitle("Leik lokið");
        a.setHeaderText("Leik Lokið");
        return a;
    }



}
