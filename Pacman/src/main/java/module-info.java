module vidmot.pacman {
    requires javafx.controls;
    requires javafx.fxml;


    opens vidmot.pacman to javafx.fxml;
    exports vidmot.pacman;
}