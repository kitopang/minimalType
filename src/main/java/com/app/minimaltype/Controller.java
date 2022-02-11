package com.app.minimaltype;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    private ChangeListener<String> textListner;

    static int wordCount = 0;
    static int charCount = 0;
    static int correctWordCount = 0;
    static boolean firstRun = true;
    static boolean startOfWord = false;
    static WordGroup wordGroup;
    boolean timerCanceled = false;

    @FXML
    private TextFlow bottomText;

    @FXML
    private TextFlow topText;

    @FXML
    private TextField textInput;

    @FXML
    private Text timerText;

    @FXML
    void btnDark(ActionEvent event) {
    }

    @FXML
    void textField(ActionEvent event) {
    }

    @FXML
    void btnReset(ActionEvent event) {
        textInput.textProperty().removeListener(textListner);
        timerCanceled = true;
        runProgram();
    }

    public void initialize() {
        runProgram();
    }

    public void runProgram() {
        wordCount = 0;
        correctWordCount = 0;
        charCount = 0;
        firstRun = true;
        startOfWord = false;

        topText.getChildren().clear();
        bottomText.getChildren().clear();
        textInput.clear();

        wordGroup = displayWords();
        textListner = (observable, oldValue, newValue) -> {
            char lastLetter = ' ';

            if(newValue.length() > 0) {
                lastLetter = newValue.charAt(newValue.length() - 1);
            }
            if (newValue.length() > 0 && firstRun) {
                timerCanceled = false;
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    int i = 30;

                    public void run() {
                        timerText.setText(String.valueOf(i));
                        i--;

                        if(i< 0) {
                            timer.cancel();
                            textInput.textProperty().removeListener(textListner);
                            int wpm = correctWordCount * 2;
                            timerText.setText(String.valueOf(wpm) + " WPM");
                        }
                        else if(timerCanceled) {
                            timer.cancel();
                            timerText.setText("30");
                        }
                    }
                }, 0, 1000);
            }

            Text currentText = wordGroup.getTextGroup(getWordCount());
            String currentWord = wordGroup.getWordGroup().get(getWordCount()).getWord();
            int wordLength = currentWord.length();

            if (lastLetter == ' ') {
                incrementWordCount();
                resetCharCount();
                charCount = -1;
                startOfWord = true;
                if (getWordCount() == 10) {
                    resetWordCount();
                    wordGroup = updateWords(wordGroup);
                }

                if(currentText.getFill() == Color.WHITE) {
                    correctWordCount++;
                }

            } else if (!firstRun) {
                incrementCharCount();
            }

            if (charCount < wordLength - 1 && lastLetter != ' ' && lastLetter != currentWord.charAt(getCharCount())) {
                currentText.setFill(Color.RED);
            } else if (lastLetter != ' ') {
                wordGroup.getTextGroup(getWordCount()).setFill(Color.WHITE);
            }
            System.out.println(correctWordCount);
            firstRun = false;
        };

        textInput.textProperty().addListener(textListner);
    }


    public WordGroup displayWords() {
        String family = "Hiragino Sans GB W3";
        double size = 16;
        Color color = Color.GRAY;

        WordGroup words = new WordGroup();

        for (int i = 0; i < 10; i++) {
            Text text1 = new Text(words.getWordGroup().get(i).getWord() + " ");
            text1.setFont(Font.font(family, size));
            text1.setFill(color);
            topText.getChildren().addAll(text1);
            words.setTextGroup(text1);
        }

        for (int i = 10; i < 20; i++) {
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

        for (int i = 0; i < 10; i++) {
            Text text1 = oldWords.getTextGroup(i + 10);
            topText.getChildren().addAll(text1);
            words.setTextGroup(text1);
        }

        for (int i = 10; i < 20; i++) {
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
