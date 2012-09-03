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
package ru.spbau.shestavin.task6.Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract event. Describes interface and some behaviour.
 *
 * @author Dmitriy shestavin
 * @version 1.0 3 Sep 2012
 */
public abstract class Event {
    private List<ActionListener> listeners = new ArrayList<ActionListener>();

    /**
     * Describes whether event ready or not.
     *
     * @return status of event.
     */
    public abstract boolean ready();

    /**
     * Calls actionPerformed for all registered ActionListeners.
     * At the end calls reset().
     */
    public void fireEvent() {
        for (ActionListener listener : listeners) {
            listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_FIRST, ""));
        }
        reset();
    }

    /**
     * Registers new ActionListener.
     *
     * @param actionListener - new ActionListener to be registered.
     */
    public void addListener(ActionListener actionListener) {
        listeners.add(actionListener);
    }

    /**
     * Resets state of Event.
     */
    public abstract void reset();
}
