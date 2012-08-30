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
import ru.spbau.shestavin.task4.sorters.HeapSorter;
import ru.spbau.shestavin.task4.sorters.ShakerSorter;
import ru.spbau.shestavin.task4.sorters.Sorter;
import ru.spbau.shestavin.task4.tools.ComparableIntegerRandomGenerator;
import ru.spbau.shestavin.task4.tools.ComparableStringRandomGenerator;
import ru.spbau.shestavin.task4.tools.Timer;

import java.util.ArrayList;
import java.util.List;


/**
 * Main class. Contains only one method witch is entry point.
 *
 * @author Dmitriy shestavin
 * @version 1.0 30 Aug 2012
 */
public class Main {

    /**
     * Entry point of the program.
     *
     * @param args contains arguments from command line.
     */
    public static void main(String[] args) {
        Timer timer = new Timer();
        List<ComparableString> list = (new ComparableStringRandomGenerator()).generate(10);
        (new ShakerSorter()).sort(list);
        System.out.println(list);
        /*System.out.println(timer.getTime(10000, new HeapSorter(), new ComparableIntegerRandomGenerator()));
        System.out.println(timer.getTime(10000, new ShakerSorter(), new ComparableIntegerRandomGenerator()));
        System.out.println("------");

        System.out.println(timer.getTime(20000, new HeapSorter(), new ComparableIntegerRandomGenerator()));
        System.out.println(timer.getTime(20000, new ShakerSorter(), new ComparableIntegerRandomGenerator()));
        System.out.println("------");

        System.out.println(timer.getTime(40000, new HeapSorter(), new ComparableIntegerRandomGenerator()));
        System.out.println(timer.getTime(40000, new ShakerSorter(), new ComparableIntegerRandomGenerator()));
        System.out.println("------");

        System.out.println(timer.getTime(80000, new HeapSorter(), new ComparableIntegerRandomGenerator()));
        System.out.println(timer.getTime(80000, new ShakerSorter(), new ComparableIntegerRandomGenerator()));
        System.out.println("------");

        System.out.println(timer.getTime(160000, new HeapSorter(), new ComparableIntegerRandomGenerator()));
        System.out.println(timer.getTime(160000, new ShakerSorter(), new ComparableIntegerRandomGenerator()));
        */
    }
}
