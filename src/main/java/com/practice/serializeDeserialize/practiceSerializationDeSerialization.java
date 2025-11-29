package com.practice.serializeDeserialize;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class practiceSerializationDeSerialization {
    public static void main(String[] args) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Student johnDoe = new Student(101, "John Doe");
            // Serialization: object to JSON
            String json = objectMapper.writeValueAsString(johnDoe);
            System.out.println("Serialized JSON: "+json);
            //Serialized JSON: {"id":101,"name":"John Doe"}


            // Deserialization: JSON to object
            Student deserializedStudentObject = objectMapper.readValue(json, Student.class);
            System.out.println("Deserialized object: "+deserializedStudentObject);
            // Deserialized object: Student{id=101, name='John Doe'}


            // Using records
            StudentRecord marryJane = new StudentRecord(102, "Marry Jane");
            // Serialization
            String json1 = objectMapper.writeValueAsString(marryJane);
            System.out.println("Serialized JSON: "+json1);
            // Serialized JSON: {"id":102,"name":"Marry Jane"}
            // Deserialization
            StudentRecord studentRecord = objectMapper.readValue(json1, StudentRecord.class);
            System.out.println("Deserialized object: "+studentRecord);
            //Deserialized object: StudentRecord[id=102, name=Marry Jane]

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
