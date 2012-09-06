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
package ru.spbau.shestavin.task7.workers;

import java.util.Queue;

/**
 * Working daemon thread. Takes tasks from taskQueue and runs them.
 * Works until thread not interrupted.
 *
 * @author Dmitriy shestavin
 * @version 1.0 4 Sep 2012
 */
public class Worker implements Runnable {

    private final Queue<Runnable> taskQueue;

    /**
     * Creates new Worker.
     *
     * @param taskQueue - queue with tasks to run.
     */
    public Worker(Queue<Runnable> taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        mainCycle();
    }

    private void mainCycle() {
        try {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
                Runnable task;
                synchronized (taskQueue) {
                    task = taskQueue.poll();
                }
                if (null != task) {
                    synchronized (task) {
                        task.run();
                        task.notify();
                    }
                } else {
                    synchronized (taskQueue) {
                        taskQueue.wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
