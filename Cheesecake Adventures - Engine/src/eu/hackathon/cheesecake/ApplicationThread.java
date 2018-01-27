/*
 * Copyright (c) 2017, PIT-Hackathon 2017 and/or its affiliates. All rights reserved.
 */
package eu.hackathon.cheesecake;

/**
 * This class extends the default Java thread and fits it for the application.
 * <p>
 * <p>This certain implementation of the thread class is required for the Application of the application.
 * This class includes the Main-Game-Loop and a Game-Timer for </p>
 *
 * @author Onur Alici
 * @version 1.0.0
 * @see Thread
 */
public class ApplicationThread extends Thread {

    /**
     * For autonumbering threads.
     */
    private static int nextThreadNumb = 0;

    /**
     * The Runnable implementation as reference.
     */
    private ApplicationRunnable applicationRunnable;

    /**
     * The Constructor.
     * <p>
     * <p>This Constructor creates a thread which uses a predefined ApplicationRunnable from the
     * application.</p>
     *
     * @param applicationRunnable Requires an ApplicationRunnable to execute.
     */
    ApplicationThread(ApplicationRunnable applicationRunnable) {
        this(applicationRunnable, "Cheesecake-Thread", false);
    }

    /**
     * The Constructor.
     * <p>
     * <p>This Constructor creates a thread which uses a predefined ApplicationRunnable from the
     * application.</p>
     *
     * @param applicationRunnable is required to execute.
     * @param threadName          the name of the thread.
     */
    ApplicationThread(ApplicationRunnable applicationRunnable, String threadName) {
        this(applicationRunnable, threadName, false);
    }

    /**
     * The Constructor.
     * <p>
     * <p>This Constructor creates a thread which uses a predefined ApplicationRunnable from the
     * application.</p>
     *
     * @param applicationRunnable is required to execute.
     * @param threadName          the name of the thread.
     * @param isDaemon            if it is a Daemon thread.
     */
    ApplicationThread(ApplicationRunnable applicationRunnable, String threadName, boolean isDaemon) {
        super(applicationRunnable);

        this.applicationRunnable = applicationRunnable;

        init(threadName, isDaemon);
    }

    /**
     * Method initializes base-values.
     * <p>
     * <p>This method is mostly for initialization purposes.</p>
     */
    private void init(String threadName, boolean isDaemon) {
        setName(threadName + "-" + nextThreadNumb());

        setDaemon(isDaemon);

        setPriority(Thread.NORM_PRIORITY);
    }

    /**
     * Opens the ApplicationThread.
     * <p>
     * <p>This method opens the ApplicationThread and starts the ApplicationRunnable.</p>
     * <p>This method is package-private.</p>
     */
    final void open() {
        start();
    }

    /**
     * Closes the ApplicationThread.
     * <p>
     * <p>This method closes the ApplicationThread and stops the ApplicationRunnable.</p>
     * <p>This method is package-private.</p>
     */
    final void close() {
        applicationRunnable.stop();
    }

    /**
     * For auto-numbering anonymous threads.
     */
    private int nextThreadNumb() {
        return nextThreadNumb++;
    }
}
