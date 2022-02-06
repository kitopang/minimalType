package com.app.minimaltype;

import javafx.scene.text.Text;

import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.*;

public class WordGroup {
    private ArrayList<Word> wordGroup;
    private ArrayList<javafx.scene.text.Text> textGroup;

    public WordGroup() {
        wordGroup = new ArrayList<>();
        textGroup = new ArrayList<>();
        String currentLine;
        int randomLine;
        int maxLines = 1000;
        for(int i = 0; i < 20; i++) {
            randomLine = (int)Math.floor(Math.random() * (maxLines + 1));

            try (Stream<String> lines = Files.lines(Paths.get("wordBank.txt"))) {
                currentLine = lines.skip(randomLine).findFirst().get();
                wordGroup.add(new Word(currentLine));
            }
            catch(IOException e){
                System.out.println(e);
            }
        }
    }

    public WordGroup(WordGroup oldWords) {
        wordGroup = new ArrayList<>();
        textGroup = new ArrayList<>();
        String currentLine;
        int randomLine;
        int maxLines = 1000;
        for(int i = 10; i < 20; i ++) {
            wordGroup.add(oldWords.getWordGroup().get(i));
        }

        for(int i = 0; i < 10; i++) {
            randomLine = (int)Math.floor(Math.random() * (maxLines + 1));

            try (Stream<String> lines = Files.lines(Paths.get("wordBank.txt"))) {
                currentLine = lines.skip(randomLine).findFirst().get();
                wordGroup.add(new Word(currentLine));
            }
            catch(IOException e){
                System.out.println(e);
            }
        }
    }

    public ArrayList<Word> getWordGroup() {
        return this.wordGroup;
    }
    public void setTextGroup(Text text) {
        textGroup.add(text);
    }

    public javafx.scene.text.Text getTextGroup(int index) {
        return textGroup.get(index);
    }

    @Override
    public String toString() {
        String returnVal = "";
        for(Word words:wordGroup) {
            returnVal += words.getWord();
            returnVal += " ";
        }

        return returnVal;
    }



}
