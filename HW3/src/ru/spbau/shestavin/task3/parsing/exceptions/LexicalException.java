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
package ru.spbau.shestavin.task3.parsing.exceptions;

import ru.spbau.shestavin.task3.parsing.lexic_tools.Token;

/**
 * Class used to represent all problems with lexical structures.
 *
 * @author Dmitriy shestavin
 * @version 1.0 7 Sep 2012
 */
public class LexicalException extends Exception {
    private Token token;

    /**
     * Constructs LexicalException with specified Token.
     *
     * @param token - token witch will be returned on getMessage() call.
     */
    public LexicalException(Token token) {
        this.token = token;
    }

    @Override
    public String getMessage() {
        return "Lexical error on: " + token.getValue();
    }
}
