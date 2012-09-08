/*
* Dmitriy Shestavin
*
* Copyright (c) Dmitriy Shestavin & co, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of
* Dmitriy Shestavin & co, Inc. ("Confidential Information").  You shall not
* disclose such Confidential Information and shall use it only in
* accordance with the terms of the license agreement you entered into
* with Dmitriy Shestavin & co, Inc.
*
* DMITRIY SHESTAVIN & CO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
* THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
* TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
* PARTICULAR PURPOSE, OR NON-INFRINGEMENT. DMITRIY SHESTAVIN & CO SHALL NOT BE LIABLE FOR
* ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
* DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
*/
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
     * Prints result of evaluation to command line.
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