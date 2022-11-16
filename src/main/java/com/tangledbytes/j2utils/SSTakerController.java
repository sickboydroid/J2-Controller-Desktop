package com.tangledbytes.j2utils;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static com.tangledbytes.j2utils.SSTaker.FILE_SCREENSHOT;

public class SSTakerController {
    /*
     * factor by which you need  scaled the image of widow to become the size of phone
     */
    private final double SCALE_FACTOR = 1.33333;
    private final SSTaker ssTaker = new SSTaker();
    public ImageView imageViewScreenshot;
    public Slider sliderSpeed;
    public Label labelSpeed;
    volatile boolean mIsDrag;
    double[] mDragStartCoordinates;
    long updateTime;

    @FXML
    public void ssClicked(MouseEvent event) {
        if (mIsDrag) {
            double[] scaledDragStartCoordinates = scaleUpValues(mDragStartCoordinates[0], mDragStartCoordinates[1]);
            double[] scaledDragEndCoordinates = scaleUpValues(event.getX(), event.getY());
            PhoneUtils.sendDragEvent(scaledDragStartCoordinates, scaledDragEndCoordinates);
            mIsDrag = false;
            return;
        }
        double[] scaledCoordinates = scaleUpValues(event.getX(), event.getY());
        PhoneUtils.sendKeyEvent(scaledCoordinates[0], scaledCoordinates[1]);
    }

    private double[] scaleUpValues(double x, double y) {
        return new double[]{x * SCALE_FACTOR, y * SCALE_FACTOR};
    }

    public void pause(ActionEvent event) {
        ssTaker.setPause(!ssTaker.isPause());
        Button pauseToggle = (Button) event.getSource();
        if (ssTaker.isPause()) pauseToggle.setText("Resume");
        else pauseToggle.setText("Pause");
    }

    public void initialize() {
        ssTaker.setDaemon(true);
        ssTaker.start();
        imageViewScreenshot.setSmooth(true);
        imageViewScreenshot.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mDragStartCoordinates = new double[]{event.getX(), event.getY()};
                mIsDrag = true;
            }
        });
        sliderSpeed.valueProperty().addListener(observable -> {
            ssTaker.setSpeed((int) sliderSpeed.getValue());
            labelSpeed.setText(String.valueOf(sliderSpeed.getValue()));
        });
        new Thread(() -> {
            while (!ssTaker.isStop()) {
                try {
                    if (FILE_SCREENSHOT.exists() && updateTime < FILE_SCREENSHOT.lastModified()) {
                        updateTime = FILE_SCREENSHOT.lastModified();
                        Image image = new Image(new FileInputStream(FILE_SCREENSHOT));
                        imageViewScreenshot.setImage(image);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void onClickBack(ActionEvent event) {
        PhoneUtils.sendEvent(PhoneUtils.PhoneEvent.GO_BACK);
    }

    public void onClickRecents(ActionEvent event) {
        PhoneUtils.sendEvent(PhoneUtils.PhoneEvent.RECENTS);
    }

    public void onClickHome(ActionEvent event) {
        PhoneUtils.sendEvent(PhoneUtils.PhoneEvent.GO_HOME);
    }
}
