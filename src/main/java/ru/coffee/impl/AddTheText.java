package ru.coffee.impl;

import ru.coffee.ImageTransformer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static java.awt.Color.*;

public class AddTheText implements ImageTransformer {


    private final List<String> colorList;
    private final List<String> positionList;
    private BufferedImage image;

    public AddTheText() {
        colorList = new ArrayList<>(Arrays.asList("red", "green", "blue", "black"));
        positionList = new ArrayList<>(Arrays.asList("center", "top", "bottom", "lefttop", "righttop", "leftbottom", "rightbottom"));
    }

    @Override
    public void transformImage(String[] args) throws IOException {
        image = ImageIO.read(new File(args[1]));
        Graphics graphics = image.getGraphics();
        String text = args[args.length - 1];

        Font font = Font.getFont("Arial", new Font("MyFont", Font.BOLD, 100));
        graphics.setFont(font);
        defineColor(args, graphics);
        definePositionAndDrawText(args, graphics, text);
        graphics.dispose();

        if (args[args.length - 2].equals("-s")) {
            ImageIO.write(image, "jpg", new File("mems" + args[1].substring(1)));
        }

    }

    private void definePositionAndDrawText(String[] args, Graphics graphics, String text) {
        if (positionList.contains(args[2])) {
            System.out.println("Position: " + args[2]);
            int[] coordinates = setPosition(args[2]);
            graphics.drawString(text, coordinates[0], coordinates[1]);
        } else if (positionList.contains(args[3])) {
            System.out.println("Position: " + args[3]);
            int[] coordinates = setPosition(args[3]);
            graphics.drawString(text, coordinates[0], coordinates[1]);
        } else {
            System.out.println("Can't find position use default.");
            graphics.drawString(text, 200, 200);
        }
    }

    private void defineColor(String[] args, Graphics graphics) {
        if (colorList.contains(args[3])) {
            System.out.println("Color: " + args[3]);
            Color color = setColor(args[3]);
            graphics.setColor(color);
        } else if (colorList.contains(args[2])) {
            System.out.println("Color: " + args[2]);
            Color color = setColor(args[2]);
            graphics.setColor(color);
        } else {
            System.out.println("Can't find color use default.");
            graphics.setColor(BLACK);
        }
    }

    private Color setColor(String color) {
        switch (color) {
            case "red" -> {
                return RED;
            }
            case "black" -> {
                return BLACK;
            }
            case "blue" -> {
                return BLUE;
            }
            default -> {
                return GREEN;
            }
        }
    }

    private int[] setPosition(String position) {
        int[] positionXY = new int[2];
        positionXY[0] = image.getWidth() / 3;
        positionXY[1] = image.getHeight() / 2;
        switch (position) {
            case "center" -> {
                positionXY[0] = image.getWidth() / 3;
                positionXY[1] = image.getHeight() / 2;
            }
            case "top" -> {
                positionXY[0] = image.getWidth() / 3;
                positionXY[1] = image.getHeight() / 7;
            }
            case "bottom" -> {
                positionXY[0] = image.getWidth() / 3;
                positionXY[1] = image.getHeight() * 7 / 8;
            }
            case "lefttop" -> {
                positionXY[0] = image.getWidth() / 9;
                positionXY[1] = image.getHeight() / 7;
            }
            case "righttop" -> {
                positionXY[0] = image.getWidth() * 5 / 9;
                positionXY[1] = image.getHeight() / 7;
            }
            case "leftbottom" -> {
                positionXY[0] = image.getWidth() / 9;
                positionXY[1] = image.getHeight() * 7 / 8;
                return positionXY;
            }
            case "rightbottom" -> {
                positionXY[0] = image.getWidth() * 5 / 9;
                positionXY[1] = image.getHeight() * 7 / 8;
            }
        }
        return positionXY;
    }
}