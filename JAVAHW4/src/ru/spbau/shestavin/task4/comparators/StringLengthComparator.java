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

import ru.spbau.shestavin.task4.comparable_data.ComparableString;

/**
 * Comparator that allow to compare two ComparableInteger by the remainder of the division.
 *
 * @author Dmitriy shestavin
 * @version 1.0 25 Aug 2012
 */
public class StringLengthComparator implements Comparator<ComparableString> {

    @Override
    public int compare(ComparableString x, ComparableString y) {
        Integer xLength = x.getValue().length();
        Integer yLength = y.getValue().length();
        return xLength.compareTo(yLength);
    }
}
