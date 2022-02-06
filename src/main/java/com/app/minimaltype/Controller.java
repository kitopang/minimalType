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
    static boolean firstRowRun = true;
    static WordGroup wordGroup;

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
        wordGroup = displayWords();

        textInput.textProperty().addListener((observable, oldValue, newValue) -> {
            char lastLetter = newValue.charAt(newValue.length() - 1);
            Text currentText = wordGroup.getTextGroup(getWordCount());
            String currentWord = wordGroup.getWordGroup().get(getWordCount()).getWord();
            int wordLength = currentWord.length();

                if (lastLetter == ' ') {
                    incrementWordCount();
                    resetCharCount();
                    charCount = -1;
                    startOfWord = true;
                    if (getWordCount() == 10 ) {
                        resetWordCount();
                        wordGroup = updateWords(wordGroup);
                    }


                } else if (!firstRun) {
                    incrementCharCount();
                }


                System.out.println(currentWord);
                if (charCount < wordLength - 1 && lastLetter != ' ' && lastLetter != currentWord.charAt(getCharCount())) {
                    currentText.setFill(Color.RED);
                }

                else if (lastLetter != ' ' ) {
                    wordGroup.getTextGroup(getWordCount()).setFill(Color.WHITE);
                }

                firstRun = false;


        });
    }



    public WordGroup displayWords() {
        String family = "Hiragino Sans GB W3";
        double size = 16;
        Color color = Color.GRAY;

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

    public WordGroup updateWords(WordGroup oldWords) {
        String family = "Hiragino Sans GB W3";
        double size = 16;
        Color color = Color.GRAY;

        WordGroup words = new WordGroup(oldWords);
        System.out.println(words);
        topText.getChildren().clear();
        bottomText.getChildren().clear();

        for(int i = 0; i < 10; i++) {
            Text text1 = oldWords.getTextGroup(i + 10);
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
