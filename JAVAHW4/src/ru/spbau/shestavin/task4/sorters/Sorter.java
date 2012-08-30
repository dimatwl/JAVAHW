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

import java.util.List;

/**
 * Interface for sorting.
 *
 * @author Dmitriy shestavin
 * @version 1.0 25 Aug 2012
 */
public interface Sorter {

    /**
     * Method for sorting object using default ordering. int compareTo(T x) used for comparation.
     */
    public <T extends ru.spbau.shestavin.task4.comparable_data.Comparable<? super T>> void sort(List<T> list);

    /**
     * Method for sorting object using ordering imposed by comparator. int compare(T x, T y) used for comparation.
     */
    public <T> void sort(List<T> list, Comparator<? super T> comparator);
}


