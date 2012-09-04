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

import ru.spbau.shestavin.task7.services.DistributedIncrementor;
import ru.spbau.shestavin.task7.users.StupidChild;
import ru.spbau.shestavin.task7.workers.WorkerPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class. Contains only one method witch is entry point.
 *
 * @author Dmitriy shestavin
 * @version 1.0 4 Sep 2012
 */
public class Main {

    /**
     * Entry point of the program.
     * Creates 5 workers and 5 stupudChilds and start them all.
     * Then waits for all stupudChilds.
     *
     * @param args contains arguments from command line.
     */
    public static void main(String[] args) {
        WorkerPool workers = new WorkerPool(5);
        DistributedIncrementor incrementor = new DistributedIncrementor(workers.getTaskQueue());
        List<StupidChild> children = new ArrayList<StupidChild>();
        for (int i = 0; i < 5; ++i) {
            children.add(new StupidChild(i, 10000, 1, 1000, incrementor));
        }
        workers.start();
        for (StupidChild child : children) {
            child.start();
        }
        try {
            for (StupidChild child : children) {
                if (!child.isFinished()) {
                    synchronized (child) {
                        child.wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Got InterruptedException. Exiting...");
            Thread.currentThread().interrupt();
        }
    }
}