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
 * Class for sorting using shakersort.
 *
 * @author Dmitriy shestavin
 * @version 1.0 29 Aug 2012
 */
public class ShakerSorter extends AbstractSorter {

    @Override
    public <T> void sort(List<T> list, Comparator<? super T> comparator) {
        if (list.size() > 1) {
            int Left = 0;
            int Right = list.size() - 1; //границы сортировки
            int Last = list.size() - 1;       //место последней перестановки
            do {
                //Сдвигаем к концу массива "легкие элементы"
                for (int i = Right; i > Left; i--) {
                    if (comparator.compare(list.get(i - 1), list.get(i)) > 0) {
                        Collections.swap(list, i - 1, i);
                        Last = i; //Запомнить место пследней перестановки
                    }
                }

                Left = Last;

                //Сдвигаем к началу массива "тяжелые элементы"
                for (int i = Left; i <= Right; i++) {
                    if (comparator.compare(list.get(i - 1), list.get(i)) > 0) {
                        Collections.swap(list, i - 1, i);
                        Last = i; //Запомнить место пследней перестановки
                    }
                }

                Right = Last - 1;
            }
            while (Left <= Right);
        }
    }
}
