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
package ru.spbau.shestavin.task6.serialization;

import ru.spbau.shestavin.task6.tools.PropertiesAnalyzer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;

/**
 * Class for serialization using reflection.
 * Method serialize reads all "properties" of class T and
 * writes it to file specified by filename.
 *
 * @author Dmitriy shestavin
 * @version 1.0 1 Sep 2012
 */
public class ReflectionSerializer<T> {
    private static final PropertiesAnalyzer analyzer = new PropertiesAnalyzer();

    /**
     * Method reads all "properties" of class T and
     * writes it to file specified by filename.
     * Method use properties with setters. Properties
     * without setters ignored.
     *
     * @param t        - object to serialize.
     * @param filename - target file.
     * @throws SerializationException when have some problems with serialization like:
     *                                Getter throw an exception, Illegal access to getter, Getter have arguments,
     *                                Initialization provoked by getter fails.
     * @throws NullPointerException   if one of params are null.
     * @throws IOException            if IOException occurs on writing properties to file.
     */
    public void serialize(T t, String filename) throws SerializationException, NullPointerException, IOException {
        if (null == filename) {
            throw new NullPointerException("FileName is null.");
        } else if (null == t) {
            throw new NullPointerException("Object for serialization is null.");
        }
        Properties propertiesTable = new Properties();
        List<PropertiesAnalyzer.Property> propertiesList = analyzer.getProperties(t.getClass());
        for (PropertiesAnalyzer.Property property : propertiesList) {
            try {
                String key = property.getName();
                Object value = property.getGetter().invoke(t);
                if (null != value) {
                    String strValue = value.toString();
                    propertiesTable.setProperty(key, strValue);
                }
            } catch (IllegalAccessException e) {
                throw new SerializationException("Illegal access to getter. " + e.getMessage());
            } catch (InvocationTargetException e) {
                throw new SerializationException("Getter throw an exception. " + e.getMessage());
            } catch (IllegalArgumentException e) {
                throw new SerializationException("Getter have arguments. " + e.getMessage());
            } catch (ExceptionInInitializerError e) {
                throw new SerializationException("Initialization provoked by getter fails. " + e.getMessage());
            }
        }
        Writer writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filename));
            propertiesTable.store(writer, t.getClass().getName());
        } finally {
            if (null != writer) {
                writer.close();
            }
        }
    }
}
