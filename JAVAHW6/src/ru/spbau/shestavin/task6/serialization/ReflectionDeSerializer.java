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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;

/**
 * Class for deserialization using reflection.
 * Method deserialize reads all "properties" from file specified by filename
 * and writes it to object of class T using setters.
 *
 * @author Dmitriy shestavin
 * @version 1.0 1 Sep 2012
 */
public class ReflectionDeSerializer<T> {
    private static final PropertiesAnalyzer analyzer = new PropertiesAnalyzer();

    /**
     * Method deserialize reads all "properties" from file specified by filename
     * and writes it to object of class T using setters.
     * Method use properties with setters. Properties
     * without setters ignored.
     */
    public T deserialize(String filename, Class<T> clazz) throws SerializationException, NullPointerException, IOException {
        if (null == filename) {
            throw new NullPointerException("FileName is null.");
        } else if (null == clazz) {
            throw new NullPointerException("Class for deserialization is null.");
        }
        Properties propertiesTable = new Properties();
        Reader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filename));
            propertiesTable.load(reader);
        } catch (IllegalArgumentException e) {
            throw new SerializationException("Have problems loading properties. Bad format.");
        } finally {
            if (null != reader) {
                reader.close();
            }
        }
        List<PropertiesAnalyzer.Property> propertiesList = analyzer.getProperties(clazz);
        Constructor<T> constructor;
        T t;
        try {
            constructor = clazz.getConstructor();
            t = constructor.newInstance();
        } catch (NoSuchMethodException e) {
            throw new SerializationException("No default constructor for class " + clazz.getName() + ". " + e.getMessage());
        } catch (IllegalAccessException e) {
            throw new SerializationException("Illegal access to default constructor. " + e.getMessage());
        } catch (InstantiationException e) {
            throw new SerializationException("Default constructor represents an abstract class in " + clazz.getName() + "." + e.getMessage());
        } catch (InvocationTargetException e) {
            throw new SerializationException("Default constructor throws an exception. " + e.getMessage());
        } catch (ExceptionInInitializerError e) {
            throw new SerializationException("Initialization provoked by default constructor fails. " + e.getMessage());
        }
        for (PropertiesAnalyzer.Property property : propertiesList) {
            try {
                String key = property.getName();
                String value = propertiesTable.getProperty(key);
                Class propertyClass = property.getGetter().getReturnType();
                Object castedValue = null;
                if (null == value) {
                    continue;
                } else if (propertyClass.equals(Integer.class) || propertyClass.equals(int.class)) {
                    castedValue = Integer.parseInt(value);
                } else if (propertyClass.equals(Byte.class) || propertyClass.equals(byte.class)) {
                    castedValue = Byte.parseByte(value);
                } else if (propertyClass.equals(Short.class) || propertyClass.equals(short.class)) {
                    castedValue = Short.parseShort(value);
                } else if (propertyClass.equals(Long.class) || propertyClass.equals(long.class)) {
                    castedValue = Long.parseLong(value);
                } else if (propertyClass.equals(Float.class) || propertyClass.equals(float.class)) {
                    castedValue = Float.parseFloat(value);
                } else if (propertyClass.equals(Double.class) || propertyClass.equals(double.class)) {
                    castedValue = Double.parseDouble(value);
                } else if (propertyClass.equals(Boolean.class) || propertyClass.equals(boolean.class)) {
                    castedValue = Boolean.parseBoolean(value);
                } else if (propertyClass.equals(Character.class) || propertyClass.equals(char.class)) {
                    if (value.length() > 0) {
                        castedValue = value.charAt(0);
                    }
                } else if (propertyClass.equals(String.class)) {
                    castedValue = value;
                } else {
                    throw new SerializationException("Unsupported class for deserialization: " + propertyClass.getName());
                }
                property.getSetter().invoke(t, castedValue);
            } catch (NumberFormatException e) {
                throw new SerializationException("Can't deserialize a value: " + e.getMessage());
            } catch (IllegalAccessException e) {
                throw new SerializationException("Illegal access to setter. " + e.getMessage());
            } catch (InvocationTargetException e) {
                throw new SerializationException("Setter throw an exception. " + e.getMessage());
            } catch (IllegalArgumentException e) {
                throw new SerializationException("Getter return value and setter input value have different types. " + e.getMessage());
            } catch (ExceptionInInitializerError e) {
                throw new SerializationException("Initialization provoked by this method fails. " + e.getMessage());
            }
        }
        return t;
    }
}
