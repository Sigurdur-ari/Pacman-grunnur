package vidmot.pacman;

public enum View {
    MENU("menu-view.fxml"),

    LEIKUR("leikur-view.fxml");


    private final String fileName;

    View(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
