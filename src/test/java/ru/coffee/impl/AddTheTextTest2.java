package ru.coffee.impl;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


class AddTheTextTest2 {

    @Test
    void mainMethod_correctArguments_wellDone() {
        AddTheText addTheText = new AddTheText();
        String[] args = {"mem", "./horse.jpg", "50", "top", "black", "-s", "Hi-Hi-Hi"};
        try {
            addTheText.transformImage(args);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void mainMethod_withIncorrectPathArguments_fail() {
        AddTheText addTheText = new AddTheText();
        String[] args = {"mem", "./horse.incorrectpath", "50", "", "black", "-s", "Hi-Hi-Hi"};
        try {
            addTheText.transformImage(args);
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
