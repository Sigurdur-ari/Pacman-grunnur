package vidmot.pacman;

import javafx.geometry.Bounds;
import javafx.scene.Node;

public class Collectables {

    /**
     * Aðferð sem athugar hvort pacman sé búinn að borða/kominn ofan á pellet.
     * @param pacman pacman á borðinu
     * @param pellet eitt pellet á borðinu
     * @return true ef pacman er á einu pellet.
     */
    public static boolean erAPellet(Node pacman, Node pellet) {
        Bounds pacmanBounds = pacman.getBoundsInParent();
        Bounds pelletBounds = pellet.getBoundsInParent();

        return pacmanBounds.intersects(pelletBounds);
    }

    /**
     * Aðferð sem athugar hvort pacman sé búinn að borða/kominn ofan á kirsuber.
     * @param pacman pacman á borðinu
     * @param cherry eitt kirsuber á borðinu
     * @return true ef pacman er á einu kirsuberi.
     */
    public static boolean erACherry(Node pacman, Node cherry) {
        Bounds pacmanBounds = pacman.getBoundsInParent();
        Bounds cherryBounds = cherry.getBoundsInParent();

        return pacmanBounds.intersects(cherryBounds);
    }

    /**
     * Aðferð sem athugar hvort pellet sé á stað þar sem eitthvað annað er
     * @param pellet eitt pellet á borði
     * @param notPellet eitthvað sem er ekki pellet. Þ.e. veggur eða pacman.
     * @return true ef pellet er inni í vegg.
     */
    public static boolean erEkkiEinmana(Node pellet, Node notPellet) {
        Bounds pelletBounds = pellet.getBoundsInParent();
        Bounds notPelletBounds = notPellet.getBoundsInParent();

        return pelletBounds.intersects(notPelletBounds);
    }
}
