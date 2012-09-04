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
package ru.spbau.shestavin.task7;

import java.util.Random;

/**
 * TODO write docs
 *
 * @author Dmitriy shestavin
 * @version 1.0 4 Sep 2012
 */
public class StupidChild {
    private boolean finished = false;
    private final int id;
    private final int numberOfIncrements;
    private final int startIntervalNumber;
    private final int intervalWidth;
    private final DistributedIncrementor incrementor;
    private final Random random = new Random();
    private final Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            mainCycle();
        }
    });

    /**
     * TODO write docs
     */
    public StupidChild(int id, int numberOfIncrements, int startIntervalNumber, int intervalWidth, DistributedIncrementor incrementor) {
        this.id = id;
        this.numberOfIncrements = numberOfIncrements;
        this.startIntervalNumber = startIntervalNumber;
        this.intervalWidth = intervalWidth;
        this.incrementor = incrementor;
    }

    /**
     * TODO write docs
     */
    public boolean isFinished() {
        return finished;
    }

    private int nextNumber() {
        return startIntervalNumber + random.nextInt(intervalWidth);
    }

    /**
     * TODO write docs
     */
    public void start() {
        thread.start();
    }

    private void mainCycle() {
        try {
            for (int i = 0; i < numberOfIncrements; ++i) {
                int originalNumber = nextNumber();
                int incrementedNumber = incrementor.increment(originalNumber);
                StringBuilder outputMessage = new StringBuilder("StupidChild#");
                outputMessage.append(id).append(": ");
                outputMessage.append("originalNumber = ").append(originalNumber).append(" ; ");
                outputMessage.append("incrementedNumber = ").append(incrementedNumber).append(" ; ");
                System.out.println(outputMessage);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        finished = true;
        synchronized (this) {
            notify();
        }
    }
}
