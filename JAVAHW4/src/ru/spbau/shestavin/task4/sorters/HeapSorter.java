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

import java.util.Collections;
import java.util.List;

/**
 * Class for sorting using heapsort.
 *
 * @author Dmitriy shestavin
 * @version 1.0 29 Aug 2012
 */
public class HeapSorter extends AbstractSorter {

    @Override
    public <T> void sort(List<T> list, Comparator<? super T> comparator) {
        buildHeap(list,comparator);
        int heapSize = list.size();
        for (int i = list.size() - 1; i > 0; --i){
            Collections.swap(list, 0, i);
            --heapSize;
            heapify(list, 0, heapSize, comparator);
        }
    }

    private <T> void heapify(List<T> A, int i, int heapSize, Comparator<? super T> comparator) {
        int left = 2 * i;
        int right = 2 * i + 1;
        int largest = i;
        if (left < heapSize && comparator.compare(A.get(left),A.get(i)) > 0){
            largest = left;
        }
        if (right < heapSize && comparator.compare(A.get(right),A.get(largest)) > 0){
            largest = right;
        }
        if (largest != i){
            Collections.swap(A, i, largest);
            heapify(A, largest, heapSize, comparator);
        }
    }

    private <T> void buildHeap(List<T> A, Comparator<? super T> comparator){
        for (int i = A.size()/2; i >= 0; --i){
            heapify(A, i, A.size(), comparator);
        }
    }
}
