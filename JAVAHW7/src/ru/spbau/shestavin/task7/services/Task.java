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

/**
 * TODO write docs
 *
 * @author Dmitriy shestavin
 * @version 1.0 4 Sep 2012
 */
public abstract class Task<T, E> implements Runnable {
    private E result = null;
    private T inputData;

    @Override
    public void run() {
        try {
            result = compute(inputData);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * TODO write docs
     */
    public Task(T inputData) {
        this.inputData = inputData;
    }

    /**
     * TODO write docs
     */
    public E getResult() {
        return result;
    }

    /**
     * TODO write docs
     */
    protected abstract E compute(T inputData) throws InterruptedException;
}


