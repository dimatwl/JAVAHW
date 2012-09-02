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


import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Walks through file system from root specified in constructor and prints all entries witch isn't matched by regexp specified in constructor.
 *
 * @author Dmitriy Shestavin
 * @version 1.0 21 Feb 2012
 */

public class FileSystemWalker {
    private File myRoot;
    private PrintStream myStream = null;
    private Pattern myBlockingPattern;

    private int GetMaxNameLength(List<File> inpEntries) {
        return Collections.max(inpEntries, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().length() - o2.getName().length();
            }
        }).getName().length();
    }

    private void sort(List<File> inpEntries) {
        Collections.sort(inpEntries, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }

    private void writeDir(File inpEntry, String inpPrefix, boolean isFirstEntry) throws IOException{
        boolean accessDenied = false;
        List<File> subEntries;
        try {
            if (inpEntry.canRead()) {
                subEntries = getSubEntries(inpEntry);
            } else {
                accessDenied = true;
                subEntries = null;
            }
        } catch (SecurityException e) {
            accessDenied = true;
            subEntries = null;
        }
        String entryName = "";
        if (isFirstEntry) {
            entryName = inpEntry.getName();
            isFirstEntry = false;
        } else if (inpPrefix.charAt(inpPrefix.length() - 1) != '|') {
            entryName = inpPrefix.substring(0, inpPrefix.length() - 1) + '|' + '_' + inpEntry.getName();
        } else {
            entryName = inpPrefix + '_' + inpEntry.getName();
        }
        if (accessDenied && inpEntry.isDirectory()) {
            myStream.write((entryName + "(access denied)").getBytes());
            myStream.println();
        } else if (subEntries == null || subEntries.isEmpty()) {
            myStream.write(entryName.getBytes());
            myStream.println();
        } else {
            myStream.write(entryName.getBytes());
            myStream.println();
            String currentOffset = "";
            for (int i = 0; i < inpEntry.getName().length() + 1; ++i) {
                currentOffset += ' ';
            }
            //newPrefix += '|';
            sort(subEntries);
            for (int i = 0; i < subEntries.size() - 1; ++i) {
                writeDir(subEntries.get(i), inpPrefix + currentOffset + '|', false);
            }
            writeDir(subEntries.get(subEntries.size() - 1), inpPrefix + currentOffset + ' ', false);
        }
    }

    /**
     * Creates a new FileSystemWalker instance.
     *
     * @param inpAbsolutePath is an absolute path to entry in file system.
     * @param inpRegExp       is a pattern witch is intended to block matched names.
     * @param inpStream       is a stream.
     */
    public FileSystemWalker(String inpAbsolutePath, String inpRegExp, PrintStream inpStream) {
        myRoot = new File(inpAbsolutePath);
        myStream = inpStream;
        myBlockingPattern = Pattern.compile(inpRegExp);
    }

    /**
     * Prints all entries witch isn't matched by regexp specified in constructor
     * to destination specified in constructor.
     */
    public void write() throws IOException{
        writeDir(myRoot, "", true);
        myStream.flush();
    }

    /**
     * Walks through directory. Result contains all entries witch is children of current entry.
     *
     * @return List of File contains all entries witch is children of current entry or null if entry is not directory.
     * @throws SecurityException - If a security manager exists and its SecurityManager.checkRead(java.lang.String)
     *                           method denies read access to the file.
     * @throws NullPointerException - If inpEntry == null or if inpEntry.listFiles() throws NullPointerException.
     */
    public List<File> getSubEntries(File inpEntry) throws SecurityException, NullPointerException {
        List<File> result = new ArrayList<File>();
        if (inpEntry.isDirectory()) {
            for (File subEntry : inpEntry.listFiles()) {
                if (!this.myBlockingPattern.matcher(subEntry.getName()).matches()) {
                    result.add(new File(subEntry.getAbsolutePath()));
                }
            }
            return result;
        } else {
            return null;
        }
    }

}
