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
package ru.spbau.shestavin.task4.tools;

import ru.spbau.shestavin.task4.comparable_data.ComparableInteger;
import ru.spbau.shestavin.task4.comparable_data.ComparableString;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class for generating random ComparableIntegers.
 *
 * @author Dmitriy shestavin
 * @version 1.0 31 Aug 2012
 */
public class ComparableStringRandomGenerator implements RandomGenerator<ComparableString> {
    private static final Random random = new Random();
    private static final char[] symbols = new char[36];

    static {
        for (int idx = 0; idx < 10; ++idx)
            symbols[idx] = (char) ('0' + idx);
        for (int idx = 10; idx < 36; ++idx)
            symbols[idx] = (char) ('a' + idx - 10);
    }

    @Override
    public List<ComparableString> generate(int size){
        List<ComparableString> result = new ArrayList<ComparableString>(size);
        for (int i = 0; i < size; ++i) {
            char[] buf = new char[random.nextInt(size) + 1];
            result.add(new ComparableString(nextString(buf)));
        }
        return result;
    }

    private String nextString(char[] buf)
    {
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }
}
