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
package ru.spbau.shestavin.task6;

import ru.spbau.shestavin.task6.Events.Event;
import ru.spbau.shestavin.task6.Events.RandomEvent;
import ru.spbau.shestavin.task6.Events.TimeEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Main class. Contains only one method witch is entry point.
 *
 * @author Dmitriy shestavin
 * @version 1.0 3 Sep 2012
 */

public class Main {

    /**
     * Entry point of the program.
     * Program makes 5 attempts to fire RandomEvent then
     * 5 attemts to fire TimeEvent.
     *
     * @param args contains arguments from command line.
     */
    public static void main(String[] args) {
        int numberOfListeners = 0;
        RandomEvent randomEvent = new RandomEvent();
        TimeEvent timeEvent = new TimeEvent();
        List<Event> events = new ArrayList<Event>();
        events.add(randomEvent);
        events.add(timeEvent);

        for (int i = 0; i < 5; ++i) {
            final int listenerNumber = numberOfListeners++;
            events.get(i % 2).addListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    StringBuilder outputMessage = new StringBuilder();
                    outputMessage.append(e.getSource().getClass().getSimpleName());
                    outputMessage.append(" - ");
                    outputMessage.append("listener #");
                    outputMessage.append(listenerNumber);
                    System.out.println(outputMessage);
                }
            });
        }

        for (int i = 0; i < 5; ++i) {
            final int listenerNumber = numberOfListeners++;
            events.get(i % 2).addListener(new ActionListener() {
                private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                private Date date = new Date();
                ;

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(dateFormat.format(date));
                }
            });
        }

        System.out.println("5 random attempts:");
        for (int i = 0; i < 5; ++i) {
            if (randomEvent.ready()) {
                randomEvent.fireEvent();
            }
        }
        System.out.println("---------------------------");
        System.out.println("5 time attempts (1 attempt in 3 seconds):");
        for (int i = 0; i < 5; ++i) {
            if (timeEvent.ready()) {
                timeEvent.fireEvent();
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}