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
 * Interface for writing messages to different destinations.
 *
 * @author Dmitriy shestavin
 * @version 1.0 12 Feb 2012
 * @see Message
 */

public interface MessageWriter {

    /**
     * Writes message.
     *
     * @param inpMassage contains message witch will be written.
     * @throws IOException if some IO error has occurred.
     */
    public void writeMessage(Message inpMassage) throws IOException;

    /**
     * Flush the stream. If the stream has saved any characters from the writeMessage() methods in a buffer,
     * write them immediately to their intended destination.
     *
     * @throws IOException if some IO error has occurred.
     */
    public void flush() throws IOException;


    /**
     * Close the stream, flushing it first. Once a stream has been closed, further write() or flush()
     * invocations will cause an IOException to be thrown. Closing a previously-closed stream, however, has no effect.
     *
     * @throws IOException if some IO error has occurred.
     */
    public void close() throws IOException;
}
