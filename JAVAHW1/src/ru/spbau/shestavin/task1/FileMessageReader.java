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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Reads messages from a file specified in constructor.
 * In general, each readMessage() request creates one Message object,
 * witch contains text from file.
 *
 * @author Dmitriy shestavin
 * @version 1.0 12 Feb 2012
 * @see Message
 */

public class FileMessageReader {
    private BufferedReader myInputReader;

    /**
     * Creates a new <tt>FileMessageReader</tt>, given the name of the
     * file to read from.
     *
     * @param inpFileName the name of the file to read from
     * @throws FileNotFoundException if the named file does not exist,
     *                               is a directory rather than a regular file,
     *                               or for some other reason cannot be opened for
     *                               reading.
     */
    public FileMessageReader(String inpFileName) throws FileNotFoundException {
        myInputReader = new BufferedReader(new FileReader(inpFileName));
    }

    /**
     * Reads message from file specified in constructor.
     *
     * @return Message object witch contains text from file.
     * @throws IOException                   if some IO error has occurred.
     * @throws IllegalMessageFormatException if file specified in constructor
     *                                       contains data witch can't be interpreted as Message.
     */
    public Message readMessage() throws IOException, IllegalMessageFormatException {
        Message messageFromFile = new Message();
        String stringFromFile = myInputReader.readLine();
        if (stringFromFile == null) {
            return null;
        }
        Integer numberOfLines;
        try {
            numberOfLines = Integer.parseInt(stringFromFile);
        } catch (NumberFormatException e) {
            throw new IllegalMessageFormatException(stringFromFile);
        }
        for (int i = 0; i < numberOfLines; i++) {
            stringFromFile = myInputReader.readLine();
            if (stringFromFile != null) {
                messageFromFile.addNewLine(stringFromFile);
            } else {
                throw new IllegalMessageFormatException("End of file!");
            }
        }
        return messageFromFile;
    }

    /**
     * Close the stream. Once a stream has been closed, further readMessage() invocations will throw an IOException.
     * Closing a previously-closed stream, however, has no effect.
     *
     * @throws IOException if some IO error has occurred.
     */
    public void close() throws IOException {
        myInputReader.close();
    }
}
