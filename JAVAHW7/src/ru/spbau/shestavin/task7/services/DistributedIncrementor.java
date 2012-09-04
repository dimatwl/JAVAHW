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
package ru.spbau.shestavin.task7.services;

import java.util.Queue;

/**
 * TODO write docs
 *
 * @author Dmitriy shestavin
 * @version 1.0 4 Sep 2012
 */
public class DistributedIncrementor {
    private final Queue<Runnable> taskQueue;

    /**
     * TODO write docs
     */
    public DistributedIncrementor(Queue<Runnable> taskQueue) {
        this.taskQueue = taskQueue;
    }

    /**
     * TODO write docs
     */
    public int increment(int i) throws InterruptedException {
        Task<Integer, Integer> task = new Task<Integer, Integer>(i) {
            @Override
            protected Integer compute(Integer inputData) throws InterruptedException {
                return inputData + 1;
            }
        };
        synchronized (task) {
            synchronized (taskQueue) {
                taskQueue.offer(task);
                taskQueue.notify();
            }
            task.wait();
        }
        return task.getResult();
    }
}
