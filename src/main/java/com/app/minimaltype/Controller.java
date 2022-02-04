package com.app.minimaltype;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class Controller {

 /*
    @FXML
    void btnDark(ActionEvent event) {
        String family = "Helvetica";
        double size = 50;
        Text text1 = new Text("Hello ");
        text1.setFont(Font.font(family, size));
        text1.setFill(Color.RED);
        topText.getChildren().addAll(text1);

    }
    */

    @FXML
    private TextFlow bottomText;

    @FXML
    private TextFlow bottomText1;

    @FXML
    private TextField textInput;

    @FXML
    void btnDark(ActionEvent event) {
    }

    @FXML
    void textField(ActionEvent event) {
//        String input = textInput.getText();
//        System.out.println(input);
//
//        if(input.isEmpty()) {
//            System.out.println("yessir");
//        }

        textInput.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
        });
    }




}
