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
package ru.spbau.shestavin.task4.tools;

import ru.spbau.shestavin.task4.comparable_data.Comparable;
import ru.spbau.shestavin.task4.comparators.Comparator;
import ru.spbau.shestavin.task4.sorters.Sorter;

import java.util.List;

/**
 * Class for measuring time of sorting.
 *
 * @author Dmitriy shestavin
 * @version 1.0 29 Aug 2012
 */
public class Timer {

    public <T extends Comparable<T>> long getTime(List<T> list, Sorter sorter) {
        long startTime = System.currentTimeMillis();
        sorter.sort(list);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public <T> long getTime(List<T> list, Sorter sorter, Comparator<? super T> comparator) {
        long startTime = System.currentTimeMillis();
        sorter.sort(list, comparator);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
