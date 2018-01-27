package eu.hackathon.game.internal;

import eu.hackathon.cheesecake.Panel;
import eu.hackathon.cheesecake.internal.Manager;
import eu.hackathon.cheesecake.internal.controls.KeyCode;
import eu.hackathon.cheesecake.internal.controls.keyboard.Keyboard;
import eu.hackathon.cheesecake.internal.graphics.ExtendedGraphics;
import eu.hackathon.cheesecake.utils.external.SceneEvent;
import eu.hackathon.cheesecake.utils.external.SceneFadeType;
import eu.hackathon.game.GameHandler;
import eu.hackathon.game.utils.Resources;

public class GamePanel extends Panel {
    public static final String KEY = "game";

    private boolean init = false;
    private double time = 0.0d;
    private double lastTime = System.currentTimeMillis();

    public GamePanel(Manager manager) {
        super(GamePanel.KEY, manager);
    }

    @Override
    public void tick() {
        if (!GameHandler.instance().isFinished()) {
            GameHandler.instance().getLevel().tick();
        } else {
            if (Keyboard.getKeyUp(KeyCode.SPACE) || Keyboard.getKeyUp(KeyCode.ENTER)) {
                time = 5001;
            }

            if (!init) {
                time = 0.0d;
                lastTime = System.currentTimeMillis();

                init = true;
            }
            time += System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();

            if (time > 5000) {
                getManager().getScreen().request(new SceneEvent(SceneFadeType.OUT) {
                    @Override
                    public void execute() {
                        getManager().getPanelHandler().setPanel(MenuPanel.KEY);

                        getManager().getScreen().request(new SceneEvent(SceneFadeType.IN) {
                            @Override
                            public void execute() { /* ... */ }
                        });
                    }
                });
                init = false;

                time = 0;
            }
        }
    }

    @Override
    public void draw(ExtendedGraphics extendedGraphics) {
        if (!GameHandler.instance().isFinished()) {
            GameHandler.instance().getLevel().draw(extendedGraphics);
        } else {
            extendedGraphics.drawImage(Resources.BACKGROUND, 0, 0);
            extendedGraphics.drawImage(Resources.GAME_WON, (getManager().getWindow().getWidth() - Resources.LOGO.getWidth()) / 2, -64);
        }
    }

    @Override
    public void load() {
        GameHandler.instance().getLevel().load();
    }

    @Override
    public void unload() {
        GameHandler.instance().getLevel().unload();

        GameHandler.instance().dispose();

        getManager().getAudioSource().clear();
    }

    @Override
    public void dispose() {
        unload();
    }
}
