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
package ru.spbau.shestavin.task4;

import ru.spbau.shestavin.task4.comparable_data.ComparableInteger;
import ru.spbau.shestavin.task4.comparable_data.ComparableString;
import ru.spbau.shestavin.task4.comparators.IntegerModComparator;
import ru.spbau.shestavin.task4.comparators.StringLengthComparator;
import ru.spbau.shestavin.task4.sorters.HeapSorter;
import ru.spbau.shestavin.task4.sorters.ShakerSorter;
import ru.spbau.shestavin.task4.tools.ComparableIntegerRandomGenerator;
import ru.spbau.shestavin.task4.tools.ComparableStringRandomGenerator;
import ru.spbau.shestavin.task4.tools.Timer;

import java.util.List;


/**
 * Main class. Contains only one method witch is entry point.
 *
 * @author Dmitriy shestavin
 * @version 1.0 30 Aug 2012
 */
public class Main {
    private static final Timer timer = new Timer();

    /**
     * Entry point of the program.
     *
     * @param args contains arguments from command line.
     */
    public static void main(String[] args) {

        for (int i = 10; i <= 10000; i *= 10) {
            compareTime(i);
        }

        demonstration(3);

    }

    private static void compareTime(int size) {
        System.out.println("Array size = " + size);
        System.out.println("ComparableIntegers:");
        System.out.println("ShakerSort time = " + timer.getTime(size, new ShakerSorter(), new ComparableIntegerRandomGenerator()) + " ms");
        System.out.println("HeapSort time = " + timer.getTime(size, new HeapSorter(), new ComparableIntegerRandomGenerator()) + " ms");
        System.out.println("ComparableStrings:");
        System.out.println("ShakerSort time = " + timer.getTime(size, new ShakerSorter(), new ComparableStringRandomGenerator()) + " ms");
        System.out.println("HeapSort time = " + timer.getTime(size, new HeapSorter(), new ComparableStringRandomGenerator()) + " ms");
        System.out.println("------------------------------------------------------------------");
    }

    private static void demonstration(int divisor) {
        System.out.println("ComparableIntegers without comparator:");
        List<ComparableInteger> list0 = (new ComparableIntegerRandomGenerator()).generate(20);
        (new HeapSorter()).sort(list0);
        System.out.println(list0);

        System.out.println("ComparableIntegers with IntegerModComparator with divisor = " + divisor + ":");
        (new HeapSorter()).sort(list0, new IntegerModComparator(divisor));
        System.out.println(list0);

        System.out.println("ComparableStrings without comparator:");
        List<ComparableString> list1 = (new ComparableStringRandomGenerator()).generate(10);
        (new HeapSorter()).sort(list1);
        System.out.println(list1);

        System.out.println("ComparableStrings with StringLengthComparator:");
        (new HeapSorter()).sort(list1, new StringLengthComparator());
        System.out.println(list1);
    }
}
