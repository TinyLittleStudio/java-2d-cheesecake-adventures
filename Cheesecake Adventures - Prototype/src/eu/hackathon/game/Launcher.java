package eu.hackathon.game;

import eu.hackathon.cheesecake.Application;
import eu.hackathon.cheesecake.ApplicationConfiguration;
import eu.hackathon.cheesecake.internal.graphics.WindowConfiguration;
import eu.hackathon.cheesecake.utils.external.Scene;
import eu.hackathon.game.internal.GamePanel;
import eu.hackathon.game.internal.MenuPanel;
import eu.hackathon.game.utils.Resources;
import eu.hackathon.game.utils.ResourcesHandler;

public class Launcher extends Application {
    public static void main(String[] args) {
        ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration("Cheesecake Adventures", WindowConfiguration.WINDOW_704x640);

        Launcher launcher = new Launcher(applicationConfiguration);

        ResourcesHandler.init();

        GameHandler gameHandler = new GameHandler(launcher);

        MenuPanel menuPanel = new MenuPanel(gameHandler);
        GamePanel gamePanel = new GamePanel(gameHandler);

        launcher.add(menuPanel);
        launcher.add(gamePanel);

        launcher.setManager(gameHandler);

        launcher.setPanel(MenuPanel.KEY);

        launcher.start();
    }

    public Launcher() {
        super();
    }

    public Launcher(ApplicationConfiguration applicationConfiguration) {
        super(applicationConfiguration);
    }

    @Override
    public void init() {
        Scene scene = getScene();

        scene.setSplashScreen(Resources.SPLASH);
        scene.setSplashScreenBackgroundColor(0xff0d0d0d);
        scene.setCancelable(true);
        scene.setDuration(5);
        scene.setVelocity(800);
    }
}
