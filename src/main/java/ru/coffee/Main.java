package ru.coffee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private ImageTransformer imageTransformer;
    public static void main(String[] args) throws IOException {
        System.out.print("Please input 'help' or 'mem': ");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line = br.readLine();
            if(line.equals("help")) {
                System.out.print("This list of command to input: ");
            }
            else if (line.equals("mem")) {
                System.out.println("We creating a mem!");
            }

        }
    }
}