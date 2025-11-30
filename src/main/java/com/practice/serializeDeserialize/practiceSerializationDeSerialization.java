package com.practice.serializeDeserialize;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.time.LocalDate;
import java.time.temporal.TemporalAmount;

public class practiceSerializationDeSerialization {
    public static void main(String[] args) {

        usingJackson();
        nativeSerializationDemo();
        usingJacksonWithTransient();
        nativeWithTransient();
    }

    private static void nativeWithTransient(){
        byte[] serializedBytes;
        NewStudent ronWeasly = new NewStudent(12, "Ron Weasly", LocalDate.now(), LocalDate.now().minusDays(1));
        try(ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos)){
            oos.writeObject(ronWeasly);
            serializedBytes = bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try(ByteArrayInputStream bis = new ByteArrayInputStream(serializedBytes);
                ObjectInputStream ois = new ObjectInputStream(bis)){
            NewStudent newStudent = (NewStudent) ois.readObject();
            System.out.println(newStudent);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private static void usingJacksonWithTransient(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String serializedObjInJSON;
        // Serialized
        try {
            serializedObjInJSON = objectMapper.writeValueAsString(new NewStudent(11, "Harry Potter", LocalDate.now(), LocalDate.now().minusDays(1)));
            System.out.println(serializedObjInJSON);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        // Deserialized
        try{
            NewStudent deserializedObj = objectMapper.readValue(serializedObjInJSON, NewStudent.class);
            System.out.println(deserializedObj);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    private static void nativeSerializationDemo() {
        Student student = new Student(101, "John Doe");
        // Serialization
        try (FileOutputStream fileOutputStream = new FileOutputStream("student.ser");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.writeObject(student);// creates a new file student.ser

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Deserialization
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("student.ser"))) {
            Student deserializedObject = (Student) objectInputStream.readObject();
            System.out.println(deserializedObject);//Student{id=101, name='John Doe'}
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private static void usingJackson() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Student johnDoe = new Student(101, "John Doe");
            // Serialization: object to JSON
            String json = objectMapper.writeValueAsString(johnDoe);
            System.out.println("Serialized JSON: " + json);
            //Serialized JSON: {"id":101,"name":"John Doe"}


            // Deserialization: JSON to object
            Student deserializedStudentObject = objectMapper.readValue(json, Student.class);
            System.out.println("Deserialized object: " + deserializedStudentObject);
            // Deserialized object: Student{id=101, name='John Doe'}


            // Using records
            StudentRecord marryJane = new StudentRecord(102, "Marry Jane");
            // Serialization
            String json1 = objectMapper.writeValueAsString(marryJane);
            System.out.println("Serialized JSON: " + json1);
            // Serialized JSON: {"id":102,"name":"Marry Jane"}
            // Deserialization
            StudentRecord studentRecord = objectMapper.readValue(json1, StudentRecord.class);
            System.out.println("Deserialized object: " + studentRecord);
            //Deserialized object: StudentRecord[id=102, name=Marry Jane]

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
