package vidmot.pacman;

import javafx.geometry.Bounds;
import javafx.scene.Node;

public class Collectables {

    public static boolean erAPellet(Node pacman, Node pellet) {
        Bounds pacmanBounds = pacman.getBoundsInParent();
        Bounds pelletBounds = pellet.getBoundsInParent();

        return pacmanBounds.intersects(pelletBounds);
    }

    public static boolean erACherry(Node pacman, Node cherry) {
        Bounds pacmanBounds = pacman.getBoundsInParent();
        Bounds cherryBounds = cherry.getBoundsInParent();

        return pacmanBounds.intersects(cherryBounds);
    }

    public static boolean erEkkiEinmana(Node pellet, Node notPellet) {
        Bounds pelletBounds = pellet.getBoundsInParent();
        Bounds notPelletBounds = notPellet.getBoundsInParent();

        return pelletBounds.intersects(notPelletBounds);
    }
}
