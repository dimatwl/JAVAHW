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
package ru.spbau.shestavin.task4.sorters;

import ru.spbau.shestavin.task4.comparators.Comparator;
import ru.spbau.shestavin.task4.comparable_data.Comparable;

import java.util.List;

/**
 * Abstract class for sorting. Defines some general behaviour.
 *
 * @author Dmitriy shestavin
 * @version 1.0 30 Aug 2012
 */
public abstract class AbstractSorter implements Sorter {

    @Override
    public <T extends Comparable<? super T>> void sort(List<T> list) {
        sort(list, new Comparator<T>() {
            @Override
            public int compare(T x, T y) {
                return x.compareTo(y);
            }
        });
    }
}
