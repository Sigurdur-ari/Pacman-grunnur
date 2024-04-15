package vidmot.pacman;

/**
 * Klasi fenginn úr goldrush verkefni
 */
public enum View {
    MENU("menu-view.fxml"),
    DIALOG("dialog.fxml"), LEIKUR("leikur-view.fxml");


    private final String fileName;

    View(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
