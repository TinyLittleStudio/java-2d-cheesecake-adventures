package eu.hackathon.game;

import eu.hackathon.cheesecake.Application;
import eu.hackathon.cheesecake.internal.Manager;
import eu.hackathon.cheesecake.internal.controls.KeyCode;
import eu.hackathon.cheesecake.internal.controls.keyboard.Keyboard;
import eu.hackathon.cheesecake.internal.graphics.ExtendedGraphics;
import eu.hackathon.cheesecake.utils.external.FileUtils;
import eu.hackathon.cheesecake.utils.external.SceneEvent;
import eu.hackathon.cheesecake.utils.external.SceneFadeType;
import eu.hackathon.game.external.Level;
import eu.hackathon.game.external.LevelData;
import eu.hackathon.game.internal.GamePanel;
import eu.hackathon.game.utils.external.ActionLog;
import eu.hackathon.game.utils.external.Helper;

import java.io.File;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class GameHandler extends Manager {
    private static GameHandler gameHandler;

    private List<LevelData> levels = new LinkedList<LevelData>();
    private int index = -1, next = -1;

    private Helper helper = new Helper();
    private ActionLog actionLog = new ActionLog();
    private Level level = null;

    private boolean isFinished = false;

    public GameHandler(Application application) {
        super(application);

        if (gameHandler == null) {
            GameHandler.gameHandler = this;
        } else {
            return;
        }

        File[] files = FileUtils.getFiles(LevelData.FOLDER);

        if (files != null) {
            for (File file : files) {
                String[] args = file.getName().split("\\.");

                if (args.length > 0) {
                    levels.add(new LevelData(args[0]));
                }
            }
        }
        levels.sort(Comparator.comparing(a -> a.file));

        reset();
    }

    @Override
    public void tick() {
        if (getScreen().isFading()) {
            return;
        }

        if (getPanelHandler().getPanel() instanceof GamePanel) {
            if (Keyboard.getKeyUp(KeyCode.F1)) {
                previous();
            }

            if (Keyboard.getKeyUp(KeyCode.F2)) {
                next();
            }

            if (Keyboard.getKeyUp(KeyCode.F5)) {
                reload();
            }
            actionLog.tick();
            helper.tick();
        }
    }

    @Override
    public void draw(ExtendedGraphics extendedGraphics) {
        if (getScreen().isFading()) {
            return;
        }

        if (getPanelHandler().getPanel() instanceof GamePanel) {
            actionLog.draw(extendedGraphics);
            helper.draw(extendedGraphics);
        }
    }

    public void next() {
        if (next == index) {
            index++;

            if (index > levels.size() - 1) {
                isFinished = true;
            }

            if (!isFinished) {
                reload();
            }
        }
    }

    public void previous() {
        if (next == index) {
            if (index > 0) {
                index--;
            }
            reload();
        }
    }

    public void reload() {
        getScreen().request(new SceneEvent(SceneFadeType.OUT) {
            @Override
            public void execute() {
                level.unload();

                level = new Level(levels.get(index));

                getScreen().request(new SceneEvent(SceneFadeType.IN) {
                    @Override
                    public void execute() {
                        next = index;

                        actionLog.cancel();

                        level.load();
                    }
                });
            }
        });
        helper.dispose();
        helper.init();
    }

    public void reset() {
        if (levels.size() > 0) {
            index = 0;
            next = 0;
            level = new Level(levels.get(index));
        }
        isFinished = false;

        helper.dispose();
        helper.init();
    }

    public final void setLevel(Level level) {
        this.level = level;
    }

    public final Level getLevel() {
        return level;
    }

    @Override
    public void dispose() {
        reset();
    }

    public boolean isFinished() {
        return isFinished;
    }

    public ActionLog getActionLog() {
        return actionLog;
    }

    public Helper getHelper() {
        return helper;
    }

    public static GameHandler instance() {
        return GameHandler.gameHandler;
    }
}
