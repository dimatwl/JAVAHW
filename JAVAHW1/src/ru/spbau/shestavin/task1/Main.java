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
package ru.spbau.shestavin.task1;

import java.io.FileNotFoundException;
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
        FileMessageReader reader = null;
        MessageWriter writer = null;
        try {
            reader = new FileMessageReader(inputFileName);
            if (args.length >= 2) {
                String outputFileName = args[1];
                writer = new CompressingMessageWriter(new FileMessageWriter(outputFileName));
            } else {
                writer = new CompressingMessageWriter(new ConsoleMessageWriter());
            }
            Message messageFromFile = reader.readMessage();
            while (messageFromFile != null) {
                writer.writeMessage(messageFromFile);
                messageFromFile = reader.readMessage();
            }
            writer.flush();
        } catch (FileNotFoundException e) {
            System.err.println(inputFileName + ": file not found!");
        } catch (IllegalMessageFormatException e) {
            System.err.println("Illegal format in line: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO error has occurred!");
        } finally {
            try {
                if (null != reader){
                    reader.close();
                }
                if (null != writer) {
                    writer.close();
                }
            } catch (IOException e) {
                System.err.println("IO error has occurred on closing!");
            }
        }
    }
}
