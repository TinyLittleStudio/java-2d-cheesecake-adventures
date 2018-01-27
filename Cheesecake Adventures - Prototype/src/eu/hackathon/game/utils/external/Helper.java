package eu.hackathon.game.utils.external;

import eu.hackathon.cheesecake.Structure;
import eu.hackathon.cheesecake.internal.controls.KeyCode;
import eu.hackathon.cheesecake.internal.controls.keyboard.Keyboard;
import eu.hackathon.cheesecake.internal.graphics.ExtendedGraphics;
import eu.hackathon.game.GameHandler;

public class Helper implements Structure {
    public static final int DELAYED = 60000;

    private double lastTime = System.currentTimeMillis();
    private double time = 0.0d;

    private boolean isInitialized = false;

    @Override
    public void init() {
        lastTime = System.currentTimeMillis();
        time = 0.0;
    }

    @Override
    public void tick() {
        if (isInitialized) {
            return;
        }

        if (Keyboard.getKeyUp(KeyCode.F3)) {
            time = Helper.DELAYED + 1;
        }

        if (GameHandler.instance().getLevel() != null) {
            time += System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();

            if (time > Helper.DELAYED) {
                Material material = Material.find(GameHandler.instance().getLevel().key());

                if (material != null) {
                    ActionLog.request("Hint: " + material.getDisplayName(), 5000);
                }
                isInitialized = true;

                time -= Helper.DELAYED;
            }
        }
    }

    @Override
    public void draw(ExtendedGraphics extendedGraphics) {

    }

    @Override
    public void dispose() {
        isInitialized = false;
    }
}
