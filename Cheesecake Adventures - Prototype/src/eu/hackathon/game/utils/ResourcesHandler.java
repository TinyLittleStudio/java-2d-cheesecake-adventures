package eu.hackathon.game.utils;

import eu.hackathon.cheesecake.utils.external.FileUtils;
import eu.hackathon.game.external.LevelData;

public final class ResourcesHandler {
    public static void init() {
        FileUtils.setRootDirectory(".Cheesecake Adventures");

        FileUtils.create(LevelData.FOLDER);

        for (int i = 0; i < 10; i++) {
            boolean result = FileUtils.install("/utils/data/level " + i + LevelData.FILE_EXTENSION, FileUtils.getTotalPath() + "/" + LevelData.FOLDER + "/");

            if (!result) {
                break;
            }
        }
        Resources.init();
    }
}
