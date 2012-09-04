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

import ru.spbau.shestavin.task7.workers.Worker;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * TODO write docs
 *
 * @author Dmitriy shestavin
 * @version 1.0 4 Sep 2012
 */
public class WorkerPool {
    private final List<Worker> workers;
    private final Queue<Runnable> taskQueue;

    /**
     * TODO write docs
     */
    public WorkerPool(int size) {
        workers = new ArrayList<Worker>(size);
        taskQueue = new LinkedList<Runnable>();
        for (int i = 0; i < size; ++i) {
            workers.add(new Worker(taskQueue));
        }
    }

    /**
     * TODO write docs
     */
    public void start() {
        for (Worker worker : workers) {
            worker.start();
        }
    }

    /**
     * TODO write docs
     */
    public Queue<Runnable> getTaskQueue() {
        return taskQueue;
    }
}
