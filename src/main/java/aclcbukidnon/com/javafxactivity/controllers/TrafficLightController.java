package aclcbukidnon.com.javafxactivity.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class TrafficLightController {

    private enum TrafficLightColor {
        STOP,
        HOLD,
        GO
    }

    private TrafficLightColor currentColor = TrafficLightColor.STOP;
    private Timeline timeline;

    @FXML
    private Circle stopLight;
    @FXML
    private Circle holdLight;
    @FXML
    private Circle goLight;

    @FXML
    public void initialize() {
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(3), e -> onTimerChange())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        updateLights(); // Show initial state
    }

    @FXML
    private void onStartClick() {
        timeline.play();
    }

    public void onTimerChange() {
        // Cycle through traffic light states
        switch (currentColor) {
            case STOP:
                currentColor = TrafficLightColor.HOLD;
                break;
            case HOLD:
                currentColor = TrafficLightColor.GO;
                break;
            case GO:
                currentColor = TrafficLightColor.STOP;
                break;
        }
        updateLights();
    }

    private void updateLights() {
        // Reset all to gray
        stopLight.setFill(Color.GRAY);
        holdLight.setFill(Color.GRAY);
        goLight.setFill(Color.GRAY);

        // Set current light to its color
        switch (currentColor) {
            case STOP:
                stopLight.setFill(Color.RED);
                break;
            case HOLD:
                holdLight.setFill(Color.YELLOW);
                break;
            case GO:
                goLight.setFill(Color.GREEN);
                break;
        }
    }
}
