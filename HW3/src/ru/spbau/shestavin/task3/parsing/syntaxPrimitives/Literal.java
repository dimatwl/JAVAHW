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
package ru.spbau.shestavin.task3.parsing.syntaxPrimitives;

/**
 * Represents symbolic literal.
 *
 * @author Dmitriy shestavin
 * @version 1.0 7 Sep 2012
 */
public class Literal implements AbstractSyntaxPrimitive {
    private Integer value;

    /**
     * Constructs Literal object with specified value.
     *
     * @param value - value of literal.
     */
    public Literal(Integer value) {
        this.value = value;
    }

    /**
     * Getter for value.
     *
     * @return value of literal.
     */
    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Literal)) return false;

        Literal literal = (Literal) o;

        if (value != null ? !value.equals(literal.value) : literal.value != null) return false;

        return true;
    }

}
