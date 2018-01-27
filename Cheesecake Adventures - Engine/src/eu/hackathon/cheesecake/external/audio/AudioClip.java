/*
 * Copyright (c) 2017, PIT-Hackathon 2017 and/or its affiliates. All rights reserved.
 */
package eu.hackathon.cheesecake.external.audio;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class AudioClip implements Cloneable {
    private URL url;
    private AudioInputStream audioInputStream;
    private AudioFormat audioFormat;

    private DataLine.Info info;

    private Clip clip;
    private volatile boolean isPlaying;

    public AudioClip(String source, AudioType audioType) {
        try {
            this.url = this.getClass().getResource(source + audioType.format());

            this.audioInputStream = AudioSystem.getAudioInputStream(url);
            this.audioFormat = audioInputStream.getFormat();

            this.info = new DataLine.Info(Clip.class, audioFormat);
            this.clip = (Clip) AudioSystem.getLine(info);

            this.clip.addLineListener(e -> {
                if (e.getType().equals(LineEvent.Type.START)) {
                    isPlaying = true;
                }

                if (e.getType().equals(LineEvent.Type.STOP)) {
                    clip.stop();
                    clip.flush();
                    clip.setFramePosition(0);
                    isPlaying = false;
                }
            });

            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        play(false);
    }

    public void play(boolean isUnsafe) {
        if (!isUnsafe) {
            if (isPlaying) {
                return;
            }
            isPlaying = true;
        } else {
            if (isPlaying) {
                clip.stop();
            }
            clip.setFramePosition(0);
        }
        clip.start();
    }

    public void stop() {
        if (isPlaying) {
            clip.stop();
            isPlaying = false;
        }
    }

    public boolean isPlaying() {
        return isPlaying;
    }
}
