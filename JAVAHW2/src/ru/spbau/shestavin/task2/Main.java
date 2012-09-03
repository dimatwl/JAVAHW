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
package ru.spbau.shestavin.task2;


import java.io.IOException;

/**
 * Main class. Contains only one method witch is entry point.
 *
 * @author Dmitriy Shestavin
 * @version 1.0 21 Feb 2012
 */

public class Main {

    /**
     * Entry point of the program.
     * First command line argument is absolute path to directory
     * witch should be displayed to console
     *
     * @param args contains arguments from command line.
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            FileSystemWalker myWalker = new FileSystemWalker(args[0], "\\..*", System.out);
            try {
                myWalker.write();
            } catch (IOException e) {
                System.err.println("Got IOException. " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Got unexpected exception. " + e.getMessage());
            }
        } else {
            System.out.println("There is no parameters, sorry...");
        }

    }
}
