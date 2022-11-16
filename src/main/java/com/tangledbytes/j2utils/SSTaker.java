package com.tangledbytes.j2utils;

import java.io.File;

import static com.tangledbytes.j2utils.PhoneUtils.ADB;

public class SSTaker extends Thread {
    public static final File FILE_SCREENSHOT = new File("c:/Users/junai/Desktop/tmp/screenshot.png");
    private int speed = 50;
    private boolean pause;
    private boolean stop;

    @Override
    public void run() {
        setPause(false);
        setStop(false);
        while (!isStop()) {
            if (isPause())
                continue;
            sleepThread(10L * (110 - speed));
            takeSS();
        }
    }

    public void takeSS() {
        PhoneUtils.exec(ADB + " shell screencap -p /sdcard/screenshot.png");
        PhoneUtils.exec(ADB + " pull /sdcard/screenshot.png C:\\Users\\junai\\Desktop\\tmp");
    }


    public void sleepThread(long millis) {
        try {
            synchronized (this) {
                wait(millis);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
