package eu.hackathon.game.internal;

import eu.hackathon.cheesecake.Panel;
import eu.hackathon.cheesecake.external.ui.GUI;
import eu.hackathon.cheesecake.external.ui.GUIRect;
import eu.hackathon.cheesecake.internal.Manager;
import eu.hackathon.cheesecake.internal.controls.KeyCode;
import eu.hackathon.cheesecake.internal.controls.keyboard.Keyboard;
import eu.hackathon.cheesecake.internal.graphics.ExtendedGraphics;
import eu.hackathon.cheesecake.utils.external.SceneEvent;
import eu.hackathon.cheesecake.utils.external.SceneFadeType;
import eu.hackathon.game.GameHandler;
import eu.hackathon.game.utils.Resources;

public class MenuPanel extends Panel {
    public static final String KEY = "menu";

    private static int BACKGROUND = 0xff000000;

    private GUIRect logo, start, background;

    private double lastTime = System.currentTimeMillis();
    private double time = 0.0d;
    private boolean toggle = true;

    public MenuPanel(Manager manager) {
        super(MenuPanel.KEY, manager);
    }

    @Override
    public void init() {
        logo = new GUIRect((getManager().getWindow().getWidth() - Resources.LOGO.getWidth()) / 2, -64);
        start = new GUIRect((getManager().getWindow().getWidth() - Resources.FONT.getWidth("Start Adventure")) / 2, getManager().getWindow().getHeight() - 128, Resources.FONT.getWidth("Start Adventure"), Resources.FONT.getHeight());

        background = new GUIRect(0, 0, 0, 0);
    }

    @Override
    public void tick() {
        time += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if (time >= 600.0f) {
            toggle = !toggle;
            time -= 600.0f;
        }

        if (getManager().getScreen().isFading()) {
            return;
        }

        if (Keyboard.getKeyUp(KeyCode.ESCAPE)) {
            getManager().application.stop();
        }

        if (Keyboard.getKeyUp(KeyCode.SPACE) || Keyboard.getKeyUp(KeyCode.ENTER)) {
            Resources.CLICK_01.play();

            getManager().getScreen().request(new SceneEvent(SceneFadeType.OUT) {
                @Override
                public void execute() {
                    ((GameHandler) getManager()).getHelper().init();

                    getManager().getPanelHandler().setPanel(GamePanel.KEY);

                    getManager().getScreen().request(new SceneEvent(SceneFadeType.IN) {
                        @Override
                        public void execute() { /* ... */ }
                    });
                }
            });
        }
    }

    @Override
    public void draw(ExtendedGraphics extendedGraphics) {
        extendedGraphics.setBackgroundColor(MenuPanel.BACKGROUND);

        GUI.drawTexture(extendedGraphics, Resources.BACKGROUND, background);

        GUI.drawTexture(extendedGraphics, Resources.LOGO, logo);

        if (getManager().getScreen().isFading()) {
            return;
        }

        if (GUI.drawButton(extendedGraphics, "Start Adventure", Resources.FONT, 0xffffffff, start, toggle)) {
            Resources.CLICK_01.play();

            getManager().getScreen().request(new SceneEvent(SceneFadeType.OUT) {
                @Override
                public void execute() {
                    ((GameHandler) getManager()).getHelper().init();

                    getManager().getPanelHandler().setPanel(GamePanel.KEY);

                    getManager().getScreen().request(new SceneEvent(SceneFadeType.IN) {
                        @Override
                        public void execute() { /* ... */ }
                    });
                }
            });
        }
    }

    @Override
    public void load() {
        getManager().getAudioSource().add(Resources.THEME);

        lastTime = System.currentTimeMillis();
        time = 0.0d;
    }

    @Override
    public void unload() {
        getManager().getAudioSource().clear();
    }

    @Override
    public void dispose() {
        unload();
    }
}
