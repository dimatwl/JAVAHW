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
package ru.spbau.shestavin.task4.comparators;

import ru.spbau.shestavin.task4.comparable_data.ComparableInteger;

/**
 * Comparator that allow to compare two ComparableInteger by the remainder of the division.
 *
 * @author Dmitriy shestavin
 * @version 1.0 25 Aug 2012
 */
public class IntegerModComparator implements Comparator<ComparableInteger> {

    Integer divisorValue;

    /**
     * Creates a new IntegerModComparatorthat that allow to compare
     * two ComparableInteger by the remainder of the division by divisorValue.
     *
     * @param divisorValue - value of the divisor.
     */
    public IntegerModComparator(Integer divisorValue) {
        this.divisorValue = divisorValue;
    }

    @Override
    public int compare(ComparableInteger x, ComparableInteger y) {
        Integer xRemainder = x.getValue() % divisorValue;
        Integer yRemainder = y.getValue() % divisorValue;
        return xRemainder.compareTo(yRemainder);
    }
}
