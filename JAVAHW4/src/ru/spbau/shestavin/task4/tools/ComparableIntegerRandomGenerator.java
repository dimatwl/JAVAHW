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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class for generating random ComparableIntegers.
 *
 * @author Dmitriy shestavin
 * @version 1.0 30 Aug 2012
 */
public class ComparableIntegerRandomGenerator implements RandomGenerator<ComparableInteger>{
    private static Random random = new Random();

    @Override
    public List<ComparableInteger> generate(int size){
        List<ComparableInteger> result = new ArrayList<ComparableInteger>(size);
        for (int i = 0; i < size; ++i) {
            result.add(new ComparableInteger(random.nextInt(size)));
        }
        return result;
    }
}
