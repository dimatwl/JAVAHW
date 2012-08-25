package ru.spbau.shestavin.task4.interfaces;
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

/**
 * A comparison function, which imposes a total ordering on some collection of objects.
 * Comparators can be passed to a sort method to allow precise control over the sort order.
 * Comparators can also be used to control the order of certain data structures.
 *
 * @author Dmitriy shestavin
 * @version 1.0 24 Aug 2012
 */
public interface Comparator<T> {

    /**
     * Compares its two arguments for order.
     * Returns a negative integer, zero, or a positive integer as the first argument is less than,
     * equal to, or greater than the second.
     *
     * @param x - the first object to be compared.
     * @param y - the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the first argument is less than,
     *         equal to, or greater than the second.
     */
    int compare(T x, T y);
}
