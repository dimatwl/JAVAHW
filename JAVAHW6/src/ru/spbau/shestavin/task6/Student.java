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
package ru.spbau.shestavin.task6;

/**
 * Class representing a student with "properties":
 * name, surname, age, avgGrade
 *
 * @author Dmitriy shestavin
 * @version 1.0 1 Sep 2012
 */
public class Student {

    private String myName;
    private String mySurname;
    private int myAge;
    private double myAvgGrade;

    /**
     * Default constructor.
     */
    public Student() {
    }

    /**
     * Getter for name property.
     *
     * @return string representing name property
     */
    public String getName() {
        return myName;
    }

    /**
     * Setter for name property.
     *
     * @param name - string representing name property
     */
    public void setName(String name) {
        myName = name;
    }

    /**
     * Getter for surname property.
     *
     * @return string representing surname property
     */
    public String getSurname() {
        return mySurname;
    }

    /**
     * Setter for surname property.
     *
     * @param surname - string representing surname property
     */
    public void setSurname(String surname) {
        mySurname = surname;
    }

    /**
     * Getter for age property.
     *
     * @return int representing age property
     */
    public int getAge() {
        return myAge;
    }

    /**
     * Setter for age property.
     *
     * @param age - int representing age property
     */
    public void setAge(int age) {
        myAge = age;
    }

    /**
     * Getter for avgGrade property.
     *
     * @return double representing avgGrade property
     */
    public double getAvgGrade() {
        return myAvgGrade;
    }

    /**
     * Setter for avgGrade property.
     *
     * @param avgGrade - double representing avgGrade property
     */
    public void setAvgGrade(double avgGrade) {
        myAvgGrade = avgGrade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", age=" + getAge() +
                ", avgGrade=" + getAvgGrade() +
                '}';
    }
}
