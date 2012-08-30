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
        buildHeap(list, comparator);
        shrinkHeap(list, comparator);
    }

    private <T> void buildHeap(List<T> list, Comparator<? super T> comparator) {
        for (int child = 1; child < list.size(); child++) {
            int parent = (child - 1) / 2;
            while (parent >= 0 && comparator.compare(list.get(parent), list.get(child)) < 0) {
                Collections.swap(list, parent, child);
                child = parent;
                parent = (child - 1) / 2;
            }
        }
    }

    private <T> void shrinkHeap(List<T> list, Comparator<? super T> comparator) {
        for (int n = list.size() - 1; n >= 0; n--) {
            Collections.swap(list, 0, n);
            int parent = 0;
            while (true) {
                int leftChild = 2 * parent + 1;
                if (leftChild >= n) {
                    break; // no more children
                }
                int rightChild = leftChild + 1;
                int maxChild = leftChild;
                if (rightChild < n && comparator.compare(list.get(leftChild), list.get(rightChild)) < 0) {
                    maxChild = rightChild;
                }
                if (comparator.compare(list.get(parent), list.get(maxChild)) < 0) {
                    Collections.swap(list, parent, maxChild);
                    parent = maxChild;
                } else {
                    break; // exit loop
                }
            }
        }
    }
}
