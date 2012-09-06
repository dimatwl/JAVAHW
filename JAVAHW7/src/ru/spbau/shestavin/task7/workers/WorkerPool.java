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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Pool of workers witch works on the same queue.
 *
 * @author Dmitriy shestavin
 * @version 1.0 4 Sep 2012
 */
public class WorkerPool {
    private final List<Worker> workers;
    private final Queue<Runnable> taskQueue = new LinkedList<Runnable>();;
    private final List<Thread> threads = new ArrayList<Thread>();

    /**
     * Creates new WorkerPool.
     *
     * @param size - number of workers to create.
     */
    public WorkerPool(int size) {
        workers = new ArrayList<Worker>(size);
        for (int i = 0; i < size; ++i) {
            workers.add(new Worker(taskQueue));
        }
    }

    /**
     * Starts all workers in separate threads.
     */
    public void start() {
        for (Worker worker : workers) {
            Thread thread = new Thread(worker);
            thread.setDaemon(true);
            thread.start();
            threads.add(thread);
        }
    }

    /**
     * Stops all workers.
     */
    public void stop() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    /**
     * Getter for taskQueue. This queue should be used by DistributedIncrementors.
     *
     * @return taskQueue.
     */
    public Queue<Runnable> getTaskQueue() {
        return taskQueue;
    }
}
