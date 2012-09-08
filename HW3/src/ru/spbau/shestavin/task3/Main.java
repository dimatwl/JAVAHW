package ru.spbau.shestavin.task3;

import ru.spbau.shestavin.task3.parsing.Parser;
import ru.spbau.shestavin.task3.parsing.exceptions.LexicalException;
import ru.spbau.shestavin.task3.parsing.exceptions.SyntaxException;

import java.io.IOException;

/**
 * Main class. Contains only one method witch is entry point.
 *
 * @author Dmitriy shestavin
 * @version 1.0 19 Feb 2012
 */

public class Main {

    /**
     * Entry point of the program.
     * First command line argument is source file.
     * Second command line argument is destination file.
     * If second argument not present messages will be written into console.
     *
     * @param args contains arguments from command line.
     */
    public static void main(String[] args) {
        String inputFileName;
        if (args.length == 0) {
            System.err.println("There is no command-line arguments!");
            System.exit(0);
        }
        inputFileName = args[0];
        try {
            Parser parser = new Parser(inputFileName);
            System.out.println("Ans = " + parser.evaluate());
        } catch (IOException e) {
            System.err.println("IOException on reading source file: " + e.getMessage());
        } catch (LexicalException e) {
            System.err.println(e.getMessage());
        } catch (SyntaxException e) {
            System.err.println(e.getMessage());
        } catch (ArithmeticException e) {
            System.err.println(e.getMessage());
        }

    }
}