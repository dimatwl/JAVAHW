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
package ru.spbau.shestavin.task6.tools;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to extract "properties" from specified class.
 * "Properties" without setter or getter ignored.
 *
 * @author Dmitriy shestavin
 * @version 1.0 1 Sep 2012
 */
public class PropertiesAnalyzer {

    /**
     * Extracts "properties" from specified class.
     * "Properties" without setter or getter ignored.
     *
     * @param clazz - class to extract properties.
     * @return list of property objects representing all suitable "properties".
     */
    public List<Property> getProperties(Class clazz) {
        List<Property> properties = new ArrayList<Property>();
        for (Method setter : getSetters(clazz)) {
            try {
                String propertyName = (new StringBuilder()).append(Character.toLowerCase(setter.getName().charAt(3))).append(setter.getName().substring(4)).toString();
                String getterName = (new StringBuilder()).append('g').append(setter.getName().substring(1)).toString();
                Method getter = clazz.getMethod(getterName);
                properties.add(new Property(propertyName, getter, setter));
            } catch (NoSuchMethodException e) {
                continue;
            }
        }
        return properties;
    }

    /**
     * Class to represent "property".
     *
     * @author Dmitriy shestavin
     * @version 1.0 1 Sep 2012
     */
    public final class Property {

        private final String name;
        private final Method getter;
        private final Method setter;

        /**
         * Constructor to create appropriate Property object.
         *
         * @param name   - represents name of property
         * @param getter - getter-method for property
         * @param setter - setter-method for property
         */
        public Property(String name, Method getter, Method setter) {
            this.name = name;
            this.getter = getter;
            this.setter = setter;
        }

        /**
         * Getter for getter-method for property.
         *
         * @return getter-method for property.
         */
        public Method getGetter() {
            return getter;
        }

        /**
         * Getter for setter-method for property.
         *
         * @return setter-method for property.
         */
        public Method getSetter() {
            return setter;
        }

        /**
         * Getter for name of property.
         *
         * @return name or property.
         */
        public String getName() {
            return name;
        }
    }

    private List<Method> getSetters(Class clazz) {
        List<Method> getters = new ArrayList<Method>();
        for (Method method : clazz.getMethods()) {
            if (method.getName().startsWith("set") && method.getName().length() > 3 && Character.isUpperCase(method.getName().charAt(3)) && Modifier.isPublic(method.getModifiers())) {
                getters.add(method);
            }
        }
        return getters;
    }
}
