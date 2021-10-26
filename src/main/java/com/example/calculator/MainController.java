package com.example.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class MainController {
    String number1 = "", number2 = "", operation = "";

    @FXML
    private Label operationLabel;

    @FXML
    protected void onNumberButtonClick(ActionEvent event) {
        Node node = (Node) event.getSource();
        String userData = (String) node.getUserData();
        if (operation.isEmpty()) number1 += userData;
        else number2 += userData;
        operationLabel.setText("%s %s %s".formatted(number1, operation, number2));
    }

    @FXML
    protected void onOperationButtonClick(ActionEvent event) {
        if (number1.isEmpty()) {
            operationLabel.setText("Invalid syntax (the first number is empty)");
        } else {
            Node node = (Node) event.getSource();
            operation = (String) node.getUserData();
            operationLabel.setText("%s %s %s".formatted(number1, operation, number2));
        }
    }

    @FXML
    public void onEqualButtonClick() {
        if (number1.isEmpty() || number2.isEmpty() || operation.isEmpty()) {
            operationLabel.setText("Invalid syntax");
        }
        int num1 = Integer.parseInt(number1);
        int num2 = Integer.parseInt(number2);
        int result = 0;
        switch (operation) {
            case "+" -> result = num1 + num2;
            case "-" -> result = num1 - num2;
            case "*" -> result = num1 * num2;
            case "/" -> result = num1 / num2;
            default -> operationLabel.setText("Invalid syntax");
        }

        operationLabel.setText("%s %s %s = %d".formatted(number1, operation, number2, result));
        number1 = "";
        number2 = "";
        operation = "";
    }

    @FXML
    public void onDeleteButtonClick() {
        if (operation.isEmpty()) {
            if (number1.length() > 1)
                number1 = number1.substring(0, number1.length() - 1);
        } else {
            if (number2.length() > 1)
                number2 = number2.substring(0, number1.length() - 1);
        }
        operationLabel.setText("%s %s %s".formatted(number1, operation, number2));
    }
}
