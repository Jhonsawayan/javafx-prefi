package aclcbukidnon.com.javafxactivity.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CounterController {

    @FXML
    private Label labelCount;

    private int counter = 0; // Holds the current count

    @FXML
    protected void onPlusClick() {
        counter++;
        labelCount.setText(String.valueOf(counter));
    }

    @FXML
    protected void onMinusClick() {
        counter--;
        labelCount.setText(String.valueOf(counter));
    }
}