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

import ru.spbau.shestavin.task3.parsing.syntaxConstructions.Expression;

import java.util.List;

/**
 * Represents function call.
 *
 * @author Dmitriy shestavin
 * @version 1.0 7 Sep 2012
 */
public class FunctionCall implements AbstractSyntaxPrimitive {
    private String name;
    List<Expression> params;

    /**
     * Constructs FunctionCall object with specified name and parameters.
     *
     * @param name - name of function.
     * @param params - parameters of function.
     */
    public FunctionCall(String name, List<Expression> params) {
        this.name = name;
        this.params = params;
    }

    /**
     * Getter for name.
     *
     * @return name of function.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for parameters of function.
     *
     * @return parameters of function.
     */
    public List<Expression> getParams() {
        return params;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FunctionCall)) return false;

        FunctionCall that = (FunctionCall) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (params != null ? !params.equals(that.params) : that.params != null) return false;

        return true;
    }

}
