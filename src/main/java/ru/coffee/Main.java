package ru.coffee;

import ru.coffee.impl.AddTheText;

import java.io.File;
import java.io.IOException;

public class Main {

    private ImageTransformer imageTransformer;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.run(args);
    }


    private void run(String[] args) throws IOException {
        String firstLine = args[0];
            if(firstLine.equals("help")) {
                System.out.println("This list of command to input: \n1) 'mem' - Adding the text to image. This command has " +
                        "argument: \n" +
                        "a) 'center', 'top', 'bottom', 'lefttop', 'righttop', 'leftbottom' or 'rightbottom' - Adding text to specified position.\n" +
                        "b) 'red', 'green', 'black', 'blue'  - Change the text's color.\n" +
                        "c) '150' - Set the size of font." +
                        "d) '-s' - save the image in folder named 'mems'.\n" +
                        "For example if you input: java -jar *.jar mem ./picture.jpg righttop red 200 -s 'WHAT A WONDERFUL DOGGY!!!' \n" +
                        "You add red text: 'WHAT A WONDERFUL DOGGY!!!' to the right top of the ./picture.jpg with size 200 and save it at the /mems/");

            }
            else if (firstLine.equals("mem")) {
                imageTransformer = new AddTheText();
                imageTransformer.transformImage(args);
                System.out.println("We creating a mem!");
            }
    }
}