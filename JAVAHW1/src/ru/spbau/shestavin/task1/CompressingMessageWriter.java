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

import java.io.IOException;

/**
 * Just wrapper for another MessageWriter.
 * Don't writes messages for itself.
 * Main function - compress two consequent messages to one.
 *
 * @author Dmitriy shestavin
 * @version 1.0 19 Feb 2012
 * @see Message
 * @see MessageWriter
 */

public class CompressingMessageWriter implements MessageWriter {
    private MessageWriter myMessageWriter;
    private Message myStoredMessage = null;
    private boolean isClosed = false;


    /**
     * Creates a new <tt>CompressingMessageWriter</tt>, given the name of the
     * file to write.
     *
     * @param inpMessageWriter MessageFriter that will be wrapped.
     */
    public CompressingMessageWriter(MessageWriter inpMessageWriter) {
        myMessageWriter = inpMessageWriter;
    }

    @Override
    public void writeMessage(Message inpMessage) throws IOException {
        if (myStoredMessage == null) {
            myStoredMessage = inpMessage;
        } else {
            myStoredMessage.append(inpMessage);
            myMessageWriter.writeMessage(myStoredMessage);
            myStoredMessage = null;
        }
    }

    @Override
    public void flush() throws IOException {
        if (isClosed) {
            throw new IOException("Can't flush closed stream.");
        } else if (myStoredMessage != null) {
            myMessageWriter.writeMessage(myStoredMessage);
            myStoredMessage = null;
        }
    }

    @Override
    public void close() throws IOException {
        if (!isClosed) {
            flush();
        }
        isClosed = true;
        myMessageWriter.close();
    }
}
