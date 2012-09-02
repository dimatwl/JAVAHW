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

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Writes messages to file specified in constructor.
 *
 * @author Dmitriy shestavin
 * @version 1.0 19 Feb 2012
 * @see Message
 * @see MessageWriter
 */

public class FileMessageWriter implements MessageWriter {
    private PrintWriter myOutputWriter;
    private boolean isClosed = false;

    /**
     * Creates a new <tt>FileMessageWriter</tt>, given the name of the
     * file to write.
     *
     * @param inpFileName the name of the file to write.
     * @throws IOException if some IO error has occurred.
     */
    public FileMessageWriter(String inpFileName) throws IOException {
        myOutputWriter = new PrintWriter(new FileWriter(inpFileName));
    }

    @Override
    public void writeMessage(Message inpMessage) throws IOException {
        if (isClosed) {
            throw new IOException("Can't write to closed stream.");
        } else {
            List<String> messageLines = inpMessage.getLines();
            myOutputWriter.println(messageLines.size());
            for (String outputString : messageLines) {
                myOutputWriter.println(outputString);
            }
        }
    }

    @Override
    public void flush() throws IOException {
        if (isClosed) {
            throw new IOException("Can't flush closed stream.");
        } else {
            myOutputWriter.flush();
        }
    }

    @Override
    public void close() {
        if (!isClosed) {
            myOutputWriter.flush();
        }
        isClosed = true;
    }
}
