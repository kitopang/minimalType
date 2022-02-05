package com.app.minimaltype;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class Controller {

    static int wordCount = 0;
    static int charCount = 0;
    static int errorCount = 0;
    static boolean firstRun = true;
    static boolean startOfWord = false;

    @FXML
    private TextFlow bottomText;

    @FXML
    private TextFlow topText;

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


    }

    public void initialize() {
        WordGroup words = displayWords();

        textInput.textProperty().addListener((observable, oldValue, newValue) -> {

            String currentWord = words.getWordGroup().get(getWordCount()).getWord();
            char lastLetter = newValue.charAt(newValue.length() - 1);
            Text currentText = words.getTextGroup(getWordCount());
            int wordLength = currentWord.length();

                if (lastLetter == ' ') {
                    incrementWordCount();
                    resetCharCount();
                    charCount = -1;
                    startOfWord = true;
                } else if (!firstRun) {
                    incrementCharCount();
                }
//            System.out.println(getCharCount());
//            System.out.println(getWordCount());
//            System.out.println(currentWord);
//            System.out.println(lastLetter);
//            System.out.println(currentWord.charAt(getCharCount()));

                if (charCount < wordLength - 1 && lastLetter != ' ' && lastLetter != currentWord.charAt(getCharCount())) {
                    currentText.setFill(Color.RED);
                }

                firstRun = false;
        });
    }



    public WordGroup displayWords() {
        String family = "Hiragino Sans GB W3";
        double size = 16;
        Color color = Color.WHITE;

        WordGroup words = new WordGroup();

        for(int i = 0; i < 10; i++) {
            Text text1 = new Text(words.getWordGroup().get(i).getWord() + " ");
            text1.setFont(Font.font(family, size));
            text1.setFill(color);
            topText.getChildren().addAll(text1);
            words.setTextGroup(text1);
        }

        for(int i = 10; i < 20; i++) {
            Text text1 = new Text(words.getWordGroup().get(i).getWord() + " ");
            text1.setFont(Font.font(family, size));
            text1.setFill(color);
            bottomText.getChildren().addAll(text1);
            words.setTextGroup(text1);
        }

        return words;
    }


    public void incrementWordCount() {
        wordCount++;
    }

    public void incrementCharCount() {
        charCount++;
    }

    public int getWordCount() {
        return wordCount;
    }

    public int getCharCount() {
        return charCount;
    }

    public void resetWordCount() {
        wordCount = 0;
    }

    public void resetCharCount() {
        charCount = 0;
    }




}
