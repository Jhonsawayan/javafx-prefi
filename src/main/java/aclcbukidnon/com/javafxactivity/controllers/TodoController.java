package aclcbukidnon.com.javafxactivity.controllers;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TodoController {

    @FXML
    private ListView<String> todoList;

    @FXML
    public void initialize() {
        ObservableList<String> items = FXCollections.observableArrayList("Remove Me");
        todoList.setItems(items);
        todoList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        todoList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                // Optional: trigger edit dialog on selection
                // onListEdit();
            }
        });
    }

    @FXML
    protected void onCreateClick() {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Create New Todo");
        dialog.setHeaderText("Enter a new task");

        dialog.showAndWait().ifPresent(text -> {
            if (!text.trim().isEmpty()) {
                todoList.getItems().add(text);
            }
        });
    }

    @FXML
    protected void onDeleteClick() {
        String selectedItem = todoList.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            showAlert("No item selected", "Please select a todo to delete.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Delete");
        confirm.setHeaderText("Are you sure you want to delete:");
        confirm.setContentText(selectedItem);

        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                todoList.getItems().remove(selectedItem);
            }
        });
    }

    @FXML
    protected void onListEdit() {
        String selectedItem = todoList.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            showAlert("No item selected", "Please select a todo to edit.");
            return;
        }

        TextInputDialog dialog = new TextInputDialog(selectedItem);
        dialog.setTitle("Edit Todo");
        dialog.setHeaderText("Update the selected task");

        dialog.showAndWait().ifPresent(updatedText -> {
            if (!updatedText.trim().isEmpty()) {
                int index = todoList.getItems().indexOf(selectedItem);
                todoList.getItems().set(index, updatedText);
            }
        });
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}