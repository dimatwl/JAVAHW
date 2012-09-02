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
 * Writes messages to console.
 *
 * @author Dmitriy shestavin
 * @version 1.0 12 Feb 2012
 * @see Message
 * @see MessageWriter
 */

public class ConsoleMessageWriter implements MessageWriter {
    private Integer myMessageNumber = 0;
    private boolean isClosed = false;

    @Override
    public void writeMessage(Message inpMessage) throws IOException {
        if (isClosed) {
            throw new IOException("Can't write to closed stream.");
        } else {
            myMessageNumber++;
            System.out.println("Message " + myMessageNumber.toString());
            Integer lineCounter = 0;
            for (String outputString : inpMessage.getLines()) {
                lineCounter++;
                System.out.println(myMessageNumber.toString() + '.' + lineCounter.toString() + ". " + outputString);
            }
        }
    }

    @Override
    public void flush() throws IOException {
        if (isClosed) {
            throw new IOException("Can't flush closed stream.");
        }
    }

    @Override
    public void close() {
        isClosed = true;
    }
}
