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
package ru.spbau.shestavin.task4.comparable_data;

/**
 * Just dummy wrapper around Integer with implementation of Comparable<ComparableInteger>
 *
 * @author Dmitriy shestavin
 * @version 1.0 25 Aug 2012
 */
public class ComparableInteger implements Comparable<ComparableInteger> {

    private Integer value;

    public int compareTo(ComparableInteger x) {
        return value.compareTo(x.value);
    }

    /**
     * Creates a new ComparableInteger, with value of input parameter.
     * @param value - value witch will be assigned to new ComparableInteger object.
     */
    public ComparableInteger(Integer value) {
        this.value = value;
    }

    /**
     * Creates a new ComparableInteger, with value of input parameter.
     * @param value - value witch will be assigned to new ComparableInteger object.
     */
    public ComparableInteger(ComparableInteger value) {
        this.value = value.value;
    }
}
