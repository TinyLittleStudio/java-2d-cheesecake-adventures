package eu.hackathon.cheesecake.utils.external;


import eu.hackathon.cheesecake.Application;
import eu.hackathon.cheesecake.internal.controls.KeyCode;
import eu.hackathon.cheesecake.internal.controls.keyboard.Keyboard;
import eu.hackathon.cheesecake.internal.controls.mouse.Mouse;
import eu.hackathon.cheesecake.internal.graphics.ExtendedGraphics;
import eu.hackathon.cheesecake.internal.graphics.Image;
import eu.hackathon.cheesecake.utils.MathUtils;
import eu.hackathon.cheesecake.utils.internal.Time;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private static List<SceneEvent> requests = new ArrayList<SceneEvent>();
    private static boolean nextRequest = false;

    private static int color = 0xff000000;
    private static boolean isFading = false;
    private static SceneFadeType sceneFadeType = null;

    private Image splash = null;
    private boolean isSplashPlaying = false;
    private double time = 0.0d, lastTime = System.currentTimeMillis();
    private int duration = 8;
    private int velocity = 200;
    private int bgColor = 0xff000000;
    private boolean isCancelable = false;

    private Application application;

    public Scene(Application application) {
        this.application = application;
    }

    public void init() {
        if (splash != null) {
            isSplashPlaying = true;
        }

        request(new SceneEvent(SceneFadeType.IN) {
            @Override
            public void execute() { /* ... */ }
        });
    }

    public void tick() {
        if (isFading() || isSplashPlaying()) {
            Mouse.disable();
        } else {
            Mouse.enable();
        }

        if (requests.size() > 0) {
            if (!nextRequest) {
                if (!isFading) {
                    Scene.sceneFadeType = requests.get(0).getSceneFadeType();

                    if (Scene.sceneFadeType != null) {
                        Scene.isFading = true;

                        color = sceneFadeType.getInitialColor();
                    }
                }
                nextRequest = true;
            } else {
                if (isFinished()) {
                    requests.get(0).execute();
                    requests.remove(0);

                    nextRequest = false;
                }
            }
        }

        if (isCancelable) {
            if (!isFading) {
                if (Keyboard.getKeyDown(KeyCode.ENTER) || Keyboard.getKeyDown(KeyCode.SPACE)) {
                    time = duration * 1000;
                }
            }
        }

        if (isSplashPlaying) {
            time += System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();

            if (time / 1000 >= duration) {
                request(new SceneEvent(SceneFadeType.OUT) {
                    @Override
                    public void execute() {
                        isSplashPlaying = false;

                        request(new SceneEvent(SceneFadeType.IN) {
                            @Override
                            public void execute() {
                                // ...
                            }
                        });
                    }
                });
                time = 0.0d;
            }
        }

        if (isFading) {
            color = (color >> 24) & 0xff;

            color += (Time.deltaTime() * velocity) * sceneFadeType.multiplier();

            color = MathUtils.clamp(color, 0, 255);

            if ((sceneFadeType.equals(SceneFadeType.OUT) && color == 255) || (sceneFadeType.equals(SceneFadeType.IN) && color == 0)) {
                isFading = false;
            }
            color = (color << 24);
        }
    }

    public void draw(ExtendedGraphics extendedGraphics) {
        if (isSplashPlaying) {
            extendedGraphics.fillRect(bgColor, 0, 0, application.getWindow().getWidth(), application.getWindow().getHeight());
            extendedGraphics.drawImage(splash, (application.getWindow().getWidth() - splash.getWidth()) / 2, (application.getWindow().getHeight() - splash.getHeight()) / 2);
        }
        extendedGraphics.fillRect(color, 0, 0, application.getWindow().getWidth(), application.getWindow().getHeight());
    }

    public void setSplashScreen(Image image) {
        this.splash = image;
    }

    public void setSplashScreenBackgroundColor(int color) {
        this.bgColor = color;
    }

    public boolean isSplashPlaying() {
        return isSplashPlaying;
    }

    public boolean isFading() {
        return isFading;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setCancelable(boolean isCancelable) {
        this.isCancelable = isCancelable;
    }

    public boolean isCancelable() {
        return isCancelable;
    }

    public boolean isFinished() {
        int alpha = (color >> 24) & 0xff;

        if (!isFading) {
            if (Scene.sceneFadeType != null) {
                if (Scene.sceneFadeType.equals(SceneFadeType.IN)) {
                    return alpha == 0;
                }

                if (Scene.sceneFadeType.equals(SceneFadeType.OUT)) {
                    return alpha == 255;
                }
            }
        }
        return false;
    }

    public void request(SceneEvent sceneEvent) {
        if (!requests.contains(sceneEvent)) {
            requests.add(sceneEvent);
        }
    }
}
