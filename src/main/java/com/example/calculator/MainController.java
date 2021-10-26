package com.example.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;

public class MainController {
    String number1 = "", number2 = "", operation = "";

    @FXML
    private Label operationLabel;

    @FXML
    protected void onNumberButtonClick(ActionEvent event) {
        Node node = (Node) event.getSource();
        String userData = (String) node.getUserData();
        handleDigitPress(userData);
    }

    @FXML
    protected void onOperationButtonClick(ActionEvent event) {
        Node node = (Node) event.getSource();
        operation = (String) node.getUserData();
        handleOperationPress(operation);
    }

    @FXML
    protected void onEqualButtonClick() {
        calculate();
    }

    @FXML
    protected void onDeleteButtonClick() {
        handleDeletePress();
    }

    @FXML
    protected void onKeyPress(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case DIGIT0, DIGIT1, DIGIT2, DIGIT3, DIGIT4, DIGIT5, DIGIT6, DIGIT7, DIGIT8, DIGIT9, NUMPAD0, NUMPAD1, NUMPAD2, NUMPAD3, NUMPAD4, NUMPAD5, NUMPAD6, NUMPAD7, NUMPAD8, NUMPAD9 -> handleDigitPress(keyEvent.getText());
            case ADD, SUBTRACT, SLASH, MULTIPLY -> handleOperationPress(keyEvent.getText());
            case EQUALS, ENTER -> calculate();
            case DELETE, BACK_SPACE -> handleDeletePress();
        }
    }

    private void handleDigitPress(String digit) {
        if (operation.isEmpty()) number1 += digit;
        else number2 += digit;
        operationLabel.setText("%s %s %s".formatted(number1, operation, number2));
    }

    private void handleOperationPress(String _operation) {
        operation = _operation;
        if (number1.isEmpty()) operationLabel.setText("Invalid syntax (the first number is empty)");
        else operationLabel.setText("%s %s %s".formatted(number1, operation, number2));
    }

    private void calculate() {
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

    private void handleDeletePress() {
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
