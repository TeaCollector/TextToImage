package ru.coffee;

import ru.coffee.impl.AddTheText;

import java.io.IOException;

public class Main {

    private ImageTransformer imageTransformer;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.run(args);
    }


    private void run(String[] args) throws IOException {
        String command = args[0];
        if (command.equals("help")) {
            System.out.println("This list of command to input: \n1) 'mem' - Adding the text to image. This command has " +
                    "arguments: \n" +
                    "a) 'center', 'top', 'bottom', 'lefttop', 'righttop', 'leftbottom' or 'rightbottom' - Adding text to specified position.\n" +
                    "b) 'red', 'green', 'black', 'blue'  - Change the text's color.\n" +
                    "c) '50' - set font size.\n" +
                    "d) '-s' - save the image in folder named 'mems'.\n\n" +
                    "For example if you input: java -jar build/libs/TextToImage-1.0-SNAPSHOT.jar mem ./horse.jpg righttop red 27 -s 'Nice kitty!' \n\n" +
                    "You add red text: 'Nice kitty!' with font size 27 to the right top of the ./picture.jpg and save it at the /mems/.\n" +
                    "You can switch the order of position and color, but another arguments NOT!\n" +
                    "By default settings: color = black, position = left top, not save.\n" +
                    "You can input only position or color, it will work with default parameters.\n\n" +
                    "*Select the appropriate font size depending on the image resolution.");

        } else if (command.equals("mem")) {
            imageTransformer = new AddTheText();
            imageTransformer.transformImage(args);
            System.out.println("We creating a mem! Visit folder: /mems/");
        }
    }
}