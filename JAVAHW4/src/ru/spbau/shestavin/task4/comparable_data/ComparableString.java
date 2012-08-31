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
 * Just dummy wrapper around String with implementation of Comparable<ComparableString>
 *
 * @author Dmitriy shestavin
 * @version 1.0 25 Aug 2012
 */
public class ComparableString implements Comparable<ComparableString> {

    private final String value;

    @Override
    public int compareTo(ComparableString x) {
        return value.compareTo(x.value);
    }

    /**
     * Creates a new ComparableString, with value of input parameter.
     *
     * @param value - value witch will be assigned to new ComparableString object.
     */
    public ComparableString(String value) {
        this.value = value;
    }

    /**
     * Creates a new ComparableString, with value of input parameter.
     *
     * @param value - value witch will be assigned to new ComparableString object.
     */
    public ComparableString(ComparableString value) {
        this.value = value.value;
    }

    /**
     * Getter for the value of ComparableString.
     *
     * @return the value of ComparableString.
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
