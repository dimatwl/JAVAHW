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
package ru.spbau.shestavin.task3.parsing.syntaxConstructions;

import ru.spbau.shestavin.task3.parsing.syntaxPrimitives.AbstractSyntaxPrimitive;

/**
 * Represents syntax tree consisting of AbstractSyntaxPrimitives.
 *
 * @author Dmitriy shestavin
 * @version 1.0 7 Sep 2012
 */
public class SyntaxTree {

    private AbstractSyntaxPrimitive abstractSyntaxPrimitive;

    private SyntaxTree leftChild = null;
    private SyntaxTree rightChild = null;

    /**
     * Getter for AbstractSyntaxPrimitive.
     *
     * @return value of AbstractSyntaxPrimitive.
     */
    public AbstractSyntaxPrimitive getAbstractSyntaxPrimitive() {
        return abstractSyntaxPrimitive;
    }

    /**
     * Constructs SyntaxTree object with specified AbstractSyntaxPrimitive.
     *
     * @param abstractSyntaxPrimitive - syntax primitive.
     */
    public SyntaxTree(AbstractSyntaxPrimitive abstractSyntaxPrimitive) {
        this.abstractSyntaxPrimitive = abstractSyntaxPrimitive;
    }

    /**
     * Getter for rightChild.
     *
     * @return value of rightChild.
     */
    public SyntaxTree getRightChild() {
        return rightChild;
    }

    /**
     * Setter for rightChild.
     *
     * @param rightChild - value of rightChild.
     */
    public void setRightChild(SyntaxTree rightChild) {
        this.rightChild = rightChild;
    }

    /**
     * Getter for leftChild.
     *
     * @return value of leftChild.
     */
    public SyntaxTree getLeftChild() {
        return leftChild;
    }

    /**
     * Setter for leftChild.
     *
     * @param leftChild - value of leftChild.
     */
    public void setLeftChild(SyntaxTree leftChild) {
        this.leftChild = leftChild;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SyntaxTree)) return false;

        SyntaxTree that = (SyntaxTree) o;

        if (abstractSyntaxPrimitive != null ? !abstractSyntaxPrimitive.equals(that.abstractSyntaxPrimitive) : that.abstractSyntaxPrimitive != null)
            return false;
        if (leftChild != null ? !leftChild.equals(that.leftChild) : that.leftChild != null) return false;
        if (rightChild != null ? !rightChild.equals(that.rightChild) : that.rightChild != null) return false;

        return true;
    }
}
