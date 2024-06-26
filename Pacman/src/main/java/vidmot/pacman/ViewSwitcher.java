package vidmot.pacman;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Klasi tekinn úr goldrush verkefni. Einni aðferð bætt við.
 */

public class ViewSwitcher {
    private static final Map<View, Parent> cache = new HashMap<>();

    private static final Map<View, Object> controllers = new HashMap<>();
    private static Scene scene;

    public static void setScene(Scene scene) {
        ViewSwitcher.scene = scene;
    }

    public static void switchTo(View view) {
        if (scene == null) {
            System.out.println("No scene was set");
            return;
        }

        try {
            Parent root;
            FXMLLoader loader = null;
            if (cache.containsKey(view)) {
                System.out.println("Loading from cache");
                root = cache.get(view);
            } else {
                System.out.println("Loading from FXML");
                loader = new FXMLLoader(ViewSwitcher.class.getResource(view.getFileName()));
                root = loader.load();

                cache.put(view, root);
                controllers.put(view, loader.getController());
            }
            scene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void switchTo(Object controller, View view) {
        try {
            Parent root;
            FXMLLoader loader = null;
            if (cache.containsKey(view)) {
                System.out.println("Loading from cache");
                root = cache.get(view);
            } else {
                System.out.println("Loading from FXML");
                loader = new FXMLLoader(ViewSwitcher.class.getResource(view.getFileName()));
                loader.setRoot(controller);
                loader.setController(controller);
                root = loader.load();
                cache.put(view, root);
                // scene.setRoot(root);
                controllers.put(view, loader.getController());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Object lookup(View v) {
        return controllers.get(v);
    }

    /**
     * Viðbótar aðferð sem breytir stærð gluggans sem er.
     * @param width breidd glugga
     * @param height hæð glugga.
     */
    public static void changeWindowSize(int width, int height) {
        if (scene != null && scene.getWindow() != null && scene.getWindow() instanceof Stage) {
            Stage stage = (Stage) scene.getWindow();
            stage.setWidth(width);
            stage.setHeight(height);
        } else {
            System.out.println("No scene or stage found to change window size.");
        }
    }
}
