/*
 * Copyright (c) 2017, PIT-Hackathon 2017 and/or its affiliates. All rights reserved.
 */
package eu.hackathon.cheesecake.external.audio;

import eu.hackathon.cheesecake.Structure;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AudioSource implements Structure {
    private volatile List<AudioClip> list = new CopyOnWriteArrayList<AudioClip>();
    private int index = 0;

    private Thread thread;
    private volatile boolean isRunning;

    public AudioSource() {

    }

    public void init() {
        this.isRunning = true;

        this.thread = new Thread(() -> {
            double lastTime = System.currentTimeMillis();
            double time = 0.0d;

            while (isRunning) {
                if (index < list.size()) {
                    if (!list.get(index).isPlaying()) {
                        time += System.currentTimeMillis() - lastTime;
                        lastTime = System.currentTimeMillis();

                        if (time / 1000 > 6) {
                            play(list.get(index));
                            index++;
                            time = 0.0d;
                        }
                    }
                } else {
                    index = 0;
                }

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void add(AudioClip audioClip) {
        list.add(audioClip);
    }

    public void play(AudioClip audioClip) {
        audioClip.play();
    }

    public void play(AudioClip audioClip, boolean unsafe) {
        audioClip.play(unsafe);
    }

    public void clear() {
        for (AudioClip audioClip : list) {
            audioClip.stop();
        }
        list.clear();

        index = 0;
    }

    @Override
    public void dispose() {
        isRunning = false;
    }
}
