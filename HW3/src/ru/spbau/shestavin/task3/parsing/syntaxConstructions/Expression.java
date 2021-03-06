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

/**
 * Represents expression witch can be evaluated.
 *
 * @author Dmitriy shestavin
 * @version 1.0 7 Sep 2012
 */
public class Expression {
    private SyntaxTree tree;

    /**
     * Constructs Expression with specified syntax tree.
     *
     * @param tree - syntax tree for expression.
     */
    public Expression(SyntaxTree tree) {
        this.tree = tree;
    }

    /**
     * Getter for tree.
     *
     * @return value of syntax tree.
     */
    public SyntaxTree getTree() {
        return tree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Expression)) return false;

        Expression that = (Expression) o;

        if (tree != null ? !tree.equals(that.tree) : that.tree != null) return false;

        return true;
    }
}
