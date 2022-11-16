package com.tangledbytes.j2utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PhoneUtils {
    public static final String ADB = "c:/Users/junai/AppData/Local/Android/Sdk/platform-tools/adb.exe";

    public static void exec(String command) {
        try {
            Runtime runtime = Runtime.getRuntime();
            Process adb = runtime.exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(adb.getInputStream()));
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(adb.getErrorStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            while ((line = errorReader.readLine()) != null) {
                System.out.println(line);
            }
            adb.waitFor();
            reader.close();
            errorReader.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sendDragEvent(double[] scaledDragStartCoordinates, double[] scaledDragEndCoordinates) {
        exec(ADB + " shell input swipe " + scaledDragStartCoordinates[0] + " " + scaledDragStartCoordinates[1]
                + " " + scaledDragEndCoordinates[0] + " " + scaledDragEndCoordinates[1] + " 400");
    }

    public static void sendKeyEvent(double x, double y) {
        exec(ADB + " shell input tap " + x + " " + y);
    }

    public static void sendEvent(PhoneEvent event) {
        String keycodeEvent = switch (event) {
            case GO_BACK -> "KEYCODE_BACK";
            case GO_HOME -> "KEYCODE_HOME";
            case RECENTS -> "KEYCODE_APP_SWITCH";
        };
        exec(ADB + " shell input keyevent " + keycodeEvent);
    }

    public enum PhoneEvent {
        GO_BACK,
        GO_HOME,
        RECENTS
    }
}
