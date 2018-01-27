/*
 * Copyright (c) 2017, PIT-Hackathon 2017 and/or its affiliates. All rights reserved.
 */
package eu.hackathon.cheesecake;

import eu.hackathon.cheesecake.utils.internal.Time;

/**
 * This class extends the default Java thread and fits it for the application.
 * <p>
 * <p>This certain implementation of the thread class is required for the Application of the application.
 * This class includes the Main-Game-Loop and a Game-Timer for </p>
 *
 * @author Onur Alici
 * @version 1.0.0
 * @see Runnable
 */
public abstract class ApplicationRunnable implements Runnable, Structure {

    /**
     * The indicator if the runnable is running.
     */
    private volatile boolean isRunning = false;

    /**
     * The Constructor.
     */
    ApplicationRunnable() {

    }

    @Override
    public final void run() {
        start();

        double time;
        double lastTime = System.nanoTime() / 1000000000.0d;

        double deltaTime = 0.0d;

        while (isRunning()) {
            boolean isSleeping = true;

            time = System.nanoTime() / 1000000000.0d;

            deltaTime += time - lastTime;

            lastTime = time;

            while (deltaTime >= Time.deltaTime()) {
                deltaTime -= Time.deltaTime();

                tick();

                isSleeping = false;
            }

            if (isSleeping) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                draw(null);
            }
        }
        dispose();

        System.exit(0);
    }

    /**
     * Starts the ApplicationRunnable.
     * <p>
     * <p>This method starts the ApplicationRunnable as long as it is not running.
     * If it is already running, this method will have no effect.</p>
     */
    public final void start() {
        if (!isRunning()) {
            isRunning = true;
        }
    }

    /**
     * Starts the ApplicationRunnable.
     * <p>
     * <p>This method starts the ApplicationRunnable as long as it is not running.
     * If it is already running, this method will have no effect.</p>
     */
    public final void stop() {
        if (isRunning()) {
            isRunning = false;
        }
    }

    /**
     * Returns if the Runnable is running.
     * <p>
     * <p>This method returns a boolean which is set true while the Runnable is running.</p>
     *
     * @return If the Runnable is running.
     */
    public final boolean isRunning() {
        return isRunning;
    }
}
