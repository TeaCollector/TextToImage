package ru.coffee.impl;

import ru.coffee.ImageTransformer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

        List<String> parsedList = parsedList(args);
        System.out.println("Parsed List: " + parsedList);
        image = ImageIO.read(new File(args[1]));
        Graphics graphics = image.getGraphics();
        String text = args[args.length - 1];

        try {
            Files.createDirectory(Path.of("./mems"));
        } catch (IOException e) {
            e.getCause();

        }

        setFont(parsedList, graphics);

        defineColor(parsedList, graphics);
        definePositionAndDrawText(parsedList, graphics, text);

        graphics.dispose();

        if (args[args.length - 2].equals("-s")) {
            ImageIO.write(image, "jpg", new File("mems" + args[1].substring(1)));
        }

    }

    private void setFont(List<String> args, Graphics graphics) {
        int fontSize = 100;
        try {
            fontSize = Integer.parseInt(args.get(args.size() - 1));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            e.getCause();
        }
        System.out.println("Font size: " + fontSize);
        Font font = Font.getFont("Arial", new Font("MyFont", Font.BOLD, fontSize));
        graphics.setFont(font);
    }

    private void definePositionAndDrawText(List<String> args, Graphics graphics, String text) {
        if (!positionList.contains(args.get(0))) {
            System.out.println("Can't find position use default.");
            graphics.drawString(text, 200, 200);

        } else {
            System.out.println("Position: " + args.get(0));
            int[] coordinates = setPosition(args.get(0));
            graphics.drawString(text, coordinates[0], coordinates[1]);
        }
    }

    private void defineColor(List<String> args, Graphics graphics) {
        if (!colorList.contains(args.get(1))) {
            if (!colorList.contains(args.get(0))) {
                System.out.println("Can't find color use default.");
                graphics.setColor(BLACK);

            } else {
                System.out.println("Color: " + args.get(0));
                Color color = setColor(args.get(0));
                graphics.setColor(color);

            }
        } else {
            System.out.println("Color: " + args.get(1));
            Color color = setColor(args.get(1));
            graphics.setColor(color);
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

    private List<String> parsedList(String[] args) {
        List<String> parsedList = new ArrayList<>();
        for (String position : args) {
            if (positionList.contains(position)) {
                parsedList.add(position);
                break;
            }
        }
        for (String color : args) {
            if (colorList.contains(color)) {
                parsedList.add(color);
            }
        }
        for (String arg : args) {
            char digit = arg.charAt(0);
            if (Character.isDigit(digit)) {
                parsedList.add(arg);
                break;
            }
        }

        return parsedList;
    }
}