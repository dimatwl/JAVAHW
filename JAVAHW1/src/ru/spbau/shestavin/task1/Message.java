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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Contains text information by lines.
 *
 * @author Dmitriy shestavin
 * @version 1.0 12 Feb 2012
 */

public class Message {
    private List<String> myData;


    /**
     * Creates a new <tt>Message</tt>, with no data.
     */
    public Message() {
        myData = new ArrayList<String>();
    }

    /**
     * Adds new line to the end of message.
     *
     * @param inpLine is a string witch will be added to the end of message.
     */
    public void addNewLine(String inpLine) {
        myData.add(inpLine);
    }


    /**
     * Appends inpMessage to current.
     * In general all lines of inpMessage will be added to the end of message.
     *
     * @param inpMessage is a Message witch lines be added to the end of message.
     */
    public void append(Message inpMessage) {
        myData.addAll(inpMessage.getLines());
    }

    /**
     * Returns collection of lines of message represented by List<String>.
     *
     * @return collection of lines of message represented by List<String>.
     */
    public List<String> getLines() {
        return Collections.unmodifiableList(myData);
    }

}
