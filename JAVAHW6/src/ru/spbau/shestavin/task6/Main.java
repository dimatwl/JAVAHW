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

import ru.spbau.shestavin.task6.serialization.ReflectionDeSerializer;
import ru.spbau.shestavin.task6.serialization.ReflectionSerializer;
import ru.spbau.shestavin.task6.serialization.SerializationException;

import java.io.IOException;

/**
 * Main class. Contains only one method witch is entry point.
 *
 * @author Dmitriy shestavin
 * @version 1.0 1 Sep 2012
 */

public class Main {

    /**
     * Entry point of the program.
     * Input data for program is name of .properties file
     * with description of student.
     * Program will deserialize Student object from file
     * and then increase avgGrade for 1.0, but 5.0 is max value.
     * If there is no params program will do nothing.
     *
     * @param args contains arguments from command line.
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("No commandline arguments. Exiting...");
            return;
        }
        try {
            ReflectionSerializer<Student> serializer = new ReflectionSerializer<Student>();
            ReflectionDeSerializer<Student> deserializer = new ReflectionDeSerializer<Student>();
            Student student = deserializer.deserialize(args[0], Student.class);
            System.out.println("Student deserialized: ");
            System.out.println(student);
            if (student.getAvgGrade() <= 4.0) {
                student.setAvgGrade(student.getAvgGrade() + 1.0);
                System.out.println("Student grade increased for 1.0: ");
                System.out.println(student);
            } else {
                student.setAvgGrade(5.0);
                System.out.println("Student have a maximum grade: ");
                System.out.println(student);
            }
            serializer.serialize(student, args[0]);
            System.out.println("Student serialized.");
        } catch (SerializationException e) {
            System.out.println("Got problem with serialization: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Got IOException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Got some unexpected problem: " + e.getMessage());
        }
    }
}
