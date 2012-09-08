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

import ru.spbau.shestavin.task3.parsing.syntaxPrimitives.Variable;

import java.util.List;

/**
 * Represents assignment of function. Here getVariable() returns name of function.
 *
 * @author Dmitriy shestavin
 * @version 1.0 7 Sep 2012
 */
public class FunctionAssignment implements Statement {
    private Variable name;
    private List<Variable> params;
    private Expression expression;

    /**
     * Constructs Expression with specified syntax tree.
     *
     * @param name       - name of function.
     * @param params     - parameters of function.
     * @param expression - body of function.
     */
    public FunctionAssignment(Variable name, List<Variable> params, Expression expression) {
        this.name = name;
        this.params = params;
        this.expression = expression;
    }


    @Override
    public Variable getVariable() {
        return name;
    }

    /**
     * Getter for function parameters.
     *
     * @return function parameters.
     */
    public List<Variable> getParams() {
        return params;
    }

    @Override
    public Expression getExpression() {
        return expression;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FunctionAssignment)) return false;

        FunctionAssignment that = (FunctionAssignment) o;

        if (expression != null ? !expression.equals(that.expression) : that.expression != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (params != null ? !params.equals(that.params) : that.params != null) return false;

        return true;
    }

}


