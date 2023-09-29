package ru.coffee.impl;

import ru.coffee.ImageTransformer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

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

        System.out.println("Array with args: " + Arrays.toString(args));
//        List<String> parseString = Arrays.asList(args);
        image = ImageIO.read(new File(args[1]));
        Graphics graphics = image.getGraphics();
        String text = args[args.length - 1];

//                String positionInput = parseString.get(0);
//        String colorInput = parseString.get(1);

//        System.out.println(parseString);
        int fontSize = 200;
        Font font = Font.getFont("Arial", new Font("MyFont", Font.BOLD, fontSize));
        graphics.setFont(font);


//        try {
//            fontSize = Integer.parseInt(args[4]);
//        } catch (Exception e) {
//            e.getMessage();
//        }


//        boolean position = positionList.contains(positionInput);
//        System.out.println("Position: " + position);
//        System.out.println("Has a position:" + positionInput);


//        boolean color = colorList.contains(colorInput);
//        System.out.println("Has a color:" + color);

        if (positionList.contains(args[2])) {
            System.out.println("position: " + args[2]);
            int[] coordinates = setPosition(args[2]);
            graphics.drawString(text, coordinates[0], coordinates[1]);
        } else {
            System.out.println("Cin't find position");
            graphics.drawString(text, 400, 400);
        }

        if (colorList.contains(args[3])) {
            System.out.println("Color: " + args[3]);
            Color color = setColor(args[3]);
            graphics.setColor(color);
        } else if (colorList.contains(args[2])) {
            System.out.println("Color: " + args[2]);
            Color color = setColor(args[2]);
            graphics.setColor(color);
        } else {
            System.out.println("Can't find color");
            graphics.setColor(Color.BLACK);
        }
        graphics.dispose();

        if (args[args.length - 2].equals("-s")) {
            ImageIO.write(image, "jpg", new File("mems/picture.jpg"));
        }

    }

    private Color setColor(String color) {
        if (color.equals("red")) return Color.RED;
        else if (color.equals("black")) return Color.BLACK;
        else if (color.equals("blue")) return Color.BLUE;
        return Color.GREEN;
    }

    private int[] setPosition(String position) {
        int[] positionXY = new int[2];
        positionXY[0] = image.getWidth() / 3;
        positionXY[1] = image.getHeight() / 2;
        switch (position) {
            case "center" -> {   // "center", "top", "bottom", "lefttop", "righttop", "leftbottom", "rightbottom"
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

    private List<String> parseArgsStringToList(String[] args) {
        List<String> parseList = Arrays.asList(args);
        for (int i = 0; i < parseList.size(); i++) {
            String colorOrPosition = parseList.get(i);
            if (colorList.contains(colorOrPosition) || positionList.contains(colorOrPosition)) {
                parseList.set(i, colorOrPosition);
            }
        }
        return parseList;
    }
}