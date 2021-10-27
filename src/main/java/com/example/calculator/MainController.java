package com.example.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class MainController {
    String number1 = "", number2 = "", operation = "";
    boolean number1Dot = false, number2Dot = false, result = false;

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
        Node node = (Node) event.getSource();
        operation = (String) node.getUserData();
        if (number1.isEmpty()) operationLabel.setText("Invalid syntax (the first number is empty)");
        else operationLabel.setText("%s %s %s".formatted(number1, operation, number2));
    }

    @FXML
    protected void onEqualButtonClick() {
        if (number1.isEmpty() || number2.isEmpty() || operation.isEmpty() || result) return;
        double num1 = Double.parseDouble(number1);
        double num2 = Double.parseDouble(number2);
        double res = 0;
        switch (operation) {
            case "+" -> res = num1 + num2;
            case "-" -> res = num1 - num2;
            case "*" -> res = num1 * num2;
            case "/" -> res = num1 / num2;
            default -> operationLabel.setText("Invalid syntax");
        }

        operationLabel.setText("%s %s %s = %f".formatted(number1, operation, number2, res));
        number1 = "";
        number2 = "";
        operation = "";
        number1Dot = false;
        number2Dot = false;
        result = true;
    }

    @FXML
    protected void onDeleteButtonClick() {
        if (operation.isEmpty()) {
            if (number1.length() > 1)
                number1 = number1.substring(0, number1.length() - 1);
        } else {
            if (number2.length() > 1)
                number2 = number2.substring(0, number1.length() - 1);
        }
        operationLabel.setText("%s %s %s".formatted(number1, operation, number2));
    }

    @FXML
    protected void onClearButtonClick() {
        number1 = "";
        number2 = "";
        operation = "";
        number1Dot = false;
        number2Dot = false;
        operationLabel.setText("");
        result = false;
    }

    @FXML
    protected void onDotButtonClick() {
        if (operation.isEmpty() && !number1Dot) {
            number1 += ".";
            number1Dot = true;
        }
        if (!operation.isEmpty() && !number2Dot) {
            number2 += ".";
            number2Dot = true;
        }
        operationLabel.setText("%s %s %s".formatted(number1, operation, number2));
    }
}
